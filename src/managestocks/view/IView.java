package managestocks.view;

import java.util.Map;

/**Represents the interface that handles the rendering to the user.*/
public interface IView {

  /**Displays any message to the screen using the Appendable field out.
   *
   * @param message the String message to be rendered on the console.
   */
  void writeMessage(String message);

  /**Displays the composition of a portfolio on the console formatted.
   *
   * @param composition represents a hashmap that maps each tickerSymbol of every stock on the
   *                    portfolio to the number of shares of each.
   *
   */
  void displayPortfolio(Map<String, Double> composition, String units);

  /**
   * Displays a horizontal bar chart containing: a title, a scale, and the dates on the time span
   * calculated with the corresponding portfolio values scaled and represented by a given symbol.
   *
   * @param datesToScaledPValues map of dates to the scaled portfolio values.
   * @param scale the scale of the performance of the portfolio
   * @param portfolioName the name of the portfolio, the performance was calculated on
   * @param dateF the starting date
   * @param dateT the ending date
   * @param scaleSymbol the String symbol used to represent the scaled portfolio values.
   */
  void displayPerformance(Map<String, Integer> datesToScaledPValues, int scale,
                          String portfolioName, String dateF, String dateT, String scaleSymbol);

}
