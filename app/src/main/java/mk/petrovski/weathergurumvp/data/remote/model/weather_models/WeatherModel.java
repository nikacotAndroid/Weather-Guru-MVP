package mk.petrovski.weathergurumvp.data.remote.model.weather_models;

import java.util.List;
import mk.petrovski.weathergurumvp.utils.DateUtils;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class WeatherModel {
  private List<AstronomyModel> astronomy;
  private String date;
  private List<HourlyModel> hourly;
  private String maxtempC;
  private String maxtempF;
  private String mintempC;
  private String mintempF;
  private String uvIndex;

  public String getShortMonthDate() {
    return DateUtils.getMonthName(date);
  }

  public String getForrmatedDate() {
    return DateUtils.getDayName(date);
  }

  public AstronomyModel getAstronomy() {
    return astronomy.get(0);
  }

  public void setAstronomy(List<AstronomyModel> astronomy) {
    this.astronomy = astronomy;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public HourlyModel getHourly() {
    return hourly.get(0);
  }

  public void setHourly(List<HourlyModel> hourly) {
    this.hourly = hourly;
  }

  public String getMaxtempC() {
    return maxtempC;
  }

  public void setMaxtempC(String maxtempC) {
    this.maxtempC = maxtempC;
  }

  public String getMaxtempF() {
    return maxtempF;
  }

  public void setMaxtempF(String maxtempF) {
    this.maxtempF = maxtempF;
  }

  public String getMintempC() {
    return mintempC;
  }

  public void setMintempC(String mintempC) {
    this.mintempC = mintempC;
  }

  public String getMintempF() {
    return mintempF;
  }

  public void setMintempF(String mintempF) {
    this.mintempF = mintempF;
  }

  public String getUvIndex() {
    return uvIndex;
  }

  public void setUvIndex(String uvIndex) {
    this.uvIndex = uvIndex;
  }
}
