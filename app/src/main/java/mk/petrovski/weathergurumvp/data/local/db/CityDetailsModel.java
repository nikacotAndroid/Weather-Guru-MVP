package mk.petrovski.weathergurumvp.data.local.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Nikola Petrovski on 2/20/2017.
 */

@Entity(nameInDb = "city") public class CityDetailsModel {

  @Id(autoincrement = true) private Long id;

  private String areaName;
  private String country;
  private String latitude;
  private String longitude;
  private String population;
  private String region;
  private String weatherUrl;
  private Boolean isSelected;

  @Generated(hash = 959524249)
  public CityDetailsModel(Long id, String areaName, String country, String latitude,
      String longitude, String population, String region, String weatherUrl, Boolean isSelected) {
    this.id = id;
    this.areaName = areaName;
    this.country = country;
    this.latitude = latitude;
    this.longitude = longitude;
    this.population = population;
    this.region = region;
    this.weatherUrl = weatherUrl;
    this.isSelected = isSelected;
  }

  @Generated(hash = 696392665) public CityDetailsModel() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
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
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getWeatherUrl() {
    return weatherUrl;
  }

  public void setWeatherUrl(String weatherUrl) {
    this.weatherUrl = weatherUrl;
  }

  public Boolean getSelected() {
    return isSelected;
  }

  public void setSelected(Boolean selected) {
    isSelected = selected;
  }

  public Boolean getIsSelected() {
    return this.isSelected;
  }

  public void setIsSelected(Boolean isSelected) {
    this.isSelected = isSelected;
  }
}
