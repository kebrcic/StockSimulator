package managestocks.view;

import java.util.Map;


/**
 * Interface representing the listeners for view events in the Stocks Program.
 * Provides methods to handle various user actions such as creating portfolios,
 * buying/selling stocks, and querying portfolio information. Represents a
 * part of the controller that the GUI can use.
 */
public interface IViewListeners {

  /**
   * Creates a new portfolio with the provided name.
   * @param name the name of the new portfolio.
   */
  public void handleCreatePortfolio(String name);

  /**
   * Queries the value of a portfolio.
   * @param name the name of the portfolio the user wishes to get the value of.
   * @param date the date at which the user wants to find the value of a portfolio.
   * @return a double representing the overall value of the portfolio at the provided date.
   */
  public double handleGetPortfolioValue(String name, String date);

  /**
   * Queries the composition of a portfolio.
   * @param name the name of the portfolio the user wishes to get the composition of.
   * @param date the date at which the user wants to find the composition of a portfolio.
   * @return a map containing the ticker symbols of the purchased stocks and their corresponding
   *                  number of shares.
   */
  public Map<String, Double> handleGetPortfolioComposition(String name, String date);

  /**
   * Buys a stock and adds it to a portfolio at a given date.
   * @param portfolioName the name of the portfolio the user wants to add the purchased stock on.
   * @param tickerSymbol the stock the user wants to buy.
   * @param shares the number of shares to buy.
   * @param date the buying date.
   * @throws Exception if an error occurs while buying the stock.
   */
  public void handleBuyStock(String portfolioName, String tickerSymbol, int shares, String date)
          throws Exception;

  /**
   * Sells a stock from a portfolio at a given date.
   * @param portfolioName the name of the portfolio the user wants to sell stocks of.
   * @param tickerSymbol the stock the user wants to sell.
   * @param shares the number of shares to sell.
   * @param date the selling date.
   */
  public void handleSellStock(String portfolioName, String tickerSymbol, int shares, String date);
}
