package mk.petrovski.weathergurumvp.data.remote;

import io.reactivex.Observable;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.BuildConfig;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ErrorHandlerHelper;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.SearchApiResponseModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;

/**
 * Created by Nikola Petrovski on 2/23/2017.
 */

public class AppApiHelper implements ApiHelper {

  ApiInterface apiInterface;
  ErrorHandlerHelper errorHandlerHelper;

  final String format = "json";
  final String key = BuildConfig.WWO_API_KEY;

  @Inject public AppApiHelper(ApiInterface apiInterface, ErrorHandlerHelper errorHandlerHelper) {
    this.apiInterface = apiInterface;
    this.errorHandlerHelper = errorHandlerHelper;
  }

  @Override public ErrorHandlerHelper getErrorHandlerHelper() {
    return errorHandlerHelper;
  }

  @Override public void setErrorHandler(ErrorHandlerHelper errorHandler) {
    this.errorHandlerHelper = errorHandler;
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
