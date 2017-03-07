package mk.petrovski.weathergurumvp.ui.city;

import io.reactivex.Observable;
import java.util.List;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;
import mk.petrovski.weathergurumvp.ui.base.BaseMvpView;
import mk.petrovski.weathergurumvp.ui.base.Presenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public interface ManageCityMvpPresenter<V extends BaseMvpView> extends Presenter<V> {

  void loadAutocompleteCities(Observable<String> cityQueryObservable);

  void loadCities();

  void addNewLocation(LocationModel location);

  void deleteLocation(int position, CityDetailsModel city);
}
