package mk.petrovski.weathergurumvp.ui.day;

import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.DataManager;
import mk.petrovski.weathergurumvp.data.local.db.AppDbHelper;
import mk.petrovski.weathergurumvp.data.local.preferences.AppPreferencesHelper;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.AppApiHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.BaseViewSubscriber;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ErrorHandlerHelper;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherResponseModel;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class DayPresenter<V extends DayMvpView> extends BasePresenter<V>
    implements DayMvpPresenter<V> {

  @Inject public DayPresenter(CompositeDisposableHelper compositeDisposableHelper,
      DataManager dataManager) {
    super(compositeDisposableHelper, dataManager);
  }

  @Override public void loadWeather() {

    CityDetailsModel selectedCity = getDataManager().getSelectedCityModel();

    if (selectedCity != null) {
      getCompositeDisposableHelper().addDisposable(
          getDataManager().weatherApiRequest(selectedCity.getLatitude(),
              selectedCity.getLongitude())
              .compose(getCompositeDisposableHelper().<WeatherResponseModel>applySchedulers())
              .subscribeWith(
                  new WeatherObserver(getMvpView(), getDataManager().getErrorHandlerHelper())));
    } else {
      getMvpView().showEmptyView();
    }
  }

  public class WeatherObserver extends BaseViewSubscriber<DayMvpView, WeatherResponseModel> {

    public WeatherObserver(DayMvpView view, ErrorHandlerHelper errorHandlerHelper) {
      super(view, errorHandlerHelper);
    }

    @Override public void onNext(WeatherResponseModel weatherResponseModel) {
      super.onNext(weatherResponseModel);

      if (weatherResponseModel.getData().getWeather().isEmpty()) {
        getMvpView().showEmptyView();
      } else {
        getMvpView().showWeather(weatherResponseModel.getData().getWeather());
      }
    }
  }
}
