package mk.petrovski.weathergurumvp.data.remote;

import io.reactivex.Observable;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.SearchApiResponseModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public interface ApiInterface {

  @GET(ApiConsts.LOCATION_SEARCH_API_URL) Observable<SearchApiResponseModel> getLocations(
      @Query("query") String query, @Query("key") String key, @Query("format") String format);

  @GET(ApiConsts.WEATHER_API_URL) Observable<WeatherResponseModel> checkWeather(
      @Query("q") String query, @Query("key") String key, @Query("format") String format,
      @Query("num_of_days") String num_of_days, @Query("tp") String tp, @Query("cc") String cc);
}
