package mk.petrovski.weathergurumvp.ui.city;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.local.db.CityDetailsModel;
import mk.petrovski.weathergurumvp.ui.base.BaseRecyclerViewAdapter;

/**
 * Created by Nikola Petrovski on 2/27/2017.
 */

public class CityAdapter extends BaseRecyclerViewAdapter<CityAdapter.ViewHolder, CityDetailsModel> {

  private DeleteListener deleteListener;

  public CityAdapter(DeleteListener deleteListener) {
    this.deleteListener = deleteListener;
    setData(new ArrayList<CityDetailsModel>());
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false));
  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {
    CityDetailsModel city = getItem(position);

    holder.txtAreaName.setText(city.getAreaName());
    holder.txtCountryName.setText(String.format("%s,%s", city.getRegion(), city.getCountry()));
    holder.setDeleteListener(deleteListener);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_area_name) TextView txtAreaName;
    @BindView(R.id.txt_country_name) TextView txtCountryName;
    @BindView(R.id.btn_delete) ImageView btnDelete;

    private DeleteListener deleteListener;

    public void setDeleteListener(DeleteListener deleteListener) {
      this.deleteListener = deleteListener;
    }

    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btn_delete) public void onClick() {
      deleteListener.onDeleteClick(getAdapterPosition());
    }
  }

  public interface DeleteListener {
    void onDeleteClick(int position);
  }
}
