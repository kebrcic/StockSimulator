package managestocks.view.commands;

import managestocks.view.IGUIView;
import managestocks.view.IViewListeners;
import javax.swing.JOptionPane;

/**
 * This class represents a command to sell stocks from a portfolio.
 */
public class SellStockCommand implements Commands {
  private IViewListeners feature;
  private IGUIView view;

  /**
   * Constructs a SellStockCommand with the specified feature and view.
   *
   * @param feature the feature to handle selling the stock.
   * @param view    the view to display messages.
   */
  public SellStockCommand(IViewListeners feature, IGUIView view) {
    this.feature = feature;
    this.view = view;
  }

  @Override
  public void execute() {
    if (view.getPortfolioName().isEmpty() ||
            view.getStockName().isEmpty() || view.getStockNumber() == 0 ||
            view.getDate().isEmpty()) {
      view.showMessage("Fields on this section can't be left empty", "Empty fields",
              JOptionPane.ERROR_MESSAGE);
    }
    else {
      try {
        feature.handleSellStock(view.getPortfolioName(), view.getStockName().toUpperCase(),
                view.getStockNumber(), view.getDate());
        view.showMessage("Sold " + view.getStockNumber() + " shares of " +
                view.getStockName(), "Selling Stock", JOptionPane.INFORMATION_MESSAGE);
        view.clearBuySellInputs();
      } catch (Exception ex) {
        view.showMessage(ex.getMessage(), "Error selling stock", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
