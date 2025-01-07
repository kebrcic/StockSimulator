package managestocks.commands;

import java.util.Scanner;

import managestocks.controller.Utils;
import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of calculating the moving average of a stock
 *  on a given date.*/
public class MovingAverage extends Commands {

  //delegates the calculation to the model.
  //throws an IllegalStateException if the date is invalid.
  @Override
  public void run(IModel model, IView view, Scanner scanner) {
    int days = 0;

    //Getting the tickerSymbol inputted by the user.
    String loadedTickerSymbol = super.tickerSymbolInput(model, view, scanner);
    //Getting the date
    String date = super.dateInput(model, view, scanner, "");

    view.writeMessage("Type number of days to go back in the moving average: ");
    if (scanner.hasNext()) {
      //CHECK IF DATEFROM IS CORRECT
      days = scanner.nextInt();
      Utils.checkInputNonNegative(days);
    }

    try {
      view.writeMessage("Moving Average: $" + model.movingAverage(loadedTickerSymbol, date, days)
              + System.lineSeparator());
    }
    catch (Exception e) {
      view.writeMessage(e.getMessage() + System.lineSeparator());
    }


  }
}
