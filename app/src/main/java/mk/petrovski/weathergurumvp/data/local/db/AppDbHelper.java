package mk.petrovski.weathergurumvp.data.local.db;

import io.reactivex.Observable;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;

/**
 * Created by Nikola Petrovski on 2/20/2017.
 */

public class AppDbHelper implements DbHelper {

  DaoSession daoSession;

  @Inject public AppDbHelper(DaoSession daoSession) {
    this.daoSession = daoSession;
  }

  @Override public Observable<Long> addNewCity(final LocationModel location) {
    return Observable.fromCallable(new Callable<Long>() {
      @Override public Long call() throws Exception {

        // Set selected if no data in DB
        location.setIsSelected(getCitiesCount() == 0);

        // create db model
        CityDetailsModel city = new CityDetailsModel();
        city.setAreaName(location.getAreaName());
        city.setCountry(location.getCountry());
        city.setRegion(location.getRegion());
        city.setLatitude(location.getLatitude());
        city.setLongitude(location.getLongitude());
        city.setPopulation(location.getPopulation());
        city.setWeatherUrl(location.getWeatherUrl());
        city.setIsSelected(location.isSelected());

        return daoSession.getCityDetailsModelDao().insert(city);

      }
    });
  }

  @Override public Observable<Long> addDefaultCity() {
    return Observable.fromCallable(new Callable<Long>() {
      @Override public Long call() throws Exception {

        // create default model. Default values from API
        CityDetailsModel city = new CityDetailsModel();
        city.setAreaName("New York");
        city.setCountry("United States of America");
        city.setRegion("New York");
        city.setLatitude("40.714");
        city.setLongitude("-74.006");
        city.setPopulation("8107916");
        city.setWeatherUrl("http://www.worldweatheronline.com/v2/weather.aspx?q=40.7142,-74.0064");
        city.setIsSelected(true);

        return daoSession.getCityDetailsModelDao().insert(city);
      }
    });
  }

  @Override public Observable<List<CityDetailsModel>> getAllCities() {
    return Observable.fromCallable(new Callable<List<CityDetailsModel>>() {
      @Override public List<CityDetailsModel> call() throws Exception {
        return daoSession.getCityDetailsModelDao()
            .queryBuilder()
            .orderDesc(CityDetailsModelDao.Properties.Id)
            .list();
      }
    });
  }

  @Override public Observable<Boolean> deleteCity(final CityDetailsModel city) {
    return Observable.fromCallable(new Callable<Boolean>() {
      @Override public Boolean call() throws Exception {
        daoSession.getCityDetailsModelDao().deleteByKey(city.getId());
        return true;
      }
    });
  }

  @Override public Observable<CityDetailsModel> getSelectedCity() {
    return Observable.fromCallable(new Callable<CityDetailsModel>() {
      @Override public CityDetailsModel call() throws Exception {
        return daoSession.getCityDetailsModelDao()
            .queryBuilder()
            .where(CityDetailsModelDao.Properties.IsSelected.eq(true))
            .limit(1)
            .unique();
      }
    });
  }

  @Override public Observable<CityDetailsModel> getCityById(final Long id) {
    return Observable.fromCallable(new Callable<CityDetailsModel>() {
      @Override public CityDetailsModel call() throws Exception {
        return daoSession.getCityDetailsModelDao()
            .queryBuilder()
            .where(CityDetailsModelDao.Properties.Id.eq(id))
            .limit(1)
            .unique();
      }
    });
  }

  @Override
  public Observable<Boolean> selectCity(final CityDetailsModel city, final boolean isSelected) {
    return Observable.fromCallable(new Callable<Boolean>() {
      @Override public Boolean call() throws Exception {

        // TODO check if there is another way
        // set select to false before selecting proper city. We want just ONE city selected
        for (CityDetailsModel city : daoSession.getCityDetailsModelDao().queryBuilder().list()) {
          city.setIsSelected(false);
          daoSession.getCityDetailsModelDao().update(city);
        }

        city.setIsSelected(isSelected);
        daoSession.getCityDetailsModelDao().update(city);
        return true;
      }
    });
  }

  @Override public Observable<Boolean> selectFirstCity() {
    return Observable.fromCallable(new Callable<Boolean>() {
      @Override public Boolean call() throws Exception {
        // select the first city
        if (getCitiesCount() > 0) {
          CityDetailsModel firstCity =
              daoSession.getCityDetailsModelDao().queryBuilder().list().get(0);
          firstCity.setIsSelected(true);
          daoSession.getCityDetailsModelDao().update(firstCity);
        }
        return true;
      }
    });
  }

  @Override public CityDetailsModel getSelectedCityModel() {
    return daoSession.getCityDetailsModelDao()
        .queryBuilder()
        .where(CityDetailsModelDao.Properties.IsSelected.eq(true))
        .unique();
  }

  @Override public Long getCitiesCount() {
    return daoSession.getCityDetailsModelDao().count();
  }

  @Override public Boolean isCityExist(String weatherUrl) {
    return daoSession.getCityDetailsModelDao()
        .queryBuilder()
        .where(CityDetailsModelDao.Properties.WeatherUrl.eq(weatherUrl))
        .count() > 0;
  }
}
