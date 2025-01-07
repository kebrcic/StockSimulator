package managestocks.controller;

import java.util.Objects;
import java.util.Scanner;

import managestocks.model.IStock;
import managestocks.model.Stock;

/**Class responsible for reading stock files and translates them into IStock objects usable by the
 *  program.*/
public class StockGenerator {
  private final Readable readable;

  /**Initialises the readable to the given Readable object.
   *
   * @param readable Readable object representing the data retrieved either from the API or a CSV
   *                 file.
   */
  public StockGenerator(Readable readable) {
    this.readable = Objects.requireNonNull(readable);
  }

  /**
   Translates this StockGenerator into an object that is of type IStock.*
   @param ticker represents the ticker symbol of the stock being created. Example: "GOOG"
   @return an IStock object containing all the stock's data, usable by the rest of the program.
   */
  public IStock makeStock(String ticker) {
    IStock stock = new Stock(ticker);
    Scanner scanner = new Scanner(this.readable);
    if (scanner.hasNext()) {
      //SKIPS HEADER LINE
      scanner.next();
    }
    while (scanner.hasNext()) {
      try {
        String input = scanner.next();
        String[] inputs = input.split(",");
        double openPrice = Double.parseDouble(inputs[1]);
        double highPrice = Double.parseDouble(inputs[2]);
        double lowPrice = Double.parseDouble(inputs[3]);
        double closePrice = Double.parseDouble(inputs[4]);
        int volume = Integer.parseInt(inputs[5]);

        String date = inputs[0];
        String[] datePart = date.split("-");
        stock.populate(date, openPrice, highPrice, lowPrice, closePrice, volume);
      }
      catch (Exception e) {
        throw new IllegalStateException("Invalid ticker symbol: " + ticker +
                System.lineSeparator());
      }
    }
    return stock;
  }


}

