package mk.petrovski.weathergurumvp.data.local.preferences;

import javax.inject.Inject;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class AppPreferencesHelper implements PreferencesHelper {

  private static final String PREF_KEY_IS_LOGGED_IN = "PREF_KEY_IS_LOGGED_IN";
  private static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME_VALUE";

  private CommonPreferencesHelper commonPreferencesHelper;

  @Inject public AppPreferencesHelper(CommonPreferencesHelper commonPreferencesHelper) {
    this.commonPreferencesHelper = commonPreferencesHelper;
  }

  @Override public void setLoggedIn() {
    commonPreferencesHelper.setBooleanToPrefs(PREF_KEY_IS_LOGGED_IN, true);
  }

  @Override public boolean isLoggedIn() {
    return commonPreferencesHelper.getBooleanFromPrefs(PREF_KEY_IS_LOGGED_IN);
  }

  @Override public void setUserName(String userName) {
    commonPreferencesHelper.setStringToPrefs(PREF_KEY_USER_NAME, userName);
  }

  @Override public String getUserName() {
    return commonPreferencesHelper.getStringFromPrefs(PREF_KEY_USER_NAME);
  }
}
