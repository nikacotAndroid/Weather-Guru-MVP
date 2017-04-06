package mk.petrovski.weathergurumvp.utils.reactive;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;

/**
 * Created by Nikola Petrovski on 4/5/2017.
 */

public interface BaseSchedulerProvider {

  @NonNull Scheduler io();

  @NonNull Scheduler ui();

  @NonNull Scheduler computation();
}
