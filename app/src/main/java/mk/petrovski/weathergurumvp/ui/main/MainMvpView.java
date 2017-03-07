package mk.petrovski.weathergurumvp.ui.main;

import java.util.List;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.ui.base.BaseMvpView;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public interface MainMvpView extends BaseMvpView {

  void showCities(List<CityDetailsModel> locationList);

  void showEmptyView();

  void setMenuUserName(String userName);

  void onCitySelect();

  void setCurrentCityTitle(String title);
}
