package mk.petrovski.weathergurumvp.injection.component;

import android.content.Context;
import dagger.Component;
import mk.petrovski.weathergurumvp.WeatherGuruApplication;
import mk.petrovski.weathergurumvp.data.DataManager;
import mk.petrovski.weathergurumvp.injection.module.ApplicationModule;
import mk.petrovski.weathergurumvp.injection.qualifier.ApplicationContext;
import mk.petrovski.weathergurumvp.injection.scope.WeatherGuruApplicationScope;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

@WeatherGuruApplicationScope @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  void inject(WeatherGuruApplication application);

  @ApplicationContext Context context();

  DataManager getDataManager();
}
