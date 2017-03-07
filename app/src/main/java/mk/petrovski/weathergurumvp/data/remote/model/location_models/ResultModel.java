package mk.petrovski.weathergurumvp.data.remote.model.location_models;

import java.util.List;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class ResultModel {
  private List<LocationModel> result;

  public List<LocationModel> getResult() {
    return result;
  }

  public void setResult(List<LocationModel> result) {
    this.result = result;
  }
}
