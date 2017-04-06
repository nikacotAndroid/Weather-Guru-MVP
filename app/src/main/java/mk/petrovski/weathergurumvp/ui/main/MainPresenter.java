package mk.petrovski.weathergurumvp.ui.main;

import io.reactivex.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.DataManager;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
    implements MainMvpPresenter<V> {

  @Inject public MainPresenter(CompositeDisposableHelper compositeDisposableHelper,
      DataManager dataManager) {
    super(compositeDisposableHelper, dataManager);
  }

  @Override public void setDrawerCities() {
    getCompositeDisposableHelper().addDisposable(getDataManager().getAllCities()
        .compose(getCompositeDisposableHelper().<List<CityDetailsModel>>applySchedulers())
        .subscribe(new Consumer<List<CityDetailsModel>>() {
          @Override public void accept(List<CityDetailsModel> cityDetailsModels) throws Exception {
            if (cityDetailsModels != null && cityDetailsModels.size() > 0) {
              getMvpView().showCities(cityDetailsModels);
            } else {
              getMvpView().showEmptyView();
            }
          }
        }));
  }

  @Override public void setUserInfo() {
    getMvpView().setMenuUserName(getDataManager().getUserName());
  }

  @Override public void selectCity(CityDetailsModel city) {
    getCompositeDisposableHelper().addDisposable(getDataManager().selectCity(city, true)
        .compose(getCompositeDisposableHelper().<Boolean>applySchedulers())
        .subscribe(new Consumer<Boolean>() {
          @Override public void accept(Boolean aBoolean) throws Exception {
            getMvpView().onCitySelect();
            // refresh cities
            setDrawerCities();
          }
        }));
  }

  @Override public void setCurrentCityTitle() {
    CityDetailsModel selectedCity = getDataManager().getSelectedCityModel();

    if (selectedCity != null) {
      getMvpView().setCurrentCityTitle(selectedCity.getAreaName());
    } else {
      getMvpView().setCurrentCityTitle("No Locations");
    }
  }
}
