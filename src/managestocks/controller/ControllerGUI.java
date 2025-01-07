package managestocks.controller;

import java.util.List;
import java.util.Map;


import managestocks.model.IModel;
import managestocks.model.IPortfolio;
import managestocks.model.IStock;
import managestocks.view.IGUIView;

import managestocks.view.IViewListeners;


/**
 * This class represents the GUI controller for the Stocks Program.
 * It extends the abstract Controller class and implements the IViewListeners interface.
 * It handles the interaction between the model and the GUI view.
 */
public class ControllerGUI extends Controller implements IViewListeners {
  private final IModel model;
  private IGUIView view;

  /**
   * Constructs a ControllerGUI with the specified model, input source, and command line view.
   *
   * @param model            the model to be used by the controller.
   * @throws NullPointerException if the input source is null.
   */
  public ControllerGUI(IModel model) {
    this.model = model;
  }

  /**
   * Sets the view for this controller.
   *
   * @param view the IGUIView to be associated with this controller.
   */
  protected void setView(IGUIView view) {
    this.view = view;
  }

  @Override
  public void runModel(IModel model) {
    super.setUp();
    if (view != null) {
      view.setVisible();
    }
  }

  @Override
  public void handleCreatePortfolio(String name) {
    try {
      this.fetchAndLoadPortfolio(name);
      throw new RuntimeException();
    }
    catch (Exception e) {
      model.createPortfolio(name);
    }
  }

  @Override
  public double handleGetPortfolioValue(String name, String date) {
    this.fetchAndLoadPortfolio(name);
    Utils.checkDateForm(date);
    double value = model.calculatePortfolioValue(name, date);
    return value;
  }

  @Override
  public Map<String, Double> handleGetPortfolioComposition(String name, String date) {
    this.fetchAndLoadPortfolio(name);
    Utils.checkDateForm(date);
    Map<String, Double> composition = model.portfolioComposition(name, date);
    return composition;
  }

  @Override
  public void handleBuyStock(String portfolioName, String tickerSymbol, int shares, String date)
          throws Exception {
    this.fetchAndLoadStock(tickerSymbol);
    this.fetchAndLoadPortfolio(portfolioName);
    Utils.checkDateForm(date);
    Utils.checkInputNonNegative(shares);
    model.addStock(portfolioName, tickerSymbol, shares, date);
    //view.sendMessageToScreen(date + ": Bought " + shares + " " + tickerSymbol +
    //  " stocks", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void handleSellStock(String portfolioName, String tickerSymbol, int shares, String date) {
    this.fetchAndLoadStock(tickerSymbol);
    this.fetchAndLoadPortfolio(portfolioName);
    Utils.checkDateForm(date);
    Utils.checkInputNonNegative(shares);
    model.removeStock(portfolioName, tickerSymbol, shares, date);
    //view.sendMessageToScreen(date + ": Sold " + shares + " " + tickerSymbol +
    //  " stocks", JOptionPane.INFORMATION_MESSAGE);
    // view.sendMessageToScreen(e.getMessage(), JOptionPane.ERROR_MESSAGE);
  }

  private void fetchAndLoadPortfolio(String portfolioName) {
    try {
      IPortfolio portfolio = model.loadPortfolio(portfolioName);
      model.populate(portfolio);
      List<String> portfolioTickers = model.getTickerFromPortfolio(portfolioName);
      for (String tickerSymbol: portfolioTickers) {
        this.fetchAndLoadStock(tickerSymbol);
      }
    }
    catch (Exception e) {
      throw new IllegalStateException("Invalid portfolio name: " + portfolioName);
    }
  }

  private void fetchAndLoadStock(String tickerSymbol) {
    try {
      Readable stockReadable = IReader.accessReadable(tickerSymbol);
      StockGenerator makeStock = new StockGenerator(stockReadable);
      IStock stock = makeStock.makeStock(tickerSymbol);
      model.populate(tickerSymbol, stock);
    }
    catch (Exception e) {
      throw new IllegalStateException("Invalid ticker Symbol: " + tickerSymbol);
    }
  }

}
