package mk.petrovski.weathergurumvp.data.local.preferences;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public interface BasePreferencesHelper {

  boolean getBooleanFromPrefs(String key);

  void setBooleanToPrefs(String key, boolean value);

  String getStringFromPrefs(String key);

  void setStringToPrefs(String key, String value);

  String getStringFromPrefs(String key, String defaultValue);

  void setLongToPrefs(String key, long value);

  long getLongFromPrefs(String key);

  int getIntFromPrefs(String key);

  void setIntToPrefs(String key, int value);
}
