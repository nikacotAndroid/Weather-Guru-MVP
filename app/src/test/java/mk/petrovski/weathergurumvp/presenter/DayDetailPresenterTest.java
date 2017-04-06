package mk.petrovski.weathergurumvp.presenter;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;
import mk.petrovski.weathergurumvp.TestModels;
import mk.petrovski.weathergurumvp.data.DataManager;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;
import mk.petrovski.weathergurumvp.ui.day_detail.DayDetailMvpView;
import mk.petrovski.weathergurumvp.ui.day_detail.DayDetailPresenter;
import mk.petrovski.weathergurumvp.utils.reactive.TestSchedulerProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nikola Petrovski on 3/20/2017.
 */
@RunWith(MockitoJUnitRunner.class) public class DayDetailPresenterTest extends
    BasePresenterTest<DayDetailPresenter<DayDetailMvpView>, DayDetailMvpView> {

  @Override DayDetailPresenter<DayDetailMvpView> createPresenter() {
    return new DayDetailPresenter<>(compositeDisposableHelper, dataManager);
  }

  @Override DayDetailMvpView createView() {
    return mock(DayDetailMvpView.class);
  }

  @Test public void selectCityAsCurrent() {
    CityDetailsModel cityDetailsModel = TestModels.newCityModel();

    when(dataManager.getSelectedCity()).thenReturn(Observable.just(cityDetailsModel));

    presenter.setCurrentCity();
    testScheduler.triggerActions();

    verify(view).setCurrentCityName(cityDetailsModel.getAreaName());
  }
}