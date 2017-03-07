package mk.petrovski.weathergurumvp.data.local.db;

import io.reactivex.Observable;
import java.util.List;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;

/**
 * Created by Nikola Petrovski on 2/20/2017.
 */

public interface BaseDbHelper {

  Observable<Long> addNewCity(LocationModel locationModel);

  Observable<Long> addDefaultCity();

  Observable<List<CityDetailsModel>> getAllCities();

  Observable<Boolean> deleteCity(CityDetailsModel city);

  Observable<CityDetailsModel> getSelectedCity();

  Observable<CityDetailsModel> getCityById(Long id);

  Observable<Boolean> selectCity(CityDetailsModel city, boolean isSelected);

  Observable<Boolean> selectFirstCity();

  CityDetailsModel getSelectedCityModel();

  Long getCitiesCount();

  Boolean isCityExist(String weatherUrl);
}
