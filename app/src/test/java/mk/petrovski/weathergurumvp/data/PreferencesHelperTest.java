package mk.petrovski.weathergurumvp.data;

import mk.petrovski.weathergurumvp.BuildConfig;
import mk.petrovski.weathergurumvp.data.local.preferences.AppPreferencesHelper;
import mk.petrovski.weathergurumvp.data.local.preferences.CommonPreferencesHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nikola Petrovski on 4/3/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class PreferencesHelperTest {

  private AppPreferencesHelper appPreferencesHelper;

  @Before public void setUp(){
    CommonPreferencesHelper commonPreferencesHelper = new CommonPreferencesHelper(
        RuntimeEnvironment.application);

    appPreferencesHelper = new AppPreferencesHelper(commonPreferencesHelper);
  }

  @Test public void putAndGetUserName(){
    String userName = "Nikola";
    appPreferencesHelper.setUserName(userName);

    assertEquals(userName, appPreferencesHelper.getUserName());
  }

  @Test public void putAndGetLoginInfo(){
    appPreferencesHelper.setLoggedIn();

    assertTrue(appPreferencesHelper.isLoggedIn());
  }
}
