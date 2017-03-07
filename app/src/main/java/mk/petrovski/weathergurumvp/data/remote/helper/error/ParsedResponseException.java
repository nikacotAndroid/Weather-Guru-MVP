package mk.petrovski.weathergurumvp.data.remote.helper.error;

/**
 * Created by Nikola Petrovski on 2/22/2017.
 */
public class ParsedResponseException extends Throwable {

  public ParsedResponseException(String detailMessage) {
    super(detailMessage);
  }
}
