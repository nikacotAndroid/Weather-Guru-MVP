package mk.petrovski.weathergurumvp.ui.welcome;

import io.reactivex.functions.Consumer;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.local.preferences.ApplicationPreferences;
import mk.petrovski.weathergurumvp.data.local.db.DbHelper;
import mk.petrovski.weathergurumvp.data.remote.ApiHelper;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class WelcomePresenter<V extends WelcomeMvpView> extends BasePresenter<V>
    implements WelcomeMvpPresenter<V> {

  @Inject public WelcomePresenter(DbHelper dbHelper, ApplicationPreferences applicationPreferences,
      ApiHelper apiHelper) {
    super(dbHelper, applicationPreferences, apiHelper);
  }

  @Override public void checkForUserName() {
    String userName = getApplicationPreferences().getUserName();

    if (userName != null && !userName.equalsIgnoreCase("")) {
      getMvpView().showMainScreen();
    } else {
      getMvpView().startInit();
    }
  }

  @Override public void setUserName(String username) {
    if (username != null && username.trim().length() > 0) {
      // set username
      getApplicationPreferences().setUserName(username);

      // add default city
      if (getDbHelper().getCitiesCount() == 0) {
        getCompositeDisposableHelper().execute(getDbHelper().addDefaultCity(),
            new Consumer<Long>() {
              @Override public void accept(Long aLong) throws Exception {
                getMvpView().showMainScreen();
              }
            });
      } else {
        getMvpView().showMainScreen();
      }
    } else {
      getMvpView().onError(R.string.error_username);
    }
  }
}
