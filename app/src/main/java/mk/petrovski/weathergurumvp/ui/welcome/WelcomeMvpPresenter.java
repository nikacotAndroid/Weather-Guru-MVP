package mk.petrovski.weathergurumvp.ui.welcome;

import mk.petrovski.weathergurumvp.ui.base.Presenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public interface WelcomeMvpPresenter<V extends WelcomeMvpView> extends Presenter<V> {

  void checkForUserName();

  void setUserName(String username);
}
