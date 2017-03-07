package mk.petrovski.weathergurumvp.injection.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import mk.petrovski.weathergurumvp.injection.qualifier.ApplicationContext;
import mk.petrovski.weathergurumvp.injection.scope.WeatherGuruApplicationScope;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

@Module public class ContextModule {
  private final Context context;

  public ContextModule(Context context) {
    this.context = context.getApplicationContext();
  }

  @Provides @WeatherGuruApplicationScope @ApplicationContext
  public Context getApplicationContext() {
    return context;
  }
}
