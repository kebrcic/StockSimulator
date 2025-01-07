package managestocks.view;


import java.util.Map;

/**
 * This interface defines the methods for the graphical user interface (GUI) of the stocks program.
 * It includes methods for displaying messages, setting visibility, and retrieving user input.
 */
public interface IGUIView {

  /**
   * Sets the visibility of the GUI.
   */
  public void setVisible();

  /**
   * Displays a message to the user in a dialog box.
   *
   * @param message the message to be displayed.
   * @param title   the title of the dialog box.
   * @param pane    the type of message (e.g., information, error).
   */
  public void showMessage(String message, String title, int pane);

  /**
   * Displays the composition of a portfolio formatted on the console.
   *
   * @param composition represents a hashmap that maps each ticker symbol of every stock in the
   *                    portfolio to the number of shares of each.
   * @param units       represents the units of the values in the portfolio.
   */
  public void displayPortfolio(Map<String, Double> composition, String units);

  /** Clears the text fields on the buy/sell section of the GUI.*/
  void clearBuySellInputs();

  /**Sets the portfolio value text field with the corresponding portfolio value.
   *
   * @param value the value of the provided portfolio at a given date. String because it contains
   *              the units, '$'.
   */
  public void setPortfolioValue(String value);

  /**
   * Adds features to the GUI by setting up the appropriate action listeners for user interactions.
   * This method associates the provided `IViewListeners` feature with the corresponding buttons
   * and actions in the GUI, enabling the controller to handle user inputs.
   *
   * @param feature the controller implementation of `IViewListeners` that contains the logic
   *                for handling user actions like buying/selling stocks, creating portfolios,
   *                querying portfolio value, and getting portfolio composition.
   */
  public void addFeatures(IViewListeners feature);

  //--------------------------------------ACCESSORS-----------------------------------------------

  /**
   * Gets the date input from the user.
   *
   * @return the date as a string.
   */
  String getDate();

  /**
   * Gets the portfolio name input from the user.
   *
   * @return the portfolio name as a string.
   */
  String getPortfolioName();

  /**
   * Gets the portfolio name query input from the user.
   *
   * @return the portfolio name query as a string.
   */
  String getPortfolioNameQuery();

  /**
   * Gets the date query input from the user.
   *
   * @return the date query as a string.
   */
  String getDateQuery();

  /**
   * Gets the number of stock shares input from the user.
   *
   * @return the number of shares as an integer.
   */
  int getStockNumber();

  /**
   * Gets the stock name input from the user.
   *
   * @return the stock name as a string.
   */
  String getStockName();

}

