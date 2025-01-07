package managestocks.commands;

import java.util.Scanner;

import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of calculating the value of a portfolio given its
 * portfolio name.*/
public class PortfolioValue extends Commands {

  //delegates the calculation to the model.
  //throws an IllegalStateException if the date is invalid.
  @Override
  public void run(IModel model, IView view, Scanner scanner) {
    String portfolioName = super.portfolioNameInput(model, view, scanner);
    String date = super.dateInput(model, view, scanner, "to when you want to" +
            " calculate the" + " value of the portfolio");
    try {
      view.writeMessage("portfolio " + portfolioName + " value: $" +
              model.calculatePortfolioValue(portfolioName, date) + System.lineSeparator());
    }
    catch (Exception e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
