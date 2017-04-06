package mk.petrovski.weathergurumvp.data.local.preferences;

/**
 * Created by Nikola Petrovski on 3/30/2017.
 */

public interface PreferencesHelper {

  void setLoggedIn();

  boolean isLoggedIn();

  void setUserName(String userName);

  String getUserName();
}
