package mk.petrovski.weathergurumvp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.List;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.events.RefreshCitiesEvent;
import mk.petrovski.weathergurumvp.events.RefreshWeatherEvent;
import mk.petrovski.weathergurumvp.ui.base.BaseActivity;
import mk.petrovski.weathergurumvp.ui.city.ManageCityActivity;
import mk.petrovski.weathergurumvp.ui.day.DayFragment;
import mk.petrovski.weathergurumvp.utils.RecyclerItemUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class MainActivity extends BaseActivity
    implements MainMvpView, RecyclerItemUtils.OnItemClickListener {

  @Inject MainMvpPresenter<MainMvpView> presenter;
  @Inject DrawerCityAdapter drawerCityAdapter;

  @BindView(R.id.container) FrameLayout container;
  @BindView(R.id.image_add) ImageView imageAdd;
  @BindView(R.id.header_drawer) RelativeLayout headerDrawer;
  @BindView(R.id.recycler_view_menu) RecyclerView recyclerViewMenu;
  @BindView(R.id.drawer) DrawerLayout drawer;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.txt_empty_locations) TextView txtEmptyLocations;
  @BindView(R.id.txt_user_name) TextView txtUserName;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getActivityFragmentComponent().inject(this);
    setUnBinder(ButterKnife.bind(this));
    presenter.attachView(this);

    init();
  }

  @Override protected void init() {
    setSupportActionBar(toolbar);

    ActionBarDrawerToggle drawerToggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name) {
          @Override public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            hideKeyboard();
          }

          @Override public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
          }
        };

    drawer.addDrawerListener(drawerToggle);
    drawerToggle.syncState();

    recyclerViewMenu.setLayoutManager(new LinearLayoutManager(this));
    recyclerViewMenu.setAdapter(drawerCityAdapter);
    RecyclerItemUtils.addTo(recyclerViewMenu).setOnItemClickListener(this);

    // interact with presenter
    presenter.setDrawerCities();
    presenter.setUserInfo();
    presenter.setCurrentCityTitle();

    getSupportFragmentManager().beginTransaction()
        .replace(R.id.container, new DayFragment())
        .commit();
  }

  @Override public void showCities(List<CityDetailsModel> locationList) {
    drawerCityAdapter.setData(locationList);
    recyclerViewMenu.setVisibility(View.VISIBLE);
    txtEmptyLocations.setVisibility(View.GONE);
  }

  @Override public void showEmptyView() {
    recyclerViewMenu.setVisibility(View.GONE);
    txtEmptyLocations.setVisibility(View.VISIBLE);
  }

  @Override public void setMenuUserName(String userName) {
    txtUserName.setText(String.format(getString(R.string.txt_welcome_main), userName));
  }

  @Override public void onCitySelect() {
    drawer.closeDrawer(Gravity.LEFT);
    EventBus.getDefault().post(new RefreshWeatherEvent());
  }

  @Override public void setCurrentCityTitle(String title) {
    setTitle(title);
  }

  @OnClick(R.id.image_add) public void onClick() {
    startActivity(new Intent(this, ManageCityActivity.class));
  }

  @Override public void onItemClicked(RecyclerView recyclerView, int position, View v) {
    CityDetailsModel city = drawerCityAdapter.getItem(position);
    if (!city.getIsSelected()) {
      setCurrentCityTitle(city.getAreaName());
      presenter.selectCity(city);
    }
  }

  @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
  public void onEvent(RefreshCitiesEvent refreshCitiesEvent) {
    // remove the sticky event if exist
    RefreshCitiesEvent stickyEvent = EventBus.getDefault().getStickyEvent(RefreshCitiesEvent.class);
    if (stickyEvent != null) {
      EventBus.getDefault().removeStickyEvent(stickyEvent);
    }

    presenter.setDrawerCities();
    presenter.setCurrentCityTitle();
    EventBus.getDefault().post(new RefreshWeatherEvent());
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
