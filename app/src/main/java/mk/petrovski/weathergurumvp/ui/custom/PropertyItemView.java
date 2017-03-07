package mk.petrovski.weathergurumvp.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import mk.petrovski.weathergurumvp.R;

/**
 * Created by Nikola Petrovski on 2/14/2017.
 */
public class PropertyItemView extends RelativeLayout {
  @BindView(R.id.txt_hint) TextView txtHint;
  @BindView(R.id.txt_value) TextView txtValue;
  @BindView(R.id.img_icon) ImageView imgIcon;
  @BindView(R.id.view_bottom_line) View viewBottomLine;

  public PropertyItemView(Context context) {
    super(context);
  }

  public PropertyItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public PropertyItemView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    inflate(getContext(), R.layout.view_property_layout, this);
    ButterKnife.bind(this);

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PropertyItemView);

    // setting values
    this.txtHint.setText(typedArray.getString(R.styleable.PropertyItemView_hintDescription));
    this.txtValue.setText(typedArray.getString(R.styleable.PropertyItemView_value));
    this.imgIcon.setImageResource(
        typedArray.getResourceId(R.styleable.PropertyItemView_iconDescription, -1));
    this.viewBottomLine.setVisibility(
        typedArray.getBoolean(R.styleable.PropertyItemView_bottomLineVisibility, true)
            ? View.VISIBLE : View.GONE);

    typedArray.recycle();
  }

  public void setValue(String valueTxt) {
    txtValue.setText(valueTxt);
  }
}
