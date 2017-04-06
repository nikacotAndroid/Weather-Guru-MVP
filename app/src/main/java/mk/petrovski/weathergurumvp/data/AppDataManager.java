package mk.petrovski.weathergurumvp.data;

import android.content.Context;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.local.db.DbHelper;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.local.preferences.PreferencesHelper;
import mk.petrovski.weathergurumvp.data.remote.ApiHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ErrorHandlerHelper;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.SearchApiResponseModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;

/**
 * Created by Nikola Petrovski on 3/30/2017.
 */

//TODO what if there will be more methods?
public class AppDataManager implements DataManager {

  private final Context context;
  private final DbHelper dbHelper;
  private final PreferencesHelper applicationPreferences;
  private final ApiHelper apiHelper;

  @Inject
  public AppDataManager(Context context, DbHelper dbHelper,
      PreferencesHelper applicationPreferences, ApiHelper apiHelper) {
    this.context = context;
    this.dbHelper = dbHelper;
    this.applicationPreferences = applicationPreferences;
    this.apiHelper = apiHelper;
  }

  @Override public void setLoggedIn() {
    applicationPreferences.setLoggedIn();
  }

  @Override public boolean isLoggedIn() {
    return applicationPreferences.isLoggedIn();
  }

  @Override public void setUserName(String userName) {
    applicationPreferences.setUserName(userName);
  }

  @Override public String getUserName() {
    return applicationPreferences.getUserName();
  }

  @Override public Observable<Long> addNewCity(LocationModel locationModel) {
    return dbHelper.addNewCity(locationModel);
  }

  @Override public Observable<Long> addDefaultCity() {
    return dbHelper.addDefaultCity();
  }

  @Override public Observable<List<CityDetailsModel>> getAllCities() {
    return dbHelper.getAllCities();
  }

  @Override public Observable<Boolean> deleteCity(CityDetailsModel city) {
    return dbHelper.deleteCity(city);
  }

  @Override public Observable<CityDetailsModel> getSelectedCity() {
    return dbHelper.getSelectedCity();
  }

  @Override public Observable<CityDetailsModel> getCityById(Long id) {
    return dbHelper.getCityById(id);
  }

  @Override public Observable<Boolean> selectCity(CityDetailsModel city, boolean isSelected) {
    return dbHelper.selectCity(city, isSelected);
  }

  @Override public Observable<Boolean> selectFirstCity() {
    return dbHelper.selectFirstCity();
  }

  @Override public CityDetailsModel getSelectedCityModel() {
    return dbHelper.getSelectedCityModel();
  }

  @Override public Long getCitiesCount() {
    return dbHelper.getCitiesCount();
  }

  @Override public Boolean isCityExist(String weatherUrl) {
    return dbHelper.isCityExist(weatherUrl);
  }

  @Override
  public Observable<WeatherResponseModel> weatherApiRequest(String latitude, String longitude) {
    return apiHelper.weatherApiRequest(latitude, longitude);
  }

  @Override public Observable<SearchApiResponseModel> locationsApiRequest(String query) {
    return apiHelper.locationsApiRequest(query);
  }

  @Override public ErrorHandlerHelper getErrorHandlerHelper() {
    return apiHelper.getErrorHandlerHelper();
  }

  @Override public void setErrorHandler(ErrorHandlerHelper errorHandler) {
    apiHelper.setErrorHandler(errorHandler);
  }
}
