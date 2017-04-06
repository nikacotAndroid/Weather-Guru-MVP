package mk.petrovski.weathergurumvp.presenter;

import io.reactivex.Observable;
import java.util.List;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.TestModels;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ServerNotAvailableException;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.SearchApiResponseModel;
import mk.petrovski.weathergurumvp.ui.city.ManageCityMvpView;
import mk.petrovski.weathergurumvp.ui.city.ManageCityPresenter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nikola Petrovski on 3/20/2017.
 */
@RunWith(MockitoJUnitRunner.class) public class ManageCityPresenterTest
    extends BasePresenterTest<ManageCityPresenter<ManageCityMvpView>, ManageCityMvpView> {

  @Override ManageCityPresenter<ManageCityMvpView> createPresenter() {
    return new ManageCityPresenter<>(compositeDisposableHelper, dataManager);
  }

  @Override ManageCityMvpView createView() {
    return mock(ManageCityMvpView.class);
  }

  @Test public void loadStoredCities() {
    List<CityDetailsModel> cities = TestModels.getCities(5);

    when(dataManager.getAllCities()).thenReturn(Observable.just(cities));

    presenter.loadCities();
    testScheduler.triggerActions();

    verify(view).showCities(cities);
    verify(view, never()).showEmptyView();
  }

  @Test public void loadStoredCitiesEmpty() {
    List<CityDetailsModel> cities = TestModels.getCities(0);

    when(dataManager.getAllCities()).thenReturn(Observable.just(cities));

    presenter.loadCities();
    testScheduler.triggerActions();

    verify(view).showEmptyView();
    verify(view, never()).showCities(ArgumentMatchers.<CityDetailsModel>anyList());
  }

  @Test public void newLocationSuccess() {
    LocationModel locationModel = TestModels.newLocationModel();
    CityDetailsModel cityDetailsModel = TestModels.newCityModel();

    when(dataManager.isCityExist(anyString())).thenReturn(false);
    when(dataManager.addNewCity(locationModel)).thenReturn(Observable.just(2L));
    when(dataManager.getCityById(2L)).thenReturn(Observable.just(cityDetailsModel));

    presenter.addNewLocation(locationModel);
    testScheduler.triggerActions();

    verify(view).onCityAdded(cityDetailsModel);
  }

  @Test public void newLocationFail() {
    LocationModel locationModel = TestModels.newLocationModel();
    when(dataManager.isCityExist(anyString())).thenReturn(true);

    presenter.addNewLocation(locationModel);

    verify(view).onError(R.string.error_multiple_city);
  }

  @Test public void deleteSelectedCity() {
    CityDetailsModel cityDetailsModel = TestModels.newCityModel();
    cityDetailsModel.setIsSelected(true);

    when(dataManager.deleteCity(cityDetailsModel)).thenReturn(Observable.just(true));
    when(dataManager.selectFirstCity()).thenReturn(Observable.just(true));

    presenter.deleteLocation(anyInt(), cityDetailsModel);
    testScheduler.triggerActions();

    verify(view).onCityDelete(anyInt());
  }

  @Test public void deleteNonSelectedCity() {
    CityDetailsModel cityDetailsModel = TestModels.newCityModel();
    cityDetailsModel.setIsSelected(false);

    when(dataManager.deleteCity(cityDetailsModel)).thenReturn(Observable.just(true));

    presenter.deleteLocation(anyInt(), cityDetailsModel);
    testScheduler.triggerActions();

    verify(view).onCityDelete(anyInt());
  }

  @Test public void loadCitiesSuccessfully() {
    SearchApiResponseModel responseModel = TestModels.getSearchResponseModel(5);

    stubDataManagerLoadCity(Observable.just(responseModel));

    presenter.loadAutocompleteCities(Observable.just("Test"));
    testScheduler.triggerActions();

    verify(view).showAutocompleteCities(responseModel.getSearch_api().getResult(),
        responseModel.getSearch_api().getResult().toArray(new LocationModel[5]));
    verify(view, never()).onError(anyString());
    verify(view, never()).showEmptyView();
  }

  @Test public void loadCitiesFail() {
    ServerNotAvailableException exception = new ServerNotAvailableException();

    when(dataManager.getErrorHandlerHelper()).thenReturn(errorHandlerHelper);
    when(errorHandlerHelper.getProperErrorMessage(exception)).thenReturn("Server not available");

    stubDataManagerLoadCity(Observable.<SearchApiResponseModel>error(exception));

    presenter.loadAutocompleteCities(Observable.just("Test"));
    testScheduler.triggerActions();

    verify(view).onError("Server not available");
    verify(view, never()).showEmptyView();
  }

  private void stubDataManagerLoadCity(Observable observable) {
    when(view.isNetworkConnected()).thenReturn(true);
    when(dataManager.locationsApiRequest(anyString())).thenReturn(observable);
  }
}