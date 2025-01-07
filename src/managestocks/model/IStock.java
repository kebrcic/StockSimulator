package managestocks.model;


import java.util.ArrayList;

/**
 * Represents a stock in the financial market.
 * This interface provides methods for managing and retrieving information
 * about a stock, including its historical prices and trading volumes.
 */
public interface IStock {

  /**
   * Given the date, the open price, closing price, high price, low price, and the volume of
   * a Stock, stores the data on data structures for further processing.
   *
   * @param date   the timestamp of the stock.
   * @param open   the opening price of the stock on the given date.
   * @param high   the highest value of the stock on the given date.
   * @param low    the lowest value of the stock on the given date.
   * @param close  the closing price of the stock on the given date.
   * @param volume the number of stocks traded during the given date.
   */
  public void populate(String date, double open, double high, double low, double close, int volume);

  /**
   * Gets the ticker name of the stock.
   *
   * @return a String representing the ticker symbol of the stock.
   */
  public String getTicker();

  /**
   * Gets this stock's closing price for the given date.
   *
   * @param date desired date.
   * @return the closing price of this stock in the given date.
   */
  public double getClosingPrice(String date);

  /**Finds the index of the given date on the arrayList.
   * @param date the date to return the index of.
   * @return an Integer object representing the index of the given date in the arrayList of dates of
   *             Stock object.
   */
  public int getDateIndex(String date);

  /**Calculates the number of timestamps of the stock.
   *
   * @return an integer representing size of the arrayList of dates.
   */
  public int getDataSize();

  /**Gets the timestamps on a stock.
   *
   * @return an ArrayList of String which is a copy of the "dates" stores on the Stock object.
   */
  public ArrayList<String> getDates();
}

