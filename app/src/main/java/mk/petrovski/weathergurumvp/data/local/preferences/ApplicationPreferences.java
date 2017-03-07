package mk.petrovski.weathergurumvp.data.local.preferences;

import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.local.preferences.PreferencesHelper;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class ApplicationPreferences {

  private static final String PREF_KEY_IS_LOGGED_IN = "PREF_KEY_IS_LOGGED_IN";
  private static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME_VALUE";

  private PreferencesHelper preferencesHelper;

  @Inject public ApplicationPreferences(PreferencesHelper preferencesHelper) {
    this.preferencesHelper = preferencesHelper;
  }

  public void setLoggedIn() {
    preferencesHelper.setBooleanToPrefs(PREF_KEY_IS_LOGGED_IN, true);
  }

  public boolean isLoggedIn() {
    return preferencesHelper.getBooleanFromPrefs(PREF_KEY_IS_LOGGED_IN);
  }

  public void setUserName(String userName) {
    preferencesHelper.setStringToPrefs(PREF_KEY_USER_NAME, userName);
  }

  public String getUserName() {
    return preferencesHelper.getStringFromPrefs(PREF_KEY_USER_NAME);
  }
}
