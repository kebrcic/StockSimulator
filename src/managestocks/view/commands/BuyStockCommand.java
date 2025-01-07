package managestocks.view.commands;

import javax.swing.JOptionPane;

import managestocks.view.IGUIView;
import managestocks.view.IViewListeners;

/**
 * This class represents a command to buy stocks for a portfolio.
 */
public class BuyStockCommand implements Commands {
  private IViewListeners feature;
  private IGUIView view;

  /**
   * Constructs a BuyStockCommand with the specified feature and view.
   *
   * @param feature the feature to handle buying the stock.
   * @param view    the view to display messages.
   */
  public BuyStockCommand(IViewListeners feature, IGUIView view) {
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
        feature.handleBuyStock(view.getPortfolioName(), view.getStockName().toUpperCase(),
                view.getStockNumber(), view.getDate());
        view.showMessage("Bought " + view.getStockNumber() + " shares of " +
                view.getStockName(), "Buying Stock", JOptionPane.INFORMATION_MESSAGE);
        view.clearBuySellInputs();
      } catch (Exception ex) {
        view.showMessage(ex.getMessage(), "Error buying stock: ", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
