package mk.petrovski.weathergurumvp.ui.welcome;

import io.reactivex.functions.Consumer;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.DataManager;
import mk.petrovski.weathergurumvp.data.local.preferences.AppPreferencesHelper;
import mk.petrovski.weathergurumvp.data.local.db.AppDbHelper;
import mk.petrovski.weathergurumvp.data.remote.AppApiHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class WelcomePresenter<V extends WelcomeMvpView> extends BasePresenter<V>
    implements WelcomeMvpPresenter<V> {

  @Inject public WelcomePresenter(CompositeDisposableHelper compositeDisposableHelper,
      DataManager dataManager) {
    super(compositeDisposableHelper, dataManager);
  }

  @Override public void checkForUserName() {
    String userName = getDataManager().getUserName();

    if (userName != null && !userName.equalsIgnoreCase("")) {
      getMvpView().showMainScreen();
    } else {
      getMvpView().startInit();
    }
  }

  @Override public void setUserName(String username) {
    if (username != null && username.trim().length() > 0) {
      // set username
      getDataManager().setUserName(username);

      // add default city
      if (getDataManager().getCitiesCount() == 0) {
        getCompositeDisposableHelper().addDisposable(getDataManager().addDefaultCity()
            .compose(getCompositeDisposableHelper().<Long>applySchedulers())
            .subscribe(new Consumer<Long>() {
              @Override public void accept(Long aLong) throws Exception {
                getMvpView().showMainScreen();
              }
            }));
      } else {
        getMvpView().showMainScreen();
      }
    } else {
      getMvpView().onError(R.string.error_username);
    }
  }
}
