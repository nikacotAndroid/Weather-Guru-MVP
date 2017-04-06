package mk.petrovski.weathergurumvp.data;

import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import mk.petrovski.weathergurumvp.BuildConfig;
import mk.petrovski.weathergurumvp.TestModels;
import mk.petrovski.weathergurumvp.data.local.db.AppDbHelper;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.local.db.DaoMaster;
import mk.petrovski.weathergurumvp.data.local.db.DaoSession;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Nikola Petrovski on 4/3/2017.
 */

@RunWith(RobolectricTestRunner.class) @Config(constants = BuildConfig.class, sdk = 25)
public class DbHelperTest {

  private AppDbHelper dbHelper;
  private DaoSession daoSession;

  @Before public void setUp() {
    daoSession = new DaoMaster(new DaoMaster.DevOpenHelper(RuntimeEnvironment.application,
        "test").getWritableDb()).newSession();

    dbHelper = new AppDbHelper(daoSession);
  }

  @Test public void addDefaultCityAndGetItById() {
    dbHelper.addDefaultCity().subscribe();
    // check if the city is added
    assertEquals(1, dbHelper.getCitiesCount().intValue());

    CityDetailsModel defaultCity = dbHelper.getSelectedCityModel();

    // verify city by id
    TestObserver<CityDetailsModel> testSubscriber = new TestObserver<>();

    dbHelper.getCityById(defaultCity.getId()).subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertComplete();
    testSubscriber.assertValue(defaultCity);
  }

  @Test public void addNewCityCheckIfExistAndDelete() {
    LocationModel location = TestModels.newDbLocationModel(true, "test");
    dbHelper.addNewCity(location).subscribe();

    // check if the record is added and if exist
    assertEquals(1, dbHelper.getCitiesCount().intValue());
    assertTrue(dbHelper.isCityExist(location.getWeatherUrl()));

    //check if the actual record is selected
    CityDetailsModel selectedCity = dbHelper.getSelectedCityModel();
    assertEquals(selectedCity.getWeatherUrl(), location.getWeatherUrl());

    // check if record is delete
    dbHelper.deleteCity(selectedCity).subscribe();
    assertEquals(0, dbHelper.getCitiesCount().intValue());
  }

  @Test public void checkSelectionOfProperCity() {
    List<LocationModel> locations = TestModels.getDbLocations();

    dbHelper.addNewCity(locations.get(0)).subscribe();
    dbHelper.addNewCity(locations.get(1)).subscribe();
    dbHelper.addNewCity(locations.get(2)).subscribe();

    List<CityDetailsModel> cities = daoSession.getCityDetailsModelDao().queryBuilder().list();

    assertEquals(locations.size(), cities.size());

    // select first city
    dbHelper.selectFirstCity().subscribe();
    assertEquals(cities.get(0), dbHelper.getSelectedCityModel());

    // select second city and verify
    dbHelper.selectCity(cities.get(1), true).subscribe();
    assertEquals(cities.get(1), dbHelper.getSelectedCityModel());
  }

  @Test public void listCities() {
    TestObserver<List<CityDetailsModel>> listTestSubscriber = new TestObserver<>();
    dbHelper.getAllCities().subscribe(listTestSubscriber);

    listTestSubscriber.assertNoErrors();
    listTestSubscriber.assertComplete();
    listTestSubscriber.assertValueCount(1);
    listTestSubscriber.assertValue(new ArrayList<CityDetailsModel>());
  }
}
