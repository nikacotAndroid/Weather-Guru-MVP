package mk.petrovski.weathergurumvp.data.remote.model.error_models;

import java.util.List;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class ErrorModel {
  private List<MsgErrorModel> error;

  public List<MsgErrorModel> getError() {
    return error;
  }

  public void setError(List<MsgErrorModel> error) {
    this.error = error;
  }
}
