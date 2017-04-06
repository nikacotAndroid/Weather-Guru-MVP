package mk.petrovski.weathergurumvp.data.remote.helper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.utils.reactive.BaseSchedulerProvider;

/**
 * Created by Nikola Petrovski on 2/22/2017.
 */

public class CompositeDisposableHelper {

  public CompositeDisposable disposables;
  public BaseSchedulerProvider schedulerProvider;

  @Inject public CompositeDisposableHelper(CompositeDisposable disposables,
      BaseSchedulerProvider schedulerProvider) {
    this.disposables = disposables;
    this.schedulerProvider = schedulerProvider;
  }

  private final ObservableTransformer schedulersTransformer = new ObservableTransformer() {
    @Override public ObservableSource apply(Observable upstream) {
      return upstream.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui());
    }
  };

  public <T> ObservableTransformer<T, T> applySchedulers() {
    return (ObservableTransformer<T, T>) schedulersTransformer;
  }

  public void addDisposable(Disposable disposable) {
    disposables.add(disposable);
  }

  public void dispose() {
    if (!disposables.isDisposed()) {
      disposables.dispose();
    }
  }

  public BaseSchedulerProvider getSchedulerProvider() {
    return schedulerProvider;
  }
}
