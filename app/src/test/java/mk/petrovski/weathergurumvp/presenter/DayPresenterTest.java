package mk.petrovski.weathergurumvp.presenter;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import mk.petrovski.weathergurumvp.TestModels;
import mk.petrovski.weathergurumvp.data.DataManager;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ErrorHandlerHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ServerNotAvailableException;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;
import mk.petrovski.weathergurumvp.ui.day.DayMvpView;
import mk.petrovski.weathergurumvp.ui.day.DayPresenter;
import mk.petrovski.weathergurumvp.ui.day_detail.DayDetailMvpView;
import mk.petrovski.weathergurumvp.ui.day_detail.DayDetailPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nikola Petrovski on 3/20/2017.
 */
@RunWith(MockitoJUnitRunner.class) public class DayPresenterTest extends
    BasePresenterTest<DayPresenter<DayMvpView>, DayMvpView> {

  @Override DayPresenter<DayMvpView> createPresenter() {
    return new DayPresenter<>(compositeDisposableHelper, dataManager);
  }

  @Override DayMvpView createView() {
    return mock(DayMvpView.class);
  }

  @Test public void loadDaysSuccessfully() {
    WeatherResponseModel responseModel = TestModels.getWeatherResponseModel(5);
    stubDataManagerLoadWeather(Observable.just(responseModel));

    presenter.loadWeather();
    testScheduler.triggerActions();

    verify(view, times(1)).showLoading();
    verify(view).showWeather(responseModel.getData().getWeather());
    verify(view, times(1)).hideLoading();
    verify(view, never()).showEmptyView();
    verify(view, never()).onError(anyString());
  }

  @Test public void loadDaysEmpty() {
    WeatherResponseModel responseModel = TestModels.getWeatherResponseModel(0);
    stubDataManagerLoadWeather(Observable.just(responseModel));

    presenter.loadWeather();
    testScheduler.triggerActions();

    verify(view).showEmptyView();
    verify(view, never()).showWeather(ArgumentMatchers.<WeatherModel>anyList());
    verify(view, never()).onError(anyString());
  }

  @Test public void loadDaysFail() {
    ServerNotAvailableException exception = new ServerNotAvailableException();

    when(dataManager.getErrorHandlerHelper()).thenReturn(errorHandlerHelper);
    when(errorHandlerHelper.getProperErrorMessage(exception)).thenReturn("Server not available");

    stubDataManagerLoadWeather(Observable.<WeatherResponseModel>error(exception));

    presenter.loadWeather();
    testScheduler.triggerActions();

    verify(view).onError("Server not available");
    verify(view, never()).showWeather(ArgumentMatchers.<WeatherModel>anyList());
    verify(view, never()).showEmptyView();
  }

  @Test public void noSelectCity() {
    when(dataManager.getSelectedCityModel()).thenReturn(null);

    presenter.loadWeather();
    testScheduler.triggerActions();

    verify(view).showEmptyView();
  }

  private void stubDataManagerLoadWeather(Observable observable) {
    when(view.isNetworkConnected()).thenReturn(true);
    when(dataManager.getSelectedCityModel()).thenReturn(TestModels.newCityModel());
    when(dataManager.weatherApiRequest(anyString(), anyString())).thenReturn(observable);
  }
}