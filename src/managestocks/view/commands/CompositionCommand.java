package managestocks.view.commands;

import java.util.Map;

import javax.swing.JOptionPane;

import managestocks.view.IGUIView;
import managestocks.view.IViewListeners;

/**
 * This class represents a command to get the composition of a portfolio.
 */
public class CompositionCommand implements Commands {
  private IViewListeners feature;
  private IGUIView view;

  /**
   * Constructs a CompositionCommand with the specified feature and view.
   *
   * @param feature the feature to handle getting the composition.
   * @param view    the view to display the composition.
   */
  public CompositionCommand(IViewListeners feature, IGUIView view) {
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
        Map<String, Double> composition = feature.handleGetPortfolioComposition(view.
                getPortfolioNameQuery(), view.getDateQuery());
        view.displayPortfolio(composition, "");
      } catch (Exception ex) {
        view.showMessage(ex.getMessage(), "Error getting portfolio composition",
                JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
