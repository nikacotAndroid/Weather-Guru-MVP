package mk.petrovski.weathergurumvp.ui.day;

import java.util.List;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherModel;
import mk.petrovski.weathergurumvp.ui.base.BaseMvpView;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public interface DayMvpView extends BaseMvpView {

  void showWeather(List<WeatherModel> weatherList);

  void showEmptyView();

}
