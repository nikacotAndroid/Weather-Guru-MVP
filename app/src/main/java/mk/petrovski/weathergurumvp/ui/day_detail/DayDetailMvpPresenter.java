package mk.petrovski.weathergurumvp.ui.day_detail;

import mk.petrovski.weathergurumvp.ui.base.BaseMvpView;
import mk.petrovski.weathergurumvp.ui.base.Presenter;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public interface DayDetailMvpPresenter<V extends BaseMvpView> extends Presenter<V> {

  void setCurrentCity();
}
