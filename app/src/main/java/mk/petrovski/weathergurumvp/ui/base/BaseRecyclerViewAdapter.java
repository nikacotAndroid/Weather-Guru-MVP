package mk.petrovski.weathergurumvp.ui.base;

import android.support.v7.widget.RecyclerView;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Nikola Petrovski on 3/2/2017.
 */

public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T>
    extends RecyclerView.Adapter<VH> {

  private List<T> data;

  @Override public int getItemCount() {
    if (data == null) {
      return 0;
    } else {
      return data.size();
    }
  }

  @Override public long getItemId(int position) {
    return position;
  }

  /**
   * Setting the data.
   *
   * @param data Data that need to be set.
   */
  public void setData(List<T> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  /**
   * Returns the data.
   *
   * @return Array of data
   */
  public List<T> getData() {
    return data;
  }

  /**
   * Inserts the specific item at the specified position in the array.
   *
   * @param item The item to insert at specified position.
   * @param position Specified position of the new item.
   */
  public void insert(T item, int position) {
    data.add(position, item);
    notifyItemInserted(position);
  }

  /**
   * Removes item of specified position.
   *
   * @param position Specified position of removal.
   */
  public void remove(int position) {
    data.remove(position);
    notifyItemRemoved(position);
  }

  /**
   * Returns the item from the data in the specified position.
   *
   * @param position Specified position of the item.
   * @return The item of the specified position.
   */
  public T getItem(final int position) {
    if (data != null && data.size() > 0) {
      return data.get(position);
    } else {
      return null;
    }
  }

  /**
   * Clears all the data in the array.
   */
  public void clear() {
    int size = getItemCount();
    data.clear();
    notifyItemRangeRemoved(0, size);
  }

  /**
   * Sorts the content of this adapter using the specified comparator.
   *
   * @param comparator The comparator used to sort the objects contained in this adapter.
   */
  public void sort(Comparator<? super T> comparator) {
    Collections.sort(data, comparator);
    notifyItemRangeChanged(0, getItemCount());
  }

  /**
   * Returns the position of the specified item in the array.
   *
   * @param item The item to retrieve the position of.
   * @return The position of the specified item.
   */
  public int getPosition(T item) {
    return data.indexOf(item);
  }
}
