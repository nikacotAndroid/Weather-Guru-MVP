package mk.petrovski.weathergurumvp.ui.main;

import io.reactivex.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.local.db.DbHelper;
import mk.petrovski.weathergurumvp.data.local.preferences.ApplicationPreferences;
import mk.petrovski.weathergurumvp.data.remote.ApiHelper;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
    implements MainMvpPresenter<V> {

  @Inject public MainPresenter(DbHelper dbHelper, ApplicationPreferences applicationPreferences,
      ApiHelper apiHelper) {
    super(dbHelper, applicationPreferences, apiHelper);
  }

  @Override public void setDrawerCities() {
    getCompositeDisposableHelper().execute(getDbHelper().getAllCities(),
        new Consumer<List<CityDetailsModel>>() {
          @Override public void accept(List<CityDetailsModel> cityDetailsModels) throws Exception {
            if (cityDetailsModels != null && cityDetailsModels.size() > 0) {
              getMvpView().showCities(cityDetailsModels);
            } else {
              getMvpView().showEmptyView();
            }
          }
        });
  }

  @Override public void setUserInfo() {
    getMvpView().setMenuUserName(getApplicationPreferences().getUserName());
  }

  @Override public void selectCity(CityDetailsModel city) {
    getCompositeDisposableHelper().execute(getDbHelper().selectCity(city, true),
        new Consumer<Boolean>() {
          @Override public void accept(Boolean aBoolean) throws Exception {
            getMvpView().onCitySelect();
            // refresh cities
            setDrawerCities();
          }
        });
  }

  @Override public void setCurrentCityTitle() {
    CityDetailsModel selectedCity = getDbHelper().getSelectedCityModel();

    if (selectedCity != null) {
      getMvpView().setCurrentCityTitle(selectedCity.getAreaName());
    } else {
      getMvpView().setCurrentCityTitle("No Locations");
    }
  }
}
