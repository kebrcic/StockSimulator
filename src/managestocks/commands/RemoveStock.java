package managestocks.commands;

import java.util.Scanner;

import managestocks.controller.Utils;
import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of removing a stock from a portfolio (essentially
 *  selling it).*/
public class RemoveStock extends Commands {

  //delegates the calculation to the model.
  //throwing an IllegalStateException if any input is not in the required form.
  @Override
  public void run(IModel model, IView view, Scanner scanner) {
    //Getting the tickerSymbol inputted by the user.
    String loadedTickerSymbol = super.tickerSymbolInput(model, view, scanner);
    String portfolioName = super.portfolioNameInput(model, view, scanner);
    //string initially to check for an overflow
    int shares = 0;

    view.writeMessage("Type the amount of shares to sell: ");
    if (scanner.hasNext()) {
      shares = scanner.nextInt();
      Utils.checkInputNonNegative(shares);
    }
    String date = super.dateInput(model, view, scanner, "");

    try {
      model.removeStock(portfolioName, loadedTickerSymbol, shares, date);
      view.writeMessage(date + ": Removed " + shares + " " + loadedTickerSymbol + " stocks " +
              "from " + portfolioName + System.lineSeparator());
    }
    catch (Exception e) {
      view.writeMessage(e.getMessage() + System.lineSeparator());
    }
  }

}

