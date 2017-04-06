package mk.petrovski.weathergurumvp.presenter;

import io.reactivex.Observable;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.ui.welcome.WelcomeMvpView;
import mk.petrovski.weathergurumvp.ui.welcome.WelcomePresenter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nikola Petrovski on 3/31/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class WelcomePresenterTest
    extends BasePresenterTest<WelcomePresenter<WelcomeMvpView>, WelcomeMvpView> {

  @Override WelcomePresenter<WelcomeMvpView> createPresenter() {
    return new WelcomePresenter<>(compositeDisposableHelper, dataManager);
  }

  @Override WelcomeMvpView createView() {
    return mock(WelcomeMvpView.class);
  }

  @Test public void userFound() {
    String userName = "Nikola";

    when(dataManager.getUserName()).thenReturn(userName);

    presenter.checkForUserName();

    verify(view).showMainScreen();
  }

  @Test public void userNotFound() {
    when(dataManager.getUserName()).thenReturn(null);

    presenter.checkForUserName();

    verify(view).startInit();
  }

  @Test public void setUserFirstTime() {
    String userName = "Nikola";

    when(dataManager.getCitiesCount()).thenReturn(Long.valueOf(2));

    presenter.setUserName(userName);

    verify(view).showMainScreen();
  }

  @Test public void setUserSecondTime() {
    String userName = "Nikola";

    when(dataManager.getCitiesCount()).thenReturn(Long.valueOf(0));
    when(dataManager.addDefaultCity()).thenReturn(Observable.just(anyLong()));

    presenter.setUserName(userName);
    testScheduler.triggerActions();

    verify(view).showMainScreen();
  }

  @Test public void noUserName() {
    presenter.setUserName(null);

    verify(view).onError(R.string.error_username);
  }
}
