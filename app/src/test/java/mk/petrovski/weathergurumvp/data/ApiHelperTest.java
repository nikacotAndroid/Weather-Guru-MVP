package mk.petrovski.weathergurumvp.data;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import mk.petrovski.weathergurumvp.TestModels;
import mk.petrovski.weathergurumvp.data.remote.ApiInterface;
import mk.petrovski.weathergurumvp.data.remote.AppApiHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ErrorHandlerHelper;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.SearchApiResponseModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Nikola Petrovski on 4/3/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class ApiHelperTest {

  @Mock ApiInterface apiInterface;
  @Mock ErrorHandlerHelper errorHandlerHelper;

  private AppApiHelper appApiHelper;

  @Before public void setUp() {
    appApiHelper = new AppApiHelper(apiInterface, errorHandlerHelper);
  }

  @Test public void loadWeather() {
    WeatherResponseModel responseModel = TestModels.getWeatherResponseModel(5);

    when(apiInterface.checkWeather(anyString(), anyString(), anyString(), anyString(), anyString(),
        anyString())).thenReturn(Observable.just(responseModel));
    TestObserver<WeatherResponseModel> testSubscriber = new TestObserver<>();

    appApiHelper.weatherApiRequest("test", "test").subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertComplete();
    testSubscriber.assertValueCount(1);
    testSubscriber.assertValue(responseModel);
  }

  @Test public void loadCities() {
    SearchApiResponseModel responseModel = TestModels.getSearchResponseModel(5);

    when(apiInterface.getLocations(anyString(), anyString(), anyString())).thenReturn(
        Observable.just(responseModel));
    TestObserver<SearchApiResponseModel> testSubscriber = new TestObserver<>();

    appApiHelper.locationsApiRequest("test").subscribe(testSubscriber);

    testSubscriber.assertNoErrors();
    testSubscriber.assertComplete();
    testSubscriber.assertValueCount(1);
    testSubscriber.assertValue(responseModel);
  }
}
