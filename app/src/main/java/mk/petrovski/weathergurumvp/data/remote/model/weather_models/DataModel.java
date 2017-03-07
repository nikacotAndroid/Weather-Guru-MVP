package mk.petrovski.weathergurumvp.data.remote.model.weather_models;

import java.util.List;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class DataModel {
  private List<WeatherModel> weather;

  public List<WeatherModel> getWeather() {
    return weather;
  }

  public void setWeather(List<WeatherModel> weather) {
    this.weather = weather;
  }
}
