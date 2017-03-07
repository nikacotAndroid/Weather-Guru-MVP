package mk.petrovski.weathergurumvp.ui.main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.data.remote.model.weather_models.WeatherModel;
import mk.petrovski.weathergurumvp.ui.base.BaseRecyclerViewAdapter;

/**
 * Created by Nikola Petrovski on 2/21/2017.
 */

public class DrawerCityAdapter
    extends BaseRecyclerViewAdapter<DrawerCityAdapter.ViewHolder, CityDetailsModel> {

  private Context context;

  @Inject public DrawerCityAdapter(Context context) {
    this.context = context;
    setData(new ArrayList<CityDetailsModel>());
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_drawer, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    CityDetailsModel city = getItem(position);

    //set name
    holder.txtDrawerCity.setText(city.getAreaName());

    //set selection
    if (city.getIsSelected()) {
      holder.txtDrawerCity.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_city_selected, 0,
          0, 0);
      holder.txtDrawerCity.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
    } else {
      holder.txtDrawerCity.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_city_unselected, 0,
          0, 0);
      holder.txtDrawerCity.setTextColor(
          ContextCompat.getColor(context, R.color.drawer_menu_unselected_color));
    }
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_drawer_city) TextView txtDrawerCity;

    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
