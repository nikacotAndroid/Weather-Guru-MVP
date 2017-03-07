package mk.petrovski.weathergurumvp.data.remote.model.location_models;

import mk.petrovski.weathergurumvp.data.remote.model.error_models.DataResponseModel;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

public class SearchApiResponseModel extends DataResponseModel {
  private ResultModel search_api;

  public ResultModel getSearch_api() {
    return search_api;
  }

  public void setSearch_api(ResultModel search_api) {
    this.search_api = search_api;
  }
}
