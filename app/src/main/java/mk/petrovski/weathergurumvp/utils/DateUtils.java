package mk.petrovski.weathergurumvp.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by Nikola Petrovski on 2/13/2017.
 */
public class DateUtils {

  /**
   * Check if some date is the current day
   */
  private static boolean isToday(String date) {
    return DateTimeComparator.getDateOnlyInstance().compare(new DateTime(), new DateTime(date))
        == 0;
  }

  /**
   * Get name day for some date. If is current day return 'Today'
   */
  //TODO should be in the string.xml
  public static String getDayName(String date) {
    if (isToday(date)) {
      return "Today";
    } else {
      return new DateTime(date).toString(DateTimeFormat.forPattern("EEEE"));
    }
  }

  /**
   * Get short month name
   */
  public static String getMonthName(String date) {
    return new DateTime(date).toString(DateTimeFormat.forPattern("dd MMM"));
  }
}
