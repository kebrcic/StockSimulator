package managestocks.commands;

import java.util.Scanner;

import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of creating a portfolio.*/
public class CreatePortfolio extends Commands {

  //delegates the operation to the model.
  //throws an IllegalStateException if a portfolio with the given name already exists.
  @Override
  public void run(IModel model, IView view, Scanner scanner) {
    String portfolioName = "";
    view.writeMessage("name of portfolio: " + System.lineSeparator());
    if (scanner.hasNext()) {
      portfolioName = scanner.next();
    }
    try {
      model.createPortfolio(portfolioName);
      view.writeMessage("portfolio " + portfolioName + " successfully created"
              + System.lineSeparator());
    }
    catch (Exception e) {
      throw new IllegalStateException("Unable to create portfolio");
    }
  }
}
