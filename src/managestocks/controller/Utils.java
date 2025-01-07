package managestocks.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


/**Utils class for input validation of dates, and numbers (shares).*/
public class Utils {

  /**Checks that the given number isn't negative. Otherwise, throws an IllegalStateException.
   *
   * @param num the number to be validated. (usually shares).
   */
  public static void checkInputNonNegative(double num) {
    if ((num < 0)) {
      throw new IllegalStateException( "Negative numbers are invalid:" + num);
    }
  }

  /** Validates that the date is in the form yyyy-mm-dd and that the month isn't less than or equal
   *  to 00 and greater than 12, and that the day isn't less than 00 neither greater than 31.
   *  Otherwise, throws an IllegalStateException.
   * @param date the date to be validated.
   */
  public static void checkDateForm(String date) {
    String pattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    if (!Pattern.matches(pattern, date)) {
      throw new IllegalStateException("Invalid date inputted: " + date);
    }
  }

  /**Ensures that the fromDate is before the toDate. Otherwise, an IllegalArgument Exception
   * is thrown.
   * @param fromDate the date to start (the 'from' date).
   * @param toDate the date to end (the 'to' date).
   */
  public static void checkFromDateBeforeToDate(String fromDate, String toDate) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate fromDateValue = LocalDate.parse(fromDate, dateFormatter);
    LocalDate toDateValue = LocalDate.parse(toDate, dateFormatter);
    if (fromDateValue.isAfter(toDateValue)) {
      throw new IllegalArgumentException("From date should be before to date.");
    }
  }
}
