package mk.petrovski.weathergurumvp.ui.city;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.remote.model.location_models.LocationModel;

/**
 * Created by Nikola Petrovski on 2/27/2017.
 */

public class CityAutoCompleteAdapter extends ArrayAdapter<LocationModel> {

  private LocationModel[] data;
  private Context context;

  public CityAutoCompleteAdapter(Context context, int resource) {
    super(context, resource);

    this.context = context;
  }

  public void setData(LocationModel[] data) {
    this.data = data;
    notifyDataSetChanged();
  }

  @Override public int getCount() {
    return data.length;
  }

  @Override public LocationModel getItem(int position) {
    return data[position];
  }

  @Override public long getItemId(int position) {
    return 0;
  }

  @Override @NonNull
  public View getView(int position, View convertedView, @NonNull ViewGroup viewGroup) {
    if (convertedView == null) {
      LayoutInflater inflater =
          (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertedView = inflater.inflate(R.layout.item_city_autocomplete, viewGroup, false);

      ViewHolder viewHolder = new ViewHolder();
      ButterKnife.bind(viewHolder, convertedView);

      convertedView.setTag(viewHolder);
    }

    final LocationModel city = getItem(position);
    final ViewHolder holder = (ViewHolder) convertedView.getTag();

    if (city != null) {
      holder.txtAreaName.setText(city.getAreaName());
      holder.txtCountryName.setText(city.getRegion() + " , " + city.getCountry());
    } else {
      holder.txtAreaName.setText(context.getString(R.string.empty_area));
      holder.txtCountryName.setText(context.getString(R.string.empty_country));
    }

    return convertedView;
  }

  public static class ViewHolder {
    @BindView(R.id.txt_area_name) TextView txtAreaName;
    @BindView(R.id.txt_country_name) TextView txtCountryName;
  }
}
