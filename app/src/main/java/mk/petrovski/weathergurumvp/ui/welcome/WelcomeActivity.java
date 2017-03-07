package mk.petrovski.weathergurumvp.ui.welcome;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.lottie.LottieAnimationView;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.ui.base.BaseActivity;
import mk.petrovski.weathergurumvp.ui.main.MainActivity;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class WelcomeActivity extends BaseActivity
    implements WelcomeMvpView, Animator.AnimatorListener {

  @Inject WelcomeMvpPresenter<WelcomeMvpView> presenter;

  @BindView(R.id.animation_view) LottieAnimationView animationView;
  @BindView(R.id.edit_text_name) EditText editTextName;
  @BindView(R.id.btn_show_weather) Button btnShowWeather;
  @BindView(R.id.layout_user_name) RelativeLayout layoutUserName;
  @BindView(R.id.image_welcome) ImageView imageWelcome;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    getActivityFragmentComponent().inject(this);
    setUnBinder(ButterKnife.bind(this));
    presenter.attachView(this);

    init();
  }

  @Override public void onStop() {
    super.onStop();
    animationView.cancelAnimation();
  }

  @Override protected void init() {
    presenter.checkForUserName();
  }

  @Override public void startInit() {
    animationView.setProgress(0f);
    animationView.playAnimation();
    animationView.addAnimatorListener(this);
  }

  @Override public void showMainScreen() {
    finish();
    startActivity(new Intent(this, MainActivity.class));
  }

  @Override public void onAnimationEnd(Animator animation) {
    layoutUserName.setVisibility(View.VISIBLE);
    editTextName.requestFocus();
    ObjectAnimator.ofFloat(layoutUserName, View.ALPHA, 0f, 1f).start();
    ObjectAnimator.ofFloat(imageWelcome, View.ALPHA, 0f, 1f).start();
  }

  @Override public void onAnimationStart(Animator animation) {

  }

  @Override public void onAnimationCancel(Animator animation) {

  }

  @Override public void onAnimationRepeat(Animator animation) {

  }

  @OnClick(R.id.btn_show_weather) public void onClick() {
    hideKeyboard();
    presenter.setUserName(editTextName.getText().toString());
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }
}
