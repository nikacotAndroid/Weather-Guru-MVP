package mk.petrovski.weathergurumvp.ui.day;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherModel;
import mk.petrovski.weathergurumvp.ui.base.BaseRecyclerViewAdapter;
import mk.petrovski.weathergurumvp.utils.AppUtils;

/**
 * Created by Nikola Petrovski on 2/21/2017.
 */

public class DaysAdapter extends BaseRecyclerViewAdapter<DaysAdapter.ViewHolder, WeatherModel> {

  private Context context;

  @Inject public DaysAdapter(Context context) {
    this.context = context;
    setData(new ArrayList<WeatherModel>());
  }

  @Override public DaysAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new DaysAdapter.ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_day, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    WeatherModel weatherModel = getItem(position);

    //set values
    holder.txtDayName.setText(weatherModel.getForrmatedDate());
    holder.txtOriginalDate.setText(weatherModel.getShortMonthDate());
    holder.txtWeatherInfo.setText(weatherModel.getHourly().getWeatherDesc());
    holder.txtTempValue.setText(
        String.format(context.getString(R.string.txt_temp), weatherModel.getHourly().getTempC(),
            weatherModel.getHourly().getFeelsLikeC()));
    holder.txtChanceRain.setText(String.format(context.getString(R.string.txt_rain),
        weatherModel.getHourly().getChanceofrain() + "%"));
    holder.imageWeather.setImageResource(
        AppUtils.getProperIconForWeather(weatherModel.getHourly().getWeatherCode(), true));
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_day_name) TextView txtDayName;
    @BindView(R.id.txt_original_date) TextView txtOriginalDate;
    @BindView(R.id.txt_weather_info) TextView txtWeatherInfo;
    @BindView(R.id.txt_temp_value) TextView txtTempValue;
    @BindView(R.id.txt_chance_rain) TextView txtChanceRain;
    @BindView(R.id.image_weather) ImageView imageWeather;

    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
