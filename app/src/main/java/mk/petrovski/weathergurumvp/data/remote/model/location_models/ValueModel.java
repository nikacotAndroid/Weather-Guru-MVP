package mk.petrovski.weathergurumvp.data.remote.model.location_models;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class ValueModel {
  private String value;

  public ValueModel(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
