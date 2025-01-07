package managestocks.commands;

import java.util.List;
import java.util.Scanner;


import managestocks.controller.IReader;
import managestocks.controller.StockGenerator;
import managestocks.controller.Utils;
import managestocks.model.IModel;
import managestocks.model.IPortfolio;
import managestocks.model.IStock;
import managestocks.view.IView;

/**Represents all commands that the user can trigger and some common functionalities that most or
 *  all specific commands require:
 * -Input validation
 * -Loading portfolio
 * -Loading stock.*/
public abstract class Commands implements ICommand {

  //fetch the portfolio from the files and check if it exists.
  //and populates the model
  void fetchAndLoadPortfolio(IModel model, String portfolioName, IView view) {
    try {
      IPortfolio portfolio = model.loadPortfolio(portfolioName);
      model.populate(portfolio);
      List<String> portfolioTickers = model.getTickerFromPortfolio(portfolioName);
      for (String tickerSymbol: portfolioTickers) {
        this.fetchAndLoadStock(model, tickerSymbol, view);
      }
    }
    catch (Exception e) {
      throw new IllegalStateException("Invalid portfolio name: " + portfolioName);
    }
  }

  //fetch the stock. Populate the model with the tickerSymbol inputted by the user and the stock
  //info fetched either from a file or the API.
  void fetchAndLoadStock(IModel model, String tickerSymbol, IView view) {
    try {
      Readable stockReadable = IReader.accessReadable(tickerSymbol);
      StockGenerator makeStock = new StockGenerator(stockReadable);
      IStock stock = makeStock.makeStock(tickerSymbol);
      model.populate(tickerSymbol, stock);
    }
    catch (Exception e) {
      throw new IllegalStateException("Invalid ticker Symbol: " + tickerSymbol);
    }
  }

  String tickerSymbolInput(IModel model, IView view, Scanner s) {
    view.writeMessage("Ticker symbol: ");
    String tickerSymbol = "";
    if (s.hasNext()) {
      tickerSymbol = s.next().toUpperCase();
    }
    this.fetchAndLoadStock(model, tickerSymbol, view);
    return tickerSymbol;
  }

  String portfolioNameInput(IModel model, IView view, Scanner s) {
    view.writeMessage("Portfolio name: ");
    String portfolioName = "";
    if (s.hasNext()) {
      portfolioName = s.next();
    }
    this.fetchAndLoadPortfolio(model, portfolioName, view);
    return portfolioName;
  }

  String dateInput(IModel model, IView view, Scanner s, String typeDate) {
    int year = 1;
    int month = 1;
    int day = 1;
    String date = "";
    view.writeMessage("Date " + typeDate + ": " + System.lineSeparator());
    view.writeMessage("Year(yyyy): ");
    if (s.hasNext()) {
      year = s.nextInt();
      Utils.checkInputNonNegative(year);
    }
    view.writeMessage("Month(mm): ");
    if (s.hasNext()) {
      month = s.nextInt();
      Utils.checkInputNonNegative(month);
    }
    view.writeMessage("Day: ");
    if (s.hasNext()) {
      day = s.nextInt();
      Utils.checkInputNonNegative(day);
    }

    String dayS = day + "";
    String monthS = month + "";
    if (monthS.length() == 1) {
      monthS = "0" + monthS;
    }
    if (dayS.length() == 1) {
      dayS = "0" + dayS;
    }

    date = year + "-" + monthS + "-" + dayS;
    Utils.checkDateForm(date);
    return date;
  }

}

