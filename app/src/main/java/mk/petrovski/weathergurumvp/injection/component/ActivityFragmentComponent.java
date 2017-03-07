package mk.petrovski.weathergurumvp.injection.component;

import dagger.Component;
import mk.petrovski.weathergurumvp.injection.module.ActivityFragmentModule;
import mk.petrovski.weathergurumvp.injection.scope.ActivityScope;
import mk.petrovski.weathergurumvp.ui.city.ManageCityActivity;
import mk.petrovski.weathergurumvp.ui.day.DayFragment;
import mk.petrovski.weathergurumvp.ui.day_detail.DayDetailActivity;
import mk.petrovski.weathergurumvp.ui.main.MainActivity;
import mk.petrovski.weathergurumvp.ui.welcome.WelcomeActivity;

/**
 * Created by Nikola Petrovski on 2/15/2017.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityFragmentModule.class)
public interface ActivityFragmentComponent {

  void inject(MainActivity mainActivity);

  void inject(DayFragment dayFragment);

  void inject(DayDetailActivity dayDetailActivity);

  void inject(ManageCityActivity manageCityActivity);

  void inject(WelcomeActivity welcomeActivity);
}
