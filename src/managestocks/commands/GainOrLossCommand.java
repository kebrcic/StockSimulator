package managestocks.commands;

import java.util.Scanner;

import managestocks.controller.Utils;
import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of determining the gain or loss (in value) of a stock
 *  over a given period of time.*/
public class GainOrLossCommand extends Commands {

  //delegates the calculation to the model.
  //throws an IllegalStateException if the dates are invalid.
  @Override
  public void run(IModel model, IView view, Scanner scanner) {

    //Getting the tickerSymbol inputted by the user.
    String loadedTickerSymbol = super.tickerSymbolInput(model, view, scanner);

    //Getting and validating correctness on dates inputted.
    String dateFrom = super.dateInput(model, view, scanner, "from");
    String dateTo = super.dateInput(model, view, scanner, "to");
    Utils.checkFromDateBeforeToDate(dateFrom, dateTo);

    try {
      view.writeMessage("Gain: $" + model.gainOrLoss(loadedTickerSymbol, dateFrom, dateTo) +
              System.lineSeparator());
    }
    catch (Exception e) {
      view.writeMessage(e.getMessage() + System.lineSeparator());
    }
  }
}
