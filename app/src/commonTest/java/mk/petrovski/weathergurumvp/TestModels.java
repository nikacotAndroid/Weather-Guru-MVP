package mk.petrovski.weathergurumvp;

import java.util.ArrayList;
import java.util.List;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.ResultModel;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.SearchApiResponseModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.DataModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;

/**
 * Created by Nikola Petrovski on 3/31/2017.
 */

public class TestModels {

  public static WeatherResponseModel getWeatherResponseModel(int weatherListSize) {
    List<WeatherModel> list = new ArrayList<>();
    for (int i = 0; i < weatherListSize; i++) {
      list.add(newWeatherModel());
    }

    WeatherResponseModel responseModel = new WeatherResponseModel();
    DataModel dataModel = new DataModel();

    dataModel.setWeather(list);
    responseModel.setData(dataModel);

    return responseModel;
  }

  public static SearchApiResponseModel getSearchResponseModel(int searchListSize) {
    List<LocationModel> list = new ArrayList<>();
    for (int i = 0; i < searchListSize; i++) {
      list.add(newLocationModel());
    }

    ResultModel resultModel = new ResultModel();
    resultModel.setResult(list);

    SearchApiResponseModel searchApiResponseModel = new SearchApiResponseModel();
    searchApiResponseModel.setSearch_api(resultModel);

    return searchApiResponseModel;
  }

  public static List<CityDetailsModel> getCities(int citiesListSize) {
    List<CityDetailsModel> list = new ArrayList<>();
    for (int i = 0; i < citiesListSize; i++) {
      list.add(newCityModel());
    }

    return list;
  }

  public static CityDetailsModel newCityModel() {
    CityDetailsModel cityModel = new CityDetailsModel();
    cityModel.setLongitude("");
    cityModel.setLatitude("");
    cityModel.setAreaName("NY");

    return cityModel;
  }

  public static LocationModel newLocationModel() {
    LocationModel locationModel = new LocationModel();
    locationModel.setWeatherUrl("test");
    locationModel.setAreaName("NY");

    return locationModel;
  }

  public static WeatherModel newWeatherModel() {
    return new WeatherModel();
  }

  // db
  public static List<LocationModel> getDbLocations() {
    List<LocationModel> locations = new ArrayList<>();
    locations.add(newDbLocationModel(false, "test1"));
    locations.add(newDbLocationModel(false, "test2"));
    locations.add(newDbLocationModel(false, "test3"));

    return locations;
  }

  public static LocationModel newDbLocationModel(boolean selected, String weatherUrl) {
    LocationModel location = new LocationModel();
    location.setAreaName("Test");
    location.setCountry("Test");
    location.setRegion("Test");
    location.setLatitude("0");
    location.setLongitude("0");
    location.setPopulation("0");
    location.setWeatherUrl(weatherUrl);
    location.setIsSelected(selected);

    return location;
  }
}
