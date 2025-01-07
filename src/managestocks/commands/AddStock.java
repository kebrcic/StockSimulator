package managestocks.commands;

import java.util.Scanner;


import managestocks.controller.Utils;
import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of adding a stock to a portfolio (essentially
 *  buying it).*/
public class AddStock extends Commands {

  //delegates the calculation to the model.
  //throwing an IllegalStateException if any input is not in the required form.
  @Override
  public void run(IModel model, IView view, Scanner scanner) {
    //string initially to check for an overflow
    int shares = 0;
    String date = "";

    //Getting the tickerSymbol inputted by the user.
    String loadedTickerSymbol = super.tickerSymbolInput(model, view, scanner);
    //Getting the portfolio name inputted by the user.
    String portfolioName = super.portfolioNameInput(model, view, scanner);

    view.writeMessage("Type the amount of shares: ");
    if (scanner.hasNext()) {
      shares = scanner.nextInt();
      Utils.checkInputNonNegative(shares);
    }

    date = super.dateInput(model, view, scanner, "");

    try {
      model.addStock(portfolioName, loadedTickerSymbol, shares, date);
      view.writeMessage(date + ": Added " + shares + " " + loadedTickerSymbol + " stocks " +
              "to " + portfolioName + System.lineSeparator());
    }
    catch (Exception e) {
      view.writeMessage(e.getMessage() + System.lineSeparator());
    }
  }
}
