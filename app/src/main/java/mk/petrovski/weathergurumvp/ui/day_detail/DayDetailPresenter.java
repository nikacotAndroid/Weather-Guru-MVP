package mk.petrovski.weathergurumvp.ui.day_detail;

import io.reactivex.functions.Consumer;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.local.preferences.ApplicationPreferences;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.local.db.DbHelper;
import mk.petrovski.weathergurumvp.data.remote.ApiHelper;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class DayDetailPresenter<V extends DayDetailMvpView> extends BasePresenter<V>
    implements DayDetailMvpPresenter<V> {

  @Inject
  public DayDetailPresenter(DbHelper dbHelper, ApplicationPreferences applicationPreferences,
      ApiHelper apiHelper) {
    super(dbHelper, applicationPreferences, apiHelper);
  }

  @Override public void setCurrentCity() {
    getCompositeDisposableHelper().execute(getDbHelper().getSelectedCity(),
        new Consumer<CityDetailsModel>() {
          @Override public void accept(CityDetailsModel cityDetailsModel) throws Exception {
            getMvpView().setCurrentCityName(cityDetailsModel.getAreaName());
          }
        });
  }
}
