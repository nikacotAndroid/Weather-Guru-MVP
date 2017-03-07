package mk.petrovski.weathergurumvp.data.remote.helper.error;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import javax.inject.Inject;
import mk.petrovski.weathergurumvp.R;
import mk.petrovski.weathergurumvp.data.remote.model.error_models.DataResponseModel;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Nikola Petrovski on 2/22/2017.
 */

public class ErrorHandlerHelper {

  Context context;
  Retrofit retrofit;

  @Inject public ErrorHandlerHelper(Context context, Retrofit retrofit) {
    this.context = context;
    this.retrofit = retrofit;
  }

  public String getProperErrorMessage(Throwable throwable) {
    Throwable properException = getProperException(throwable);

    if (properException instanceof ServerException) {
      return context.getString(R.string.error_server);
    } else if (properException instanceof ServerNotAvailableException) {
      return context.getString(R.string.error_server_not_available);
    } else if (properException instanceof InternetConnectionException) {
      return context.getString(R.string.error_connection);
    } else if (properException instanceof NotFoundException) {
      return context.getString(R.string.error_not_found);
    } else if (properException instanceof UnauthorizedException) {
      return context.getString(R.string.error_unauthorized);
    } else if (properException instanceof ParsedResponseException) {
      return throwable.getMessage();
    } else {
      return String.format(context.getString(R.string.error_default), throwable.getMessage());
    }
  }

  public Throwable getProperException(Throwable throwable) {
    if (throwable instanceof HttpException) {
      HttpException httpException = (HttpException) throwable;
      Response response = httpException.response();

      // try to parse the error
      Converter<ResponseBody, DataResponseModel> converter =
          retrofit.responseBodyConverter(DataResponseModel.class, new Annotation[0]);
      DataResponseModel error = null;
      try {
        error = converter.convert(response.errorBody());
      } catch (IOException | JsonSyntaxException e) {
        e.printStackTrace();
      }

      if (error == null || error.getData() == null || error.getData().getError() == null) {
        return getThrowable(response.message(), response.code(), throwable);
      } else {
        return new ParsedResponseException(error.getData().getError().get(0).getMsg());
      }
    } else if (throwable instanceof IOException) {
      return new InternetConnectionException();
    } else if (throwable instanceof NetworkErrorException) {
      return new InternetConnectionException();
    }
    return throwable;
  }

  @NonNull private Throwable getThrowable(String message, int code, Throwable throwable) {
    Throwable exception;
    switch (code) {
      case 404:
        exception = new NotFoundException();
        break;
      case 401:
        exception = new UncheckedException(message);
        break;
      case 500:
        exception = new ServerNotAvailableException();
        break;
      case 501:
      case 502:
      case 503:
      case 504:
        exception = new ServerException(throwable);
        break;
      default:
        exception = new UncheckedException(message);
        break;
    }
    return exception;
  }
}
