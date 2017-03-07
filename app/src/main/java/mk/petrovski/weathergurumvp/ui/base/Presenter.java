package mk.petrovski.weathergurumvp.ui.base;

import android.support.annotation.NonNull;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the BaseMvpView type that wants to be attached with.
 */
public interface Presenter<V extends BaseMvpView> {

  /**
   * Called when the view is attached to the presenter. Presenters should normally not use this
   * method since it's only used to link the view to the presenter which is done by the
   * BasePresenter.
   *
   * @param mvpView the view
   */
  void attachView(@NonNull V mvpView);

  /**
   * Called when the view is detached from the presenter. Presenters should normally not use this
   * method since it's only used to unlink the view from the presenter which is done by the
   * BasePresenter.
   */
  void detachView();
}
