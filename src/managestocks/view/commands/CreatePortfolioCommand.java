package managestocks.view.commands;

import managestocks.view.IGUIView;
import managestocks.view.IViewListeners;
import javax.swing.JOptionPane;

/**
 * This class represents a command to create a new portfolio.
 */
public class CreatePortfolioCommand implements Commands {
  private IViewListeners feature;
  private IGUIView view;

  /**
   * Constructs a CreatePortfolioCommand with the specified feature and view.
   *
   * @param feature the feature to handle creating the portfolio.
   * @param view    the view to display messages.
   */
  public CreatePortfolioCommand(IViewListeners feature, IGUIView view) {
    this.feature = feature;
    this.view = view;
  }

  @Override
  public void execute() {
    if (view.getPortfolioNameQuery().isEmpty()) {
      view.showMessage("Portfolio name field is blank", "Empty fields",
              JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        feature.handleCreatePortfolio(view.getPortfolioNameQuery());
        view.showMessage("Portfolio " + view.getPortfolioName() + " successfully created",
                "Creating Portfolio", JOptionPane.INFORMATION_MESSAGE);
      } catch (Exception ex) {
        view.showMessage(ex.getMessage(), "Error creating portfolio", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
