package mk.petrovski.weathergurumvp.data.remote.helper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

/**
 * Created by Nikola Petrovski on 2/22/2017.
 */

public class CompositeDisposableHelper {

  private CompositeDisposable disposables;

  @Inject public CompositeDisposableHelper(CompositeDisposable disposables) {
    this.disposables = disposables;
  }

  public <T> void execute(Observable<T> observable, DisposableObserver<T> observer) {
    addDisposable(observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(observer));
  }

  public <T> void execute(Observable<T> observable, Consumer<T> consumer) {
    addDisposable(observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(consumer));
  }

  public void dispose() {
    if (!disposables.isDisposed()) {
      disposables.dispose();
    }
  }

  public void addDisposable(Disposable disposable) {
    disposables.add(disposable);
  }
}
