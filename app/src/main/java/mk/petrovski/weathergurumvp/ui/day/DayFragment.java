package mk.petrovski.weathergurumvp.ui.day;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherModel;
import mk.petrovski.weathergurumvp.events.RefreshWeatherEvent;
import mk.petrovski.weathergurumvp.events.WeatherDayEvent;
import mk.petrovski.weathergurumvp.ui.base.BaseFragment;
import mk.petrovski.weathergurumvp.ui.day_detail.DayDetailActivity;
import mk.petrovski.weathergurumvp.utils.RecyclerItemUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class DayFragment extends BaseFragment
    implements DayMvpView, RecyclerItemUtils.OnItemClickListener,
    SwipeRefreshLayout.OnRefreshListener {

  @Inject DayMvpPresenter<DayMvpView> presenter;
  @Inject DaysAdapter daysAdapter;

  @BindView(R.id.recycler_days) RecyclerView recyclerDays;
  @BindView(R.id.swipe_layout_days) SwipeRefreshLayout swipeLayoutDays;
  @BindView(R.id.txt_error) TextView txtError;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_day, container, false);

    getActivityFragmentComponent().inject(this);
    setUnBinder(ButterKnife.bind(this, view));
    presenter.attachView(this);

    init();

    return view;
  }

  @Override protected void init() {
    swipeLayoutDays.setOnRefreshListener(this);
    swipeLayoutDays.setColorSchemeResources(R.color.drawer_background_color);
    swipeLayoutDays.setProgressBackgroundColorSchemeColor(
        ContextCompat.getColor(getActivity(), R.color.colorPrimary));

    recyclerDays.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerDays.setAdapter(daysAdapter);
    RecyclerItemUtils.addTo(recyclerDays).setOnItemClickListener(this);

    // load weather
    presenter.loadWeather();
  }

  @Override public void showWeather(List<WeatherModel> weatherList) {
    showRecycler(true);
    daysAdapter.setData(weatherList);
    recyclerDays.smoothScrollToPosition(0);
  }

  @Override public void showEmptyView() {
    showRecycler(false);
    txtError.setText(R.string.error_weather);
  }

  @Override public void onError(String message) {
    // ignore, we want different type of error for this screen
    // /super.onError(message);
    showRecycler(false);
    txtError.setText(message);
  }

  @Override public void onItemClicked(RecyclerView recyclerView, int position, View v) {
    startActivity(new Intent(getActivity(), DayDetailActivity.class));
    EventBus.getDefault().postSticky(new WeatherDayEvent(daysAdapter.getItem(position)));
  }

  @Override public void onRefresh() {
    presenter.loadWeather();
  }

  private void showRecycler(boolean visibility) {
    recyclerDays.setVisibility(visibility ? View.VISIBLE : View.GONE);
    txtError.setVisibility(visibility ? View.GONE : View.VISIBLE);
    swipeLayoutDays.setRefreshing(false);
  }

  @Subscribe public void onEvent(RefreshWeatherEvent refreshWeatherEvent) {
    presenter.loadWeather();
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    EventBus.getDefault().unregister(this);
    super.onStop();
  }

  @Override public void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }
}
