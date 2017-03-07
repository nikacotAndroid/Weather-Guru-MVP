package mk.petrovski.weathergurumvp.data.remote.model.error_models;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class DataResponseModel {
  private ErrorModel data;

  public ErrorModel getData() {
    return data;
  }

  public void setData(ErrorModel data) {
    this.data = data;
  }
}
