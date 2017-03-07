package mk.petrovski.weathergurumvp.data.remote.helper;

import io.reactivex.observers.DisposableObserver;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.WeatherGuruApplication;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ErrorHandlerHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.error.InternetConnectionException;
import mk.petrovski.weathergurumvp.ui.base.BaseMvpView;
import mk.petrovski.weathergurumvp.utils.AppUtils;

/**
 * Created by Nikola Petrovski on 2/22/2017.
 */

/**
 * Default DisposableObserver base class to be used whenever you want default error handling
 */

public class BaseViewSubscriber<V extends BaseMvpView, T> extends DisposableObserver<T> {

  // TODO why can not inject with dagger??
  private ErrorHandlerHelper errorHandlerHelper;

  private V view;

  public BaseViewSubscriber(V view) {
    this.view = view;
    errorHandlerHelper = WeatherGuruApplication.getApplicationComponent().errorHelper();
  }

  public V getView() {
    return view;
  }

  public boolean shouldShowError() {
    return true;
  }

  protected boolean shouldShowLoading() {
    return true;
  }

  @Override public void onStart() {
    if (!AppUtils.isNetworkAvailable(WeatherGuruApplication.getApplicationComponent().context())) {
      onError(new InternetConnectionException());
      return;
    }

    if (shouldShowLoading()) {
      view.showLoading();
    }
    super.onStart();
  }

  @Override public void onError(Throwable e) {
    if (view == null) {
      return;
    }

    if (shouldShowLoading()) {
      view.hideLoading();
    }

    if (shouldShowError()) {
      view.onError(errorHandlerHelper.getProperErrorMessage(e));
    }
  }

  @Override public void onComplete() {
    if (view == null) {
      return;
    }

    if (shouldShowLoading()) {
      view.hideLoading();
    }
  }

  @Override public void onNext(T t) {
    if (view == null) {
      return;
    }
  }
}
