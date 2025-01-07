package managestocks.view.commands;

import javax.swing.JOptionPane;

import managestocks.view.IGUIView;
import managestocks.view.IViewListeners;

/**
 * This class represents a command to find the value of a portfolio.
 */
public class FindValueCommand implements Commands {
  private IViewListeners feature;
  private IGUIView view;

  /**
   * Constructs a FindValueCommand with the specified feature and view.
   *
   * @param feature the feature to handle finding the portfolio value.
   * @param view    the view to display messages.
   */
  public FindValueCommand(IViewListeners feature, IGUIView view) {
    this.feature = feature;
    this.view = view;
  }

  @Override
  public void execute() {
    if (view.getPortfolioNameQuery().isEmpty() || view.getDateQuery().isEmpty()) {
      view.showMessage("Fields on this section can't be left empty", "Empty fields",
              JOptionPane.ERROR_MESSAGE);
    }
    else {
      try {
        double value = feature.handleGetPortfolioValue(view.getPortfolioNameQuery(),
                view.getDateQuery());
        view.setPortfolioValue("$" + value);
      } catch (Exception ex) {
        view.showMessage(ex.getMessage(), "Error finding portfolio value",
                JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
