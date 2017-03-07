package mk.petrovski.weathergurumvp.data.remote;

import io.reactivex.Observable;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.BuildConfig;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.SearchApiResponseModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;

/**
 * Created by Nikola Petrovski on 2/23/2017.
 */

public class ApiHelper implements BaseApiHelper {

  ApiInterface apiInterface;

  final String format = "json";
  final String key = BuildConfig.WWO_API_KEY;

  @Inject public ApiHelper(ApiInterface apiInterface) {
    this.apiInterface = apiInterface;
  }

  @Override
  public Observable<WeatherResponseModel> weatherApiRequest(String latitude, String longitude) {
    String days = "5";
    String tp = "24";
    String cc = "no";
    String location = String.format("%s,%s", latitude, longitude);

    return apiInterface.checkWeather(location, key, format, days, tp, cc);
  }

  @Override public Observable<SearchApiResponseModel> locationsApiRequest(String query) {
    return apiInterface.getLocations(query, key, format);
  }
}
