package mk.petrovski.weathergurumvp.data.remote;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class ApiConsts {
  /**
   * Base URL must end with /, because of the Retrofit
   */
  private static final String BASE = "http://api.worldweatheronline.com/";

  private static final String BASE_VERSION = "free/v2/";
  public static final String BASE_URL = BASE + BASE_VERSION;

  /**
   * API URLs
   */
  public static final String LOCATION_SEARCH_API_URL = BASE_URL + "search.ashx";
  public static final String WEATHER_API_URL = BASE_URL + "weather.ashx";
}
