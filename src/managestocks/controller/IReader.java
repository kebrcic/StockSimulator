package managestocks.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import static managestocks.controller.CSVReader.convertToReadable;

/**Interface to deal with obtaining stock data from database using AlphaVantage API, then processing
data into Readable format, and conversion of stored CSV files into Readable.
*/
public interface IReader {

  /**
  AlphaVantage API. This method uses the API to obtain the desired stock data.*
  @param tickerSymbol The desired stock ticker symbol, such as "GOOG" for Google.
  @return a Readable that contains the stock's entire historic data in CSV
          (comma-separated-values) format.
  */
  public static Readable makeReadable(String tickerSymbol) {
    //the API key needed to use this web service.
    //Please get your own free API key here: https://www.alphavantage.co/
    //Please look at documentation here: https://www.alphavantage.co/documentation/
    String apiKey = "K8KHJR1NUZNEMFX1";
    String stockSymbol = tickerSymbol; //ticker symbol for Google
    URL url = null;

    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char)b);
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return new StringReader(output.toString());
  }

  /**
   * Accesses the processed values for a stock.
   * If the stock has already been previously accessed through the API, its stored file is accessed
   * and processed. This avoids repeatedly using API requests for previously accessed stocks.
   * If the stock has not been previously accessed, uses the API to obtain the stock, processes
   * it, and stores a file for this stock
   *
   * @param tickerSymbol the desired stock
   * @return the processed stock
   */
  public static Readable accessReadable(String tickerSymbol) {
    String stockFilePath = System.getProperty("user.home") + "/StocksProgram/res/stocks/" +
            tickerSymbol + ".csv";
    File stockFile = new File(stockFilePath);
    if (stockFile.exists()) {
      return convertToReadable(stockFile);
    }
    else {
      try {
        Readable stock = makeReadable(tickerSymbol); //readable - StringReader
        String stockAsString = extractReadable((StringReader) stock);
        FileWriter writer = new FileWriter(stockFilePath);
        writer.write(stockAsString);
        writer.close();
        return stock;
      }
      catch (Exception e) {
        throw new IllegalArgumentException("Something went wrong when creating a file" +
                " for the stock");
      }
    }
  }

  /**Extracts the readable and turns the given StringReader into a String.*/
  private static String extractReadable(StringReader stock) throws IOException {
    StringBuilder result = new StringBuilder();
    int a;
    while ((a = stock.read()) != -1) {
      result.append((char) a);
    }
    stock.reset();
    return result.toString();
  }

}


