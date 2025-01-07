package managestocks.controller;

import managestocks.commands.PortfolioComposition;
import managestocks.commands.PerformancePortfolio;
import managestocks.commands.PortfolioDistribution;
import managestocks.commands.RebalancePortfolio;
import managestocks.commands.RemoveStock;
import managestocks.model.IModel;
import managestocks.view.IView;

/**Represents the new controller of the StockModel program, which includes more commands.
 *  Receives requests from the user and processes them on the model.*/
public class SmartControllerImpl extends ControllerImpl {
  /**
   * Initialises the parameters out, and in, as well as the hashMap commands to store the String
   * representing the command key, and the value being the corresponding function object.
   *
   * @param view Represents an Appendable object to display in the screen to the user
   * @param in   Represents a Readable object to read from the user
   */
  public SmartControllerImpl(IView view, Readable in) {
    super(view, in);
    commands.put("loadportfolio", new PortfolioComposition());
    commands.put("rebalance", new RebalancePortfolio());
    commands.put("portfolioperformance", new PerformancePortfolio());
    commands.put("sellstock", new RemoveStock());
    commands.put("portfoliocomposition", new PortfolioComposition());
    commands.put("portfoliodistribution", new PortfolioDistribution());
  }

  @Override
  public void runModel(IModel model) {
    super.runModel(model);
  }

  @Override
  void displayMenu() {
    super.displayMenu();
    view.writeMessage("SellStock" + System.lineSeparator());
    view.writeMessage("PortfolioComposition" + System.lineSeparator());
    view.writeMessage("PortfolioDistribution" + System.lineSeparator());
    view.writeMessage("Rebalance" + System.lineSeparator());
    view.writeMessage("PortfolioPerformance" + System.lineSeparator());
    view.writeMessage("Exit [type e]" + System.lineSeparator());
  }


}
