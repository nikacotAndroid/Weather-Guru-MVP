package mk.petrovski.weathergurumvp.data;

import mk.petrovski.weathergurumvp.data.local.db.DbHelper;
import mk.petrovski.weathergurumvp.data.local.preferences.PreferencesHelper;
import mk.petrovski.weathergurumvp.data.remote.ApiHelper;

/**
 * Created by Nikola Petrovski on 3/30/2017.
 */

public interface DataManager extends PreferencesHelper, DbHelper, ApiHelper {
}
