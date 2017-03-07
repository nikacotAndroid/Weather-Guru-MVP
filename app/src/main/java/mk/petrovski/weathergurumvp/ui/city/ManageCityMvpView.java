package mk.petrovski.weathergurumvp.ui.city;

import java.util.List;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;
import mk.petrovski.weathergurumvp.ui.base.BaseMvpView;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public interface ManageCityMvpView extends BaseMvpView {

  void showAutocompleteCities(List<LocationModel> locationList, LocationModel[] locationArray);

  void showCities(List<CityDetailsModel> cities);

  void showEmptyView();

  void onCityAdded(CityDetailsModel city);

  void onCityDelete(int position);
}
