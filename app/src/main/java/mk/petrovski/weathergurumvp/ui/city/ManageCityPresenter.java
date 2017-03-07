package mk.petrovski.weathergurumvp.ui.city;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.local.preferences.ApplicationPreferences;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.local.db.DbHelper;
import mk.petrovski.weathergurumvp.data.remote.ApiHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.BaseViewSubscriber;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.SearchApiResponseModel;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class ManageCityPresenter<V extends ManageCityMvpView> extends BasePresenter<V>
    implements ManageCityMvpPresenter<V> {

  @Inject
  public ManageCityPresenter(DbHelper dbHelper, ApplicationPreferences applicationPreferences,
      ApiHelper apiHelper) {
    super(dbHelper, applicationPreferences, apiHelper);
  }

  @Override public void loadAutocompleteCities(Observable<String> cityQueryObservable) {
    Observable<SearchApiResponseModel> a =
        cityQueryObservable.flatMap(new Function<String, Observable<SearchApiResponseModel>>() {
          @Override public Observable<SearchApiResponseModel> apply(String s) throws Exception {
            return getApiHelper().locationsApiRequest(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
          }
        });

    getCompositeDisposableHelper().execute(a, new CityObserver(getMvpView()));
  }

  @Override public void loadCities() {
    getCompositeDisposableHelper().execute(getDbHelper().getAllCities(),
        new Consumer<List<CityDetailsModel>>() {
          @Override public void accept(List<CityDetailsModel> cityDetailsModels) throws Exception {
            if (cityDetailsModels != null && cityDetailsModels.size() > 0) {
              getMvpView().showCities(cityDetailsModels);
            } else {
              getMvpView().showEmptyView();
            }
          }
        });
  }

  @Override public void addNewLocation(LocationModel location) {
    if (getDbHelper().isCityExist(location.getWeatherUrl())) {
      getMvpView().onError(R.string.error_multiple_city);
    } else {
      getCompositeDisposableHelper().execute(getDbHelper().addNewCity(location),
          new Consumer<Long>() {
            @Override public void accept(Long cityId) throws Exception {
              getCompositeDisposableHelper().execute(getDbHelper().getCityById(cityId),
                  new Consumer<CityDetailsModel>() {
                    @Override public void accept(CityDetailsModel cityDetailsModel)
                        throws Exception {
                      getMvpView().onCityAdded(cityDetailsModel);
                    }
                  });
            }
          });
    }
  }

  @Override public void deleteLocation(final int position, final CityDetailsModel city) {
    getCompositeDisposableHelper().execute(getDbHelper().deleteCity(city), new Consumer<Boolean>() {
      @Override public void accept(Boolean aBoolean) throws Exception {
        // check if the current city is selected and select another one
        if (city.getIsSelected()) {
          getCompositeDisposableHelper().execute(getDbHelper().selectFirstCity(),
              new Consumer<Boolean>() {
                @Override public void accept(Boolean aBoolean) throws Exception {
                  getMvpView().onCityDelete(position);
                }
              });
        } else {
          getMvpView().onCityDelete(position);
        }
      }
    });
  }

  private class CityObserver extends BaseViewSubscriber<ManageCityMvpView, SearchApiResponseModel> {

    public CityObserver(ManageCityMvpView view) {
      super(view);
    }

    @Override protected boolean shouldShowLoading() {
      return false;
    }

    @Override public void onNext(SearchApiResponseModel locationModels) {
      super.onNext(locationModels);

      if (locationModels != null
          && locationModels.getSearch_api() != null
          && locationModels.getSearch_api().getResult() != null
          && locationModels.getSearch_api().getResult().size() > 0) {

        getMvpView().showAutocompleteCities(locationModels.getSearch_api().getResult(),
            locationModels.getSearch_api()
                .getResult()
                .toArray(new LocationModel[locationModels.getSearch_api().getResult().size()]));
      }
    }
  }
}
