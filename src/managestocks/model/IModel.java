package managestocks.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**Represents a Stock Model, where the model can do the following.
 * -determine the gain or loss on a stock between two dates.
 * -calculate the moving average of a stock.
 * -calculate the crossover of a stock.
 * -create portfolios and add stocks, and remove stocks
 * -calculate the value of a portfolio on a given date.
 * -determine the composition and distribution of a portfolio.
 * -rebalance a portfolio.
 * -get the performance of a portfolio on a time-range, scaling the portfolio values accordingly.
 * */
public interface IModel {

  /**
   * Calculates if the given stock saw gain or loss over a period of time.
   *
   * @param tickerSymbol desired stock ticker symbol (example "GOOG").
   * @param dateFrom start of the desired period of time.
   * @param dateTo end of the desired period of time.
   * @return the number value the stock gained or lost during this time. Positive values
   *         represent gain and negative values represent loss.
   */
  public double gainOrLoss(String tickerSymbol, String dateFrom, String dateTo);

  /**
   * Calculates the moving average of a stock for the given number of days starting from a given
   * date.
   * @param tickerSymbol desired stock ticker symbol (example "GOOG").
   * @param date the starting date of the moving average.
   * @param days number of days of the moving average.
   * @return the moving average value as a number.
   * @throws IllegalArgumentException if there isn't enough data to go back the
   *                                  desired number of days.
   */
  public double movingAverage(String tickerSymbol, String date, int days);

  /**
   * Calculates the crossover days of a stock starting from a given date and spanning
   * over a given number of days.
   *
   * @param tickerSymbol desired stock ticker symbol (example "GOOG").
   * @param date the starting date.
   * @param x the number of days to go back.
   * @return a list of crossover days as an ArrayList of String dates in the format YYYY-MM-DD.
   * @throws IllegalArgumentException if there isn't enough data to go back the
   *                                  desired number of days.
   */
  public ArrayList<String> crossover(String tickerSymbol, String date, int x);

  /**
   * Adds a stock to a portfolio.
   *
   * @param portfolioName name of the portfolio to add the stock to.
   * @param tickerName stock ticker symbol (example "GOOG").
   * @param shares number of shares bought of a stock.
   * @param date the date when the stock was added.
   */
  public void addStock(String portfolioName, String tickerName, double shares, String date)
          throws Exception;

  /**
   * Removes a stock from a portfolio.
   *
   * @param portfolioName name of the portfolio to add the stock to.
   * @param tickerName stock ticker symbol (example "GOOG").
   * @param shares number of shares bought of a stock.
   * @param date the date when the stock was removed.
   */
  public void removeStock(String portfolioName, String tickerName, double shares, String date);

  /**
   * Calculates the total value of a portfolio on a specific date, combining all
   * stocks and their shares.
   *
   * @param portfolioName name of the portfolio to get value of.
   * @param date date to get portfolio value of. Stock values will be taken from this date.
   * @return a number representing the total value of the portfolio.
   */
  public double calculatePortfolioValue(String portfolioName, String date);

  /**
   * Adds a stock to the stock list in the model object. If the stock was already in
   * the list, does nothing.
   *
   * @param tickerSymbol stock ticker symbol (example "GOOG").
   * @param stock data for the stock.
   */
  public void populate(String tickerSymbol, IStock stock);

  /**
   * Adds a portfolio to the portfolio list in the model object. If the portfolio was already in
   * the list, does nothing.
   *
   * @param portfolio represents a portfolio.
   */
  public void populate(IPortfolio portfolio);

  /**
   * Creates a new portfolio of stocks.
   *
   * @param name the desired name of the new portfolio.
   * @throws IllegalArgumentException if another portfolio already has the inputted name.
   */
  public void createPortfolio(String name);

  /**Gets the stocks in this model.*
   @return a HashMap that maps String ticker symbols to IStock stocks.*/
  public Map<String, IStock> getStocks();

  /**
   Gets the portfolios in this model.*
   @return an ArrayList of IPortfolios.*/
  List<IPortfolio> getPortfolios();

  /** Maps the ticker symbols to the number of shares, of a portfolio on a given date.
   *
   * @param portfolioName the portfolio the user wants to determine the composition
   * @param date the date to display the composition of a portfolio.
   * @return a map representing the tickerSymbols and number of shares of stocks on the portfolio.
   */
  Map<String, Double> portfolioComposition(String portfolioName, String date);

  /** Maps the ticker symbols to the value of the shares, of a portfolio on a given date.
   *
   * @param portfolioName the portfolio the user wants to determine the distribution
   * @param date the date to display the distribution of a portfolio.
   * @return a map representing the tickerSymbols and value of shares of stocks on the portfolio.
   */
  Map<String, Double> portfolioDistribution(String portfolioName, String date);

  /**Gets all the stocks a portfolio contains.
   *
   * @param portfolioName the name of the portfolio to get the stocks from.
   * @return a List of Strings representing the tickerSymbols of the stocks inside
   *         the given portfolio
   */
  List<String> getTickerFromPortfolio(String portfolioName);

  /**
   * Obtains the stored file for a portfolio.
   *
   * @param portfolioName the name of the portfolio to be loaded.
   * @return the IPortfolio object of the stored portfolio.
   */
  public IPortfolio loadPortfolio(String portfolioName);

  /**
   * Rebalances the shares for all stocks in this IPortfolio so that the value for each stock
   * matches the given weights.
   *
   * @param portfolioName the name of the portfolio to be rebalanced.
   * @param date the specific date to rebalance.
   * @param tickerSymbolToWeights a Map that represents each stocks weight after the rebalancing.
   */
  public void rebalancePortfolio(String portfolioName, String date,
                                 Map<String, Double> tickerSymbolToWeights);

  /** calculates the timespan, the scale and maps the dates on each timespan within the date range
   * to the corresponding value of the portfolio at that date; essentially representing the
   * performance ot the portfolio over a given time-range.
   *
   * @param portfolioName the portfolio name to determine the performance
   * @param dateFrom the starting date to determine the performance of the portfolio
   * @param dateTo the ending date to determine the performance of the portfolio.
   * @return A map representing the dates that follow the calculated timespan between the starting
   *         and ending date, which map to the scaled portfolio values on each of these dates.
   */
  public Map<String, Integer> performancePortfolio(String portfolioName, String dateFrom,
                                                   String dateTo);

  /**Gets the scale on the model, used to determine the performance of a portfolio.
   *
   * @return an integer number representing the scale calculated to be used on displaying
   *         the performance on a portfolio.
   */
  public int getScale();

  /**
   * Returns the portfolio with the given name.
   * @param portfolioName the name of the portfolio the user wants to retrieve.
   * @return the corresponding portfolio with the given name.
   */
  public IPortfolio findPortfolio(String portfolioName);



}