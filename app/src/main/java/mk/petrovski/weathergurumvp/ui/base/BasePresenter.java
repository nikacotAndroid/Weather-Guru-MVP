package mk.petrovski.weathergurumvp.ui.base;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.DataManager;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends BaseMvpView> implements Presenter<V> {

  CompositeDisposableHelper compositeDisposableHelper;
  DataManager dataManager;

  @Inject
  public BasePresenter(CompositeDisposableHelper compositeDisposableHelper, DataManager dataManager) {
    this.compositeDisposableHelper = compositeDisposableHelper;
    this.dataManager = dataManager;
  }

  private V mvpView;

  @Override public void attachView(@NonNull V mvpView) {
    this.mvpView = mvpView;
  }

  @Override public void detachView() {
    mvpView = null;
    compositeDisposableHelper.dispose();
  }

  public boolean isViewAttached() {
    return mvpView != null;
  }

  public V getMvpView() {
    return mvpView;
  }

  public void checkViewAttached() {
    if (!isViewAttached()) throw new MvpViewNotAttachedException();
  }

  public static class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
      super("Please call Presenter.attachView(BaseMvpView) before"
          + " requesting data to the Presenter");
    }
  }

  public DataManager getDataManager() {
    return dataManager;
  }

  public CompositeDisposableHelper getCompositeDisposableHelper() {
    return compositeDisposableHelper;
  }

  public void setCompositeDisposableHelper(CompositeDisposableHelper compositeDisposableHelper) {
    this.compositeDisposableHelper = compositeDisposableHelper;
  }
}

