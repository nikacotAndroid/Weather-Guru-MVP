package mk.petrovski.weathergurumvp.injection.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import mk.petrovski.weathergurumvp.data.local.db.AppDbHelper;
import mk.petrovski.weathergurumvp.data.local.db.DaoMaster;
import mk.petrovski.weathergurumvp.data.local.db.DaoSession;
import mk.petrovski.weathergurumvp.data.local.db.DbHelper;
import mk.petrovski.weathergurumvp.injection.qualifier.ApplicationContext;
import mk.petrovski.weathergurumvp.injection.scope.WeatherGuruApplicationScope;

/**
 * Created by Nikola Petrovski on 2/20/2017.
 */

@Module(includes = ContextModule.class) public class DatabaseModule {

  @Provides @WeatherGuruApplicationScope
  public DaoSession daoSession(@ApplicationContext Context context) {
    return new DaoMaster(new DaoMaster.DevOpenHelper(context, "test").getWritableDb()).newSession();
  }

  @Provides @WeatherGuruApplicationScope public DbHelper dbHelper(DaoSession daoSession) {
    return new AppDbHelper(daoSession);
  }
}
