package mk.petrovski.weathergurumvp.presenter;

import android.support.annotation.CallSuper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;
import mk.petrovski.weathergurumvp.data.DataManager;
import mk.petrovski.weathergurumvp.data.remote.helper.CompositeDisposableHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ErrorHandlerHelper;
import mk.petrovski.weathergurumvp.ui.base.BaseMvpView;
import mk.petrovski.weathergurumvp.ui.base.BasePresenter;
import mk.petrovski.weathergurumvp.utils.reactive.TestSchedulerProvider;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Nikola Petrovski on 4/5/2017.
 */

public abstract class BasePresenterTest<P extends BasePresenter<V>, V extends BaseMvpView> {

  @Mock ErrorHandlerHelper errorHandlerHelper;
  @Mock DataManager dataManager;

  public V view;
  public P presenter;
  public CompositeDisposableHelper compositeDisposableHelper;
  public TestScheduler testScheduler;

  @CallSuper @Before public void before() {
    initMocks(this);

    testScheduler = new TestScheduler();
    compositeDisposableHelper = new CompositeDisposableHelper(new CompositeDisposable(),
        new TestSchedulerProvider(testScheduler));

    view = createView();
    presenter = createPresenter();
    presenter.attachView(view);
  }

  @CallSuper @After public void tearDown() throws Exception {
    presenter.detachView();
  }

  abstract P createPresenter();
  abstract V createView();

}