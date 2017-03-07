package mk.petrovski.weathergurumvp.injection.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import mk.petrovski.weathergurumvp.data.local.preferences.ApplicationPreferences;
import mk.petrovski.weathergurumvp.data.local.preferences.PreferencesHelper;
import mk.petrovski.weathergurumvp.injection.qualifier.ApplicationContext;
import mk.petrovski.weathergurumvp.injection.scope.WeatherGuruApplicationScope;

/**
 * Created by Nikola Petrovski on 3/1/2017.
 */
@Module(includes = ContextModule.class) public class PreferencesModule {

  @Provides @WeatherGuruApplicationScope PreferencesHelper getPreferencesHelper(
      @ApplicationContext Context context) {
    return new PreferencesHelper(context);
  }

  @Provides @WeatherGuruApplicationScope ApplicationPreferences getApplicationPreferences(
      PreferencesHelper preferencesHelper) {
    return new ApplicationPreferences(preferencesHelper);
  }
}
