package mk.petrovski.weathergurumvp.ui.base;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.data.local.preferences.ApplicationPreferences;
import mk.petrovski.weathergurumvp.data.local.db.DbHelper;
import mk.petrovski.weathergurumvp.data.remote.ApiHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends BaseMvpView> implements Presenter<V> {

  @Inject CompositeDisposableHelper compositeDisposableHelper;

  DbHelper dbHelper;
  ApplicationPreferences applicationPreferences;
  ApiHelper apiHelper;

  @Inject protected BasePresenter(DbHelper dbHelper, ApplicationPreferences applicationPreferences,
      ApiHelper apiHelper) {
    this.dbHelper = dbHelper;
    this.applicationPreferences = applicationPreferences;
    this.apiHelper = apiHelper;
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

  public DbHelper getDbHelper() {
    return dbHelper;
  }

  public ApplicationPreferences getApplicationPreferences() {
    return applicationPreferences;
  }

  public ApiHelper getApiHelper() {
    return apiHelper;
  }

  public CompositeDisposableHelper getCompositeDisposableHelper() {
    return compositeDisposableHelper;
  }
}

