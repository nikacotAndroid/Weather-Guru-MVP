package mk.petrovski.weathergurumvp.injection.module;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;

/**
 * Created by Nikola Petrovski on 2/23/2017.
 */

@Module public class RxModule {

  @Provides CompositeDisposable getCompositeDisposable() {
    return new CompositeDisposable();
  }

  @Provides CompositeDisposableHelper getCompositeDisposableHelper(
      CompositeDisposable compositeDisposable) {
    return new CompositeDisposableHelper(compositeDisposable);
  }
}
