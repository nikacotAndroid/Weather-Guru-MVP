package mk.petrovski.weathergurumvp.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import mk.petrovski.weathergurumvp.R;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */
public class RecyclerItemUtils {

  private OnItemClickListener onItemClickListener;

  private RecyclerItemUtils(final RecyclerView recyclerView) {
    recyclerView.setTag(R.id.item_click_support, this);

    recyclerView.addOnChildAttachStateChangeListener(
        new RecyclerView.OnChildAttachStateChangeListener() {
          @Override public void onChildViewAttachedToWindow(View view) {
            if (onItemClickListener != null) {
              view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                  if (onItemClickListener != null) {
                    RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                    onItemClickListener.onItemClicked(recyclerView, holder.getAdapterPosition(), v);
                  }
                }
              });
            }
          }

          @Override public void onChildViewDetachedFromWindow(View view) {

          }
        });
  }

  public static RecyclerItemUtils addTo(RecyclerView view) {
    RecyclerItemUtils support = (RecyclerItemUtils) view.getTag(R.id.item_click_support);
    if (support == null) {
      support = new RecyclerItemUtils(view);
    }
    return support;
  }

  public RecyclerItemUtils setOnItemClickListener(OnItemClickListener listener) {
    onItemClickListener = listener;
    return this;
  }

  public interface OnItemClickListener {
    void onItemClicked(RecyclerView recyclerView, int position, View v);
  }
}