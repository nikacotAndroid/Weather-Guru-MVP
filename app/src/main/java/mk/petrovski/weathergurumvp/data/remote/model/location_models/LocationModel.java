package mk.petrovski.weathergurumvp.data.remote.model.location_models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class LocationModel {
  private List<ValueModel> areaName;
  private List<ValueModel> country;
  private String latitude;
  private String longitude;
  private String population;
  private List<ValueModel> region;
  private List<ValueModel> weatherUrl;
  private int id;
  private boolean isSelected;

  public LocationModel() {
  }

  public LocationModel(String areaName, String country, String latitude, String longitude,
      String population, String region, String weatherUrl, boolean isSelected) {
    this.areaName = getValueModelFromString(areaName);
    this.country = getValueModelFromString(country);
    this.latitude = latitude;
    this.longitude = longitude;
    this.population = population;
    this.region = getValueModelFromString(region);
    this.weatherUrl = getValueModelFromString(weatherUrl);
    this.isSelected = isSelected;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setIsSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }

  public String getAreaName() {
    return areaName.get(0).getValue();
  }

  public void setAreaName(String areaName) {
    this.areaName = getValueModelFromString(areaName);
  }

  public String getCountry() {
    return country.get(0).getValue();
  }

  public void setCountry(String country) {
    this.country = getValueModelFromString(country);
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getPopulation() {
    return population;
  }

  public void setPopulation(String population) {
    this.population = population;
  }

  public String getRegion() {
    return region.get(0).getValue();
  }

  public void setRegion(String region) {
    this.region = getValueModelFromString(region);
  }

  public String getWeatherUrl() {
    return weatherUrl.get(0).getValue();
  }

  public void setWeatherUrl(String weatherUrl) {
    this.weatherUrl = getValueModelFromString(weatherUrl);
  }

  private List<ValueModel> getValueModelFromString(String value) {
    List<ValueModel> valueList = new ArrayList<>();
    valueList.add(new ValueModel(value));
    return valueList;
  }
}
