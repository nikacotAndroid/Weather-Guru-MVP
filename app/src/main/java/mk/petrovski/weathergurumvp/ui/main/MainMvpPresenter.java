package mk.petrovski.weathergurumvp.ui.main;

import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.ui.base.Presenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends Presenter<V> {

  void setDrawerCities();

  void setUserInfo();

  void selectCity(CityDetailsModel city);

  void setCurrentCityTitle();
}
