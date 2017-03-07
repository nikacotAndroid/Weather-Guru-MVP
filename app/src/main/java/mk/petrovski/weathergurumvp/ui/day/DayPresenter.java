package mk.petrovski.weathergurumvp.ui.day;

import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.local.preferences.ApplicationPreferences;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.local.db.DbHelper;
import mk.petrovski.weathergurumvp.data.remote.ApiHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.BaseViewSubscriber;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class DayPresenter<V extends DayMvpView> extends BasePresenter<V>
    implements DayMvpPresenter<V> {

  @Inject public DayPresenter(DbHelper dbHelper, ApplicationPreferences applicationPreferences,
      ApiHelper apiHelper) {
    super(dbHelper, applicationPreferences, apiHelper);
  }

  @Override public void loadWeather() {

    CityDetailsModel selectedCity = getDbHelper().getSelectedCityModel();

    if (selectedCity != null) {
      getCompositeDisposableHelper().execute(
          getApiHelper().weatherApiRequest(selectedCity.getLatitude(), selectedCity.getLongitude()),
          new WeatherObserver(getMvpView()));
    } else {
      getMvpView().showEmptyView();
    }
  }

  private class WeatherObserver extends BaseViewSubscriber<DayMvpView, WeatherResponseModel> {

    public WeatherObserver(DayMvpView view) {
      super(view);
    }

    @Override public void onNext(WeatherResponseModel weatherResponseModel) {
      super.onNext(weatherResponseModel);

      getMvpView().showWeather(weatherResponseModel.getData().getWeather());
    }
  }
}
