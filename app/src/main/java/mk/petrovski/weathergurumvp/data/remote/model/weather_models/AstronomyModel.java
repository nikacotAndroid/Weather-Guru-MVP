package mk.petrovski.weathergurumvp.data.remote.model.weather_models;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class AstronomyModel {
  private String moonrise;
  private String moonset;
  private String sunrise;
  private String sunset;

  public String getMoonrise() {
    return moonrise;
  }

  public void setMoonrise(String moonrise) {
    this.moonrise = moonrise;
  }

  public String getMoonset() {
    return moonset;
  }

  public void setMoonset(String moonset) {
    this.moonset = moonset;
  }

  public String getSunrise() {
    return sunrise;
  }

  public void setSunrise(String sunrise) {
    this.sunrise = sunrise;
  }

  public String getSunset() {
    return sunset;
  }

  public void setSunset(String sunset) {
    this.sunset = sunset;
  }
}
