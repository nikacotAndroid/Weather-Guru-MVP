package mk.petrovski.weathergurumvp.injection.module;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;
import mk.petrovski.weathergurumvp.utils.reactive.SchedulerProvider;

/**
 * Created by Nikola Petrovski on 2/23/2017.
 */

@Module public class RxModule {

  @Provides CompositeDisposable getCompositeDisposable() {
    return new CompositeDisposable();
  }

  @Provides SchedulerProvider getSchedulerProvider() {
    return new SchedulerProvider();
  }

  @Provides CompositeDisposableHelper getCompositeDisposableHelper(
      CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider) {
    return new CompositeDisposableHelper(compositeDisposable, schedulerProvider);
  }
}
