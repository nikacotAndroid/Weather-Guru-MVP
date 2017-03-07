package mk.petrovski.weathergurumvp.injection.module;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.injection.scope.WeatherGuruApplicationScope;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

@Module(includes = {
    ContextModule.class, PreferencesModule.class, NetworkModule.class, DatabaseModule.class
}) public class ApplicationModule {

  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides Application getApplication() {
    return application;
  }

  @Provides @WeatherGuruApplicationScope CalligraphyConfig getCalligraphyConfig() {
    return new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Roboto-Regular.ttf")
        .setFontAttrId(R.attr.fontPath)
        .build();
  }
}
