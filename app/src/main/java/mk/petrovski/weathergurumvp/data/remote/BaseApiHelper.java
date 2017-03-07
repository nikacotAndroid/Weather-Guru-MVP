package mk.petrovski.weathergurumvp.data.remote;

import io.reactivex.Observable;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.SearchApiResponseModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;

/**
 * Created by Nikola Petrovski on 2/23/2017.
 */

public interface BaseApiHelper {

  Observable<WeatherResponseModel> weatherApiRequest(String latitude, String longitude);

  Observable<SearchApiResponseModel> locationsApiRequest(String query);
}
