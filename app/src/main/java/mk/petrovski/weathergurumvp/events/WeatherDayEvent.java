package mk.petrovski.weathergurumvp.events;

import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherModel;

/**
 * Created by Nikola Petrovski on 2/24/2017.
 */

public class WeatherDayEvent {

  private final WeatherModel weatherModel;

  public WeatherDayEvent(WeatherModel weatherModel) {
    this.weatherModel = weatherModel;
  }

  public WeatherModel getWeatherModel() {
    return weatherModel;
  }
}
