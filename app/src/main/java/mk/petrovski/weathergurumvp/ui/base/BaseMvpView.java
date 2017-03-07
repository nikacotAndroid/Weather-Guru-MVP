package mk.petrovski.weathergurumvp.ui.base;

import android.support.annotation.StringRes;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface BaseMvpView {

  void showLoading();

  void hideLoading();

  void onError(@StringRes int resId);

  void onError(String message);

  boolean isNetworkConnected();

  void hideKeyboard();
}
