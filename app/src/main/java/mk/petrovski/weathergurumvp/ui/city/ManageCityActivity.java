package mk.petrovski.weathergurumvp.ui.city;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;
import mk.petrovski.weathergurumvp.events.RefreshCitiesEvent;
import mk.petrovski.weathergurumvp.events.RefreshWeatherEvent;
import mk.petrovski.weathergurumvp.ui.base.BaseActivity;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */

public class ManageCityActivity extends BaseActivity
    implements ManageCityMvpView, AdapterView.OnItemClickListener, CityAdapter.DeleteListener {

  @Inject ManageCityMvpPresenter<ManageCityMvpView> presenter;
  @Inject CityAutoCompleteAdapter autoCompleteAdapter;
  private CityAdapter adapter;

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.autocomplete_txt_city) AutoCompleteTextView autocompleteTxtCity;
  @BindView(R.id.recycler_cities) RecyclerView recyclerCities;
  @BindView(R.id.txt_empty_locations) TextView txtEmptyLocations;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_manage_city);

    getActivityFragmentComponent().inject(this);
    setUnBinder(ButterKnife.bind(this));
    presenter.attachView(this);

    init();
  }

  @Override protected void init() {
    setSupportActionBar(toolbar);
    showBackButton(true);
    setTitle(getString(R.string.screen_manage_cities));

    recyclerCities.setLayoutManager(new LinearLayoutManager(this));
    adapter = new CityAdapter(this);
    recyclerCities.setAdapter(adapter);

    autocompleteTxtCity.setAdapter(autoCompleteAdapter);
    autocompleteTxtCity.setOnItemClickListener(this);

    Observable<String> observable = RxTextView.textChangeEvents(autocompleteTxtCity)
        .skip(2)
        .debounce(500, TimeUnit.MILLISECONDS)
        .map(new Function<TextViewTextChangeEvent, String>() {
          @Override public String apply(TextViewTextChangeEvent textViewTextChangeEvent)
              throws Exception {
            return textViewTextChangeEvent.text().toString();
          }
        })
        .filter(new Predicate<String>() {
          @Override public boolean test(String s) throws Exception {
            return s.length() > 2;
          }
        });

    presenter.loadAutocompleteCities(observable);
    presenter.loadCities();
  }

  @Override public void showAutocompleteCities(List<LocationModel> locationList,
      LocationModel[] locationArray) {
    autoCompleteAdapter.setData(locationArray);
  }

  @Override public void showCities(List<CityDetailsModel> cities) {
    showRecycler(true);
    adapter.setData(cities);
  }

  @Override public void showEmptyView() {
    showRecycler(false);
  }

  @Override public void onCityAdded(CityDetailsModel city) {
    EventBus.getDefault().postSticky(new RefreshCitiesEvent());
    adapter.insert(city, 0);
    recyclerCities.smoothScrollToPosition(0);
    showRecycler(true);
  }

  @Override public void onCityDelete(int position) {
    EventBus.getDefault().postSticky(new RefreshCitiesEvent());
    adapter.remove(position);
    if (adapter.getData().isEmpty()) {
      showEmptyView();
    }
  }

  @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    // clear text
    autocompleteTxtCity.getText().clear();
    hideKeyboard();
    presenter.addNewLocation((LocationModel) parent.getItemAtPosition(position));
  }

  @Override public void onDeleteClick(int position) {
    presenter.deleteLocation(position, adapter.getItem(position));
  }

  private void showRecycler(boolean visibility) {
    recyclerCities.setVisibility(visibility ? View.VISIBLE : View.GONE);
    txtEmptyLocations.setVisibility(visibility ? View.GONE : View.VISIBLE);
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }
}
