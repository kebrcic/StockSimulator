package managestocks.commands;

import java.util.Scanner;

import managestocks.controller.Utils;
import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of calculating the crossover of a stock.*/
public class CrossOver extends Commands {

  //delegates the calculation to the model.
  //throws an IllegalStateException if the date is invalid or if the number of crossover days
  //is negative.
  @Override
  public void run(IModel model, IView view, Scanner scanner) {
    int numDays = 0;

    //Getting the tickerSymbol inputted by the user.
    String loadedTickerSymbol = super.tickerSymbolInput(model, view, scanner);
    //Getting the date
    String dateFrom = super.dateInput(model, view, scanner, "");

    view.writeMessage("Type the number of crossover days: " + System.lineSeparator());
    if (scanner.hasNext()) {
      numDays = scanner.nextInt();
      Utils.checkInputNonNegative(numDays);
    }

    try {
      view.writeMessage("Crossover days: " + model.crossover(loadedTickerSymbol, dateFrom, numDays)
              + System.lineSeparator());
    }
    catch (Exception e) {
      view.writeMessage(e.getMessage() + System.lineSeparator());
    }
  }
}
