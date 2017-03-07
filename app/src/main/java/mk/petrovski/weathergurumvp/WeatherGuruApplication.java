package mk.petrovski.weathergurumvp;

import android.app.Application;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ErrorHandlerHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.error.UncheckedException;
import mk.petrovski.weathergurumvp.injection.component.ApplicationComponent;
import mk.petrovski.weathergurumvp.injection.component.DaggerApplicationComponent;
import mk.petrovski.weathergurumvp.injection.module.ApplicationModule;
import mk.petrovski.weathergurumvp.injection.module.ContextModule;
import mk.petrovski.weathergurumvp.injection.module.DatabaseModule;
import mk.petrovski.weathergurumvp.injection.module.NetworkModule;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Nikola Petrovski on 2/10/2017.
 */

public class WeatherGuruApplication extends Application {

  @Inject CalligraphyConfig calligraphyConfig;

  public static ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();

    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .contextModule(new ContextModule(this))
        .databaseModule(new DatabaseModule())
        .networkModule(new NetworkModule())
        .build();

    applicationComponent.inject(this);

    CalligraphyConfig.initDefault(calligraphyConfig);
    Timber.plant(new Timber.DebugTree());
  }

  public static ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  public void setApplicationComponent(ApplicationComponent applicationComponent) {
    WeatherGuruApplication.applicationComponent = applicationComponent;
  }
}