package mk.petrovski.weathergurumvp.presenter;

import io.reactivex.Observable;
import java.util.List;
import mk.petrovski.weathergurumvp.TestModels;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.ui.main.MainMvpView;
import mk.petrovski.weathergurumvp.ui.main.MainPresenter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nikola Petrovski on 3/31/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class MainPresenterTest
    extends BasePresenterTest<MainPresenter<MainMvpView>, MainMvpView> {

  @Override MainPresenter<MainMvpView> createPresenter() {
    return new MainPresenter<>(compositeDisposableHelper, dataManager);
  }

  @Override MainMvpView createView() {
    return mock(MainMvpView.class);
  }

  @Test public void loadCities() {
    List<CityDetailsModel> cities = TestModels.getCities(5);
    when(dataManager.getAllCities()).thenReturn(Observable.just(cities));

    presenter.setDrawerCities();
    testScheduler.triggerActions();

    verify(view).showCities(cities);
    verify(view, never()).showEmptyView();
  }

  @Test public void noCities() {
    List<CityDetailsModel> cities = TestModels.getCities(0);
    when(dataManager.getAllCities()).thenReturn(Observable.just(cities));

    presenter.setDrawerCities();
    testScheduler.triggerActions();

    verify(view).showEmptyView();
  }

  @Test public void userInfoSet() {
    String userName = "Nikola";

    when(dataManager.getUserName()).thenReturn(userName);

    presenter.setUserInfo();

    verify(view).setMenuUserName(userName);
  }

  @Test public void selectCity() {
    CityDetailsModel city = TestModels.newCityModel();
    List<CityDetailsModel> cities = TestModels.getCities(5);

    when(dataManager.getAllCities()).thenReturn(Observable.just(cities));
    when(dataManager.selectCity(city, true)).thenReturn(Observable.just(true));

    presenter.selectCity(city);
    testScheduler.triggerActions();

    verify(view).onCitySelect();
    verify(view).showCities(cities);
    verify(view, never()).showEmptyView();
  }

  @Test public void currentCityTitle() {
    CityDetailsModel city = TestModels.newCityModel();

    when(dataManager.getSelectedCityModel()).thenReturn(city);

    presenter.setCurrentCityTitle();

    verify(view).setCurrentCityTitle(city.getAreaName());
  }

  @Test public void noCityTitle() {
    when(dataManager.getSelectedCityModel()).thenReturn(null);

    presenter.setCurrentCityTitle();

    verify(view).setCurrentCityTitle("No Locations");
  }
}
