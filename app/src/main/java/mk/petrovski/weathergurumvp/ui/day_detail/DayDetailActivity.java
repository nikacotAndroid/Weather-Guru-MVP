package mk.petrovski.weathergurumvp.ui.day_detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherModel;
import mk.petrovski.weathergurumvp.events.WeatherDayEvent;
import mk.petrovski.weathergurumvp.ui.base.BaseActivity;
import mk.petrovski.weathergurumvp.ui.custom.PropertyItemView;
import mk.petrovski.weathergurumvp.utils.AppUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class DayDetailActivity extends BaseActivity implements DayDetailMvpView {

  @Inject DayDetailMvpPresenter<DayDetailMvpView> presenter;

  @BindView(R.id.txt_city_name) TextView txtCityName;
  @BindView(R.id.txt_weather_info) TextView txtWeatherInfo;
  @BindView(R.id.txt_temp_value) TextView txtTempValue;
  @BindView(R.id.image_weather) ImageView imageWeather;
  @BindView(R.id.view_feels_like) PropertyItemView viewFeelsLike;
  @BindView(R.id.view_chance_rain) PropertyItemView viewChanceRain;
  @BindView(R.id.view_wind) PropertyItemView viewWind;
  @BindView(R.id.view_humidity) PropertyItemView viewHumidity;
  @BindView(R.id.view_visibility) PropertyItemView viewVisibility;
  @BindView(R.id.txt_sunrise) TextView txtSunrise;
  @BindView(R.id.txt_sunset) TextView txtSunset;
  @BindView(R.id.toolbar) Toolbar toolbar;

  private WeatherModel weatherModel;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_day_details);

    getActivityFragmentComponent().inject(this);
    setUnBinder(ButterKnife.bind(this));
    presenter.attachView(this);
  }

  @Override protected void init() {
    setSupportActionBar(toolbar);
    showBackButton(true);

    presenter.setCurrentCity();

    // setting proper screen title
    setTitle(String.format("%s (%s)", weatherModel.getForrmatedDate(),
        weatherModel.getShortMonthDate()));

    // setting values
    imageWeather.setImageResource(
        AppUtils.getProperIconForWeather(weatherModel.getHourly().getWeatherCode(), false));
    txtWeatherInfo.setText(weatherModel.getHourly().getWeatherDesc());
    txtTempValue.setText(
        String.format(getString(R.string.txt_temp_value), weatherModel.getHourly().getTempC()));
    txtSunrise.setText(weatherModel.getAstronomy().getSunrise());
    txtSunset.setText(weatherModel.getAstronomy().getSunset());
    viewFeelsLike.setValue(String.format(getString(R.string.txt_temp_value),
        weatherModel.getHourly().getFeelsLikeC()));
    viewChanceRain.setValue(String.format(getString(R.string.txt_percentage_value),
        weatherModel.getHourly().getChanceofrain()));
    viewWind.setValue(String.format(getString(R.string.txt_wind_value),
        weatherModel.getHourly().getWindGustKmph(), weatherModel.getHourly().getWinddirDegree()));
    viewHumidity.setValue(String.format(getString(R.string.txt_percentage_value),
        weatherModel.getHourly().getHumidity()));
    viewVisibility.setValue(String.format(getString(R.string.txt_visibility_value),
        weatherModel.getHourly().getVisibility()));
  }

  @Override public void setCurrentCityName(String areaName) {
    txtCityName.setText(areaName);
  }

  @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
  public void onEvent(WeatherDayEvent weatherDayEvent) {

    // remove the sticky event if exist
    WeatherDayEvent stickyEvent = EventBus.getDefault().getStickyEvent(WeatherDayEvent.class);
    if (stickyEvent != null) {
      EventBus.getDefault().removeStickyEvent(stickyEvent);
    }

    weatherModel = weatherDayEvent.getWeatherModel();
    init();
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    EventBus.getDefault().unregister(this);
    super.onStop();
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }
}
