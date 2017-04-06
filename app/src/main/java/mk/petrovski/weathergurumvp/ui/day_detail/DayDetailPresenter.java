package mk.petrovski.weathergurumvp.ui.day_detail;

import io.reactivex.functions.Consumer;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.DataManager;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class DayDetailPresenter<V extends DayDetailMvpView> extends BasePresenter<V>
    implements DayDetailMvpPresenter<V> {

  @Inject public DayDetailPresenter(CompositeDisposableHelper compositeDisposableHelper,
      DataManager dataManager) {
    super(compositeDisposableHelper, dataManager);
  }

  @Override public void setCurrentCity() {
    getCompositeDisposableHelper().addDisposable(getDataManager().getSelectedCity()
        .compose(getCompositeDisposableHelper().<CityDetailsModel>applySchedulers())
        .subscribe(new Consumer<CityDetailsModel>() {
          @Override public void accept(CityDetailsModel cityDetailsModel) throws Exception {
            getMvpView().setCurrentCityName(cityDetailsModel.getAreaName());
          }
        }));
  }
}
