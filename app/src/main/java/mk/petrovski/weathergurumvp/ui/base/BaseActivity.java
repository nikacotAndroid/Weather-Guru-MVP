package mk.petrovski.weathergurumvp.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.Unbinder;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.WeatherGuruApplication;
import mk.petrovski.weathergurumvp.injection.component.ActivityFragmentComponent;
import mk.petrovski.weathergurumvp.injection.component.DaggerActivityFragmentComponent;
import mk.petrovski.weathergurumvp.injection.module.ActivityFragmentModule;
import mk.petrovski.weathergurumvp.utils.AppUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Nikola Petrovski on 2/15/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView {

  private ActivityFragmentComponent activityFragmentComponent;
  private Unbinder unbinder;
  private ProgressDialog progressDialog;

  @Override protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityFragmentComponent = DaggerActivityFragmentComponent.builder()
        .activityFragmentModule(new ActivityFragmentModule(this))
        .applicationComponent(WeatherGuruApplication.getApplicationComponent())
        .build();
  }

  public void showBackButton(boolean shouldShow) {
    if (getSupportActionBar() != null) {
      Drawable backArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
      backArrow.setColorFilter(ContextCompat.getColor(this, android.R.color.white),
          PorterDuff.Mode.SRC_ATOP);
      getSupportActionBar().setHomeAsUpIndicator(backArrow);
      getSupportActionBar().setHomeButtonEnabled(shouldShow);
      getSupportActionBar().setDisplayHomeAsUpEnabled(shouldShow);
    }
  }

  public ActivityFragmentComponent getActivityFragmentComponent() {
    return activityFragmentComponent;
  }

  @Override public void showLoading() {
    hideLoading();
    progressDialog = AppUtils.showLoadingDialog(this);
  }

  @Override public void hideLoading() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.cancel();
    }
  }

  @Override public void onError(@StringRes int resId) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
  }

  @Override public void onError(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  @Override public boolean isNetworkConnected() {
    return AppUtils.isNetworkAvailable(getApplicationContext());
  }

  @Override public void hideKeyboard() {
    AppUtils.hideKeyboard(this);
  }

  public void setUnBinder(Unbinder unBinder) {
    unbinder = unBinder;
  }

  @Override protected void onDestroy() {
    if (unbinder != null) {
      unbinder.unbind();
    }
    super.onDestroy();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  protected abstract void init();
}
