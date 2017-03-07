package mk.petrovski.weathergurumvp.injection.module;

import android.app.Activity;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.injection.qualifier.ActivityContext;
import mk.petrovski.weathergurumvp.injection.scope.ActivityScope;
import mk.petrovski.weathergurumvp.ui.city.CityAdapter;
import mk.petrovski.weathergurumvp.ui.city.CityAutoCompleteAdapter;
import mk.petrovski.weathergurumvp.ui.city.ManageCityMvpPresenter;
import mk.petrovski.weathergurumvp.ui.city.ManageCityMvpView;
import mk.petrovski.weathergurumvp.ui.city.ManageCityPresenter;
import mk.petrovski.weathergurumvp.ui.day.DayMvpPresenter;
import mk.petrovski.weathergurumvp.ui.day.DayMvpView;
import mk.petrovski.weathergurumvp.ui.day.DayPresenter;
import mk.petrovski.weathergurumvp.ui.day.DaysAdapter;
import mk.petrovski.weathergurumvp.ui.day_detail.DayDetailMvpPresenter;
import mk.petrovski.weathergurumvp.ui.day_detail.DayDetailMvpView;
import mk.petrovski.weathergurumvp.ui.day_detail.DayDetailPresenter;
import mk.petrovski.weathergurumvp.ui.main.DrawerCityAdapter;
import mk.petrovski.weathergurumvp.ui.main.MainMvpPresenter;
import mk.petrovski.weathergurumvp.ui.main.MainMvpView;
import mk.petrovski.weathergurumvp.ui.main.MainPresenter;
import mk.petrovski.weathergurumvp.ui.welcome.WelcomeMvpPresenter;
import mk.petrovski.weathergurumvp.ui.welcome.WelcomeMvpView;
import mk.petrovski.weathergurumvp.ui.welcome.WelcomePresenter;

/**
 * Created by Nikola Petrovski on 2/15/2017.
 */

@Module(includes = RxModule.class) public class ActivityFragmentModule {

  private Activity activity;

  public ActivityFragmentModule(Activity activity) {
    this.activity = activity;
  }

  @Provides Activity getActivity() {
    return activity;
  }

  @Provides @ActivityContext Context getActivityContext() {
    return activity;
  }

  @Provides @ActivityScope MainMvpPresenter<MainMvpView> getMainPresenter(
      MainPresenter<MainMvpView> presenter) {
    return presenter;
  }

  @Provides @ActivityScope DayMvpPresenter<DayMvpView> getDayPresenter(
      DayPresenter<DayMvpView> presenter) {
    return presenter;
  }

  @Provides @ActivityScope DayDetailMvpPresenter<DayDetailMvpView> getDayDetailPresenter(
      DayDetailPresenter<DayDetailMvpView> presenter) {
    return presenter;
  }

  @Provides @ActivityScope ManageCityMvpPresenter<ManageCityMvpView> getCityPresenter(
      ManageCityPresenter<ManageCityMvpView> presenter) {
    return presenter;
  }

  @Provides @ActivityScope WelcomeMvpPresenter<WelcomeMvpView> getWelcomePresenter(
      WelcomePresenter<WelcomeMvpView> presenter) {
    return presenter;
  }

  @Provides @ActivityScope DrawerCityAdapter getDrawerAdapter(@ActivityContext Context context) {
    return new DrawerCityAdapter(context);
  }

  @Provides @ActivityScope DaysAdapter getDaysAdapter(@ActivityContext Context context) {
    return new DaysAdapter(context);
  }

  @Provides @ActivityScope CityAutoCompleteAdapter getCityAutocompleteAdapter(
      @ActivityContext Context context) {
    return new CityAutoCompleteAdapter(context, R.layout.item_city_autocomplete);
  }
}
