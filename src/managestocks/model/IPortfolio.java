package managestocks.model;

import java.util.List;
import java.util.Map;

/**
 * Represents any type of portfolio of stocks.
 * This interface provides methods for managing and retrieving information
 * about a collection of stocks within a portfolio.
 */
public interface IPortfolio {

  /**Adds a stock to the portfolio.
   * @param tickerSymbol represents the company to which the user is willing to add stocks.
   * @param shares represents the number of shares willing to purchase for the given company
   *              "stock".*/
  public void addStock(String tickerSymbol, double shares, String date) throws Exception;

  /**Finds the value of the portfolio.
   * @param date represents the value of the portfolio on this date, (the value of each stock on the
   *             portfolio on this date).
   * @return a double, representing the value of the stocks in the portfolio on the provided date
   *             multiplied by the number of shares on each stock.
   */
  public double findValueOfPortfolio(String date, Map<String, IStock> stocks);

  /**retrieves the stockHoldings on the portfolio.
   * @return a copy of all the stocks and corresponding shares currently on the portfolio.*/
  public Map<String, Double> getStockHoldings();

  /**Gets the name of the portfolio.
   * @return a String representing the name assigned to the portfolio.*/
  public String getName();

  /**
   * Loads a portfolio stored in the system files.
   *
   * @param fileName name of the desired portfolio.
   * @return The IPortfolio stored in the system file.
   * @throws Exception if there is no stored portfolio with the given name
   */
  public IPortfolio loadPortfolio(String fileName) throws Exception;


  /**
   * Gets a copy of an IPortfolio's tickerHoldings.
   *
   * @return a map that associates stock ticker symbols to a map that associates this
   *         stock's buy dates to the number of shares bought.
   */
  public Map<String, Map<String, Double>> getTickerHoldings();

  /**
   * Saves this IPortfolio into the system files in the program user's user folder.
   *
   * @throws Exception if the program user's user folder can't be found.
   */
  public void savePortfolio() throws Exception;

  /**
   * Gets this IPortfolio's stock distribution at a specific date, including the total
   * value for each owned stock.
   *
   * @param date the specific date to get the distribution on.
   * @param stocks the model's data for all stocks in this IPortfolio.
   * @return a Map that associates owned stock ticker symbols with the stock's total value
   *         in this IPortfolio.
   */
  public Map<String, Double> portfolioDistribution(String date, Map<String, IStock> stocks);

  /**
   * Gets this IPortfolio's stock composition at a specific date, including the total number
   * of shares for each owned stock.
   *
   * @param date the specific date to get the composition on.
   * @param stocks the model's data for all stocks in this IPortfolio.
   * @return a Map that associates owned stock ticker symbols with the stock's total number of
   *         owned shares in this IPortfolio.
   */
  public Map<String, Double> portfolioComposition(String date, Map<String, IStock> stocks);

  /**
   * Sells a specific stock on a specific date.
   *
   * @param tickerSymbol the stock's ticker symbol.
   * @param shares the number of shares to sell.
   * @param date the date to sell this stock on.
   * @throws Exception if this IPortfolio didn't have enough shares to sell the given number of
   *                   shares in the given date, or if the given ticker symbol is invalid, or
   *                   if there is no data for the given stock in the given date, or if
   *                   the given number of shares is smaller than 0.
   */
  public void sellStock(String tickerSymbol, double shares, String date) throws Exception;

  /**
   * Rebalances the shares for all stocks in this IPortfolio so that the value for each stock
   * matches the given weights.
   *
   * @param date the specific date to rebalance.
   * @param weights an array of doubles representing the weights for each stock.
   * @param stocks the model's data for stocks in this IPortfolio.
   */
  void rebalance(String date, double[] weights, Map<String, IStock> stocks);

  /**
   * Gets all ticker symbols in this IPortfolio.
   *
   * @return a list of all ticker symbols in String format.
   */
  public List<String> getTickerSymbols();

  /**
   * Gets this IPortfolio's history of sales.
   *
   * @return a List of four Lists. The list at index 0 contains all sell dates, the list at
   *         index 1 contains all stock ticker symbols, the list at index 2 contains the
   *         number of shares sold, and list the at index 4 contains all buy dates.
   */
  List<List<String>> getHistory();
}
