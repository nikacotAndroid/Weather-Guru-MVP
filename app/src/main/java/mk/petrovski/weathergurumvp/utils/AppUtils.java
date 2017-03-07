package mk.petrovski.weathergurumvp.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.inputmethod.InputMethodManager;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.ui.base.BaseActivity;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class AppUtils {

  /**
   * Check for internet connection
   */
  public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
  }

  /**
   * Show loading progress
   */
  public static ProgressDialog showLoadingDialog(Context context) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.show();

    if (progressDialog.getWindow() != null) {
      progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    progressDialog.setContentView(R.layout.dialog_progress);
    progressDialog.setIndeterminate(true);
    progressDialog.setCancelable(true);
    progressDialog.setCanceledOnTouchOutside(false);

    return progressDialog;
  }

  /**
   * Hide keyboard
   */
  public static void hideKeyboard(Context context) {
    InputMethodManager inputManager =
        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    if (((Activity) context).getCurrentFocus() != null) {
      try {
        inputManager.hideSoftInputFromWindow(
            ((BaseActivity) context).getCurrentFocus().getWindowToken(), 0);
      } catch (NullPointerException ex) {
        //
      }
    }
  }

  /**
   * Getting proper weather icon. Weather codes can be find http://www.worldweatheronline.com/feed/wwoConditionCodes.xml
   * (Default icons from the API did not match with the app design, so I have made different one)
   *
   * There are 7 categories: Snow, Cloudy, Sunny, Rain, Drizzle, Fog and Sleet
   * Default one is Cloudy if I have missed some weather code
   */
  public static int getProperIconForWeather(String weatherCode, boolean isBlack) {
    int code = Integer.parseInt(weatherCode);

    if (code == 395
        || code == 392
        || code == 371
        || code == 368
        || code == 338
        || code == 335
        || code == 329
        || code == 326
        || code == 323
        || code == 227
        || code == 179
        || code == 332) {
      return isBlack ? R.drawable.ic_snow_black : R.drawable.ic_snow;
    } else if (code == 119 || code == 116 || code == 122) {
      return isBlack ? R.drawable.ic_clouds_black : R.drawable.ic_clouds;
    } else if (code == 113) {
      return isBlack ? R.drawable.ic_sunny_black : R.drawable.ic_sunny;
    } else if (code == 389
        || code == 386
        || code == 359
        || code == 356
        || code == 353
        ||
        code == 314
        || code == 311
        || code == 308
        || code == 305
        || code == 302
        || code == 299
        || code == 296
        || code == 176
        || code == 200
        || code == 293) {
      return isBlack ? R.drawable.ic_rain_black : R.drawable.ic_rain;
    } else if (code == 281
        || code == 266
        || code == 263
        || code == 185
        || code == 230
        || code == 284
        || code == 377
        || code == 374) {
      return isBlack ? R.drawable.ic_drizzle_black : R.drawable.ic_drizzle;
    } else if (code == 143 || code == 248 || code == 260) {
      return isBlack ? R.drawable.ic_fog_black : R.drawable.ic_fog;
    } else if (code == 320
        || code == 317
        || code == 182
        || code == 350
        || code == 362
        || code == 365) {
      return isBlack ? R.drawable.ic_sleet_back : R.drawable.ic_sleet;
    } else {
      return isBlack ? R.drawable.ic_clouds_black : R.drawable.ic_clouds;
    }
  }
}
