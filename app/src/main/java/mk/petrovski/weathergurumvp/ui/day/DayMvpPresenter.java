package mk.petrovski.weathergurumvp.ui.day;

import mk.petrovski.weathergurumvp.ui.base.Presenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public interface DayMvpPresenter<V extends DayMvpView> extends Presenter<V> {

  void loadWeather();
}
