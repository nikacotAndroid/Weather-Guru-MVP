package mk.petrovski.weathergurumvp.injection.module;

import android.content.Context;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import mk.petrovski.weathergurumvp.data.remote.ApiConsts;
import mk.petrovski.weathergurumvp.data.remote.ApiHelper;
import mk.petrovski.weathergurumvp.data.remote.ApiInterface;
import mk.petrovski.weathergurumvp.data.remote.BaseApiHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.HeaderHelper;
import mk.petrovski.weathergurumvp.data.remote.helper.error.ErrorHandlerHelper;
import mk.petrovski.weathergurumvp.injection.qualifier.ApplicationContext;
import mk.petrovski.weathergurumvp.injection.scope.WeatherGuruApplicationScope;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */

@Module(includes = { ContextModule.class, RxModule.class }) public class NetworkModule {

  @Provides @WeatherGuruApplicationScope public Interceptor httpInterceptor() {
    return new Interceptor() {
      @Override public Response intercept(Chain chain) throws IOException {

        // Request customization: add request headers
        Request.Builder requestBuilder = chain.request()
            .newBuilder()
            .method(chain.request().method(), chain.request().body())
            .headers(HeaderHelper.getAppHeaders());

        return chain.proceed(requestBuilder.build());
      }
    };
  }

  @Provides @WeatherGuruApplicationScope public HttpLoggingInterceptor loggingInterceptor() {
    HttpLoggingInterceptor interceptor =
        new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
          @Override public void log(String message) {
            Timber.i(message);
          }
        });
    interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    return interceptor;
  }

  @Provides @WeatherGuruApplicationScope public OkHttpClient okHttpClient(Interceptor interceptor) {
    return new OkHttpClient.Builder().addInterceptor(interceptor)
        .addInterceptor(loggingInterceptor())
        .build();
  }

  @Provides @WeatherGuruApplicationScope public Retrofit retrofit(OkHttpClient okHttpClient) {
    return new Retrofit.Builder().baseUrl(ApiConsts.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }

  @Provides @WeatherGuruApplicationScope
  public ApiInterface apiServiceInterface(Retrofit retrofit) {
    return retrofit.create(ApiInterface.class);
  }

  @Provides @WeatherGuruApplicationScope
  public ErrorHandlerHelper errorHandlerHelper(@ApplicationContext Context context,
      Retrofit retrofit) {
    return new ErrorHandlerHelper(context, retrofit);
  }

  @Provides @WeatherGuruApplicationScope public BaseApiHelper apiHelper(ApiInterface apiInterface) {
    return new ApiHelper(apiInterface);
  }
}
