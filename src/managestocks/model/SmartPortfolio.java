package managestocks.model;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents a SmartPortfolio, which has all capabilities of a regular Portfolio but has additional
 * functionalities, such as supporting specific dates for buying and selling stocks, finding the
 * value, composition, performance and distribution, rebalancing, and creating portfolios.
 *
 */
public class SmartPortfolio implements IPortfolio {
  private final String name;
  private Map<String, Map<String, Double>> tickerHoldings;
  private final List<List<String>> history;

  /**
   * Initializes an empty SmartPortfolio.
   *
   * @param name of this portfolio to support saving and loading it.
   */
  public SmartPortfolio(String name) {
    this.name = name;
    tickerHoldings = new HashMap<String, Map<String, Double>>();
    history = new ArrayList<>();
    history.add(new ArrayList<String>()); //index 0 represents date of sale
    history.add(new ArrayList<String>()); //index 1 represents tickerSymbol of stock
    history.add(new ArrayList<String>()); //index 2 represents shares sold
    history.add(new ArrayList<String>()); //index 3 represents original date of buying
  }

  //--------------------------------FILE RELATED METHODS-------------------------------------------

  @Override
  public IPortfolio loadPortfolio(String fileName) throws Exception {
    Gson mapper = new Gson();
    String filePath = System.getProperty("user.home") + "/StocksProgram/res/portfolios/" +
            fileName + ".txt";
    FileInputStream in = new FileInputStream(filePath);
    int b;
    StringBuilder output = new StringBuilder();

    while ((b = in.read()) != -1) {
      output.append((char) b);
    }
    File logFile = new File(System.getProperty("user.home") +
            "/StocksProgram/res/portfolios/" +
            fileName + "Log.txt");

    return mapper.fromJson(output.toString(), SmartPortfolio.class);
  }

  @Override
  public void savePortfolio() throws Exception {
    Gson mapper = new Gson();
    String filePath = System.getProperty("user.home") + "/StocksProgram/res/portfolios/" +
            this.name + ".txt";
    File maybeOldFile = new File(filePath);
    if (!maybeOldFile.exists()) {
      maybeOldFile.delete();
    }
    try {
      String stringPortfolio = mapper.toJson(this);
      File file = new File(filePath);
      FileWriter writer = new FileWriter(filePath);
      writer.write(stringPortfolio);
      writer.close();
    } catch (Exception e) {
      throw new IllegalArgumentException("Something went wrong when creating " +
              "the file for the portfolio. Check folder path is correct. (" + e.getMessage() + ")");
    }
  }

  //--------------------------------ACTUAL FUNCTIONS----------------------------------------------

  @Override
  public void addStock(String tickerSymbol, double shares, String date) throws Exception {
    if (tickerHoldings.containsKey(tickerSymbol)) {
      if (tickerHoldings.get(tickerSymbol).containsKey(date)) {
        double old = tickerHoldings.get(tickerSymbol).get(date);
        tickerHoldings.get(tickerSymbol).replace(date, shares + old);
      }
      else {
        tickerHoldings.get(tickerSymbol).put(date, shares);
      }
    } else {
      Map<String, Double> newHolding = new HashMap<String, Double>();
      newHolding.put(date, shares);
      tickerHoldings.put(tickerSymbol, newHolding);
    }
    this.savePortfolio();
  }

  @Override
  public double findValueOfPortfolio(String date, Map<String, IStock> stocks) {
    try {
      IPortfolio pastVersion = recreatePastVersion(date);
      double result = 0;
      for (String ticker : pastVersion.getTickerHoldings().keySet()) {
        if (!stocks.containsKey(ticker)) {
          throw new IllegalArgumentException("No stock found for ticker " + ticker + " in map");
        }
        for (String mapDate : pastVersion.getTickerHoldings().get(ticker).keySet()) {
          result += pastVersion.getTickerHoldings().get(ticker).get(mapDate)
                  * stocks.get(ticker).getClosingPrice(date);
        }
      }
      return result;
    } catch (Exception e) {
      throw new IllegalArgumentException("Error in findValueOfPortfolio method: " + e.getMessage());
    }
  }

  @Override
  public Map<String, Double> getStockHoldings() {
    HashMap<String, Double> result = new HashMap<String, Double>();
    for (String ticker : tickerHoldings.keySet()) {
      double totalShares = 0;
      for (String date : tickerHoldings.get(ticker).keySet()) {
        totalShares += tickerHoldings.get(ticker).get(date);
      }
      result.put(ticker, totalShares);
    }
    return result;
  }

  @Override
  public Map<String, Double> portfolioDistribution(String date, Map<String, IStock> stocks) {
    try {
      IPortfolio pastVersion = recreatePastVersion(date);
      Map<String, Double> result = new HashMap<String, Double>();
      for (String ticker : pastVersion.getTickerHoldings().keySet()) {
        if (!stocks.containsKey(ticker)) {
          throw new IllegalArgumentException("No stock found for ticker " + ticker + " in map");
        }
        for (String boughtDate : pastVersion.getTickerHoldings().get(ticker).keySet()) {
          if (dateIsAfterOrSame(date, boughtDate)) {
            if (result.containsKey(ticker)) {
              result.replace(ticker, result.get(ticker) +
                      pastVersion.getTickerHoldings().get(ticker).get(boughtDate) *
                              stocks.get(ticker).getClosingPrice(date));
            } else {
              result.put(ticker, pastVersion.getTickerHoldings().get(ticker).get(boughtDate) *
                      stocks.get(ticker).getClosingPrice(date));
            }
          }
        }
      }
      return result;
    } catch (Exception e) {
      throw new RuntimeException("Something went wrong when obtaining " + date + " version of " +
              "this portfolio (" + e.getMessage() + ")");
    }
  }

  @Override
  public Map<String, Double> portfolioComposition(String date, Map<String, IStock> stocks) {
    try {
      IPortfolio pastVersion = recreatePastVersion(date);

      Map<String, Double> result = new HashMap<String, Double>();
      for (String ticker : pastVersion.getTickerHoldings().keySet()) {
        if (!stocks.containsKey(ticker)) {
          throw new IllegalArgumentException("No stock found for ticker " + ticker + " in map");
        }
        for (String boughtDate : pastVersion.getTickerHoldings().get(ticker).keySet()) {
          if (dateIsAfterOrSame(date, boughtDate)) {
            if (result.containsKey(ticker)) {
              result.replace(ticker, result.get(ticker) +
                      pastVersion.getTickerHoldings().get(ticker).get(boughtDate));
            } else {
              result.put(ticker, pastVersion.getTickerHoldings().get(ticker).get(boughtDate));
            }
          }
        }
      }
      return result;
    } catch (Exception e) {
      throw new RuntimeException("Something went wrong when obtaining " + date + " version of " +
              "this portfolio (" + e.getMessage() + ")");
    }
  }

  @Override
  public void sellStock(String tickerSymbol, double shares, String date) throws Exception {
    if (!enoughShares(tickerSymbol, shares, date)) {
      throw new IllegalStateException("There aren't enough shares of this stock in this " +
              "portfolio to sell " + shares + " of it.");
    }
    IPortfolio pastVersion = recreatePastVersion(date);
    double remainingToSell = shares;
    for (String boughtDate : pastVersion.getTickerHoldings().get(tickerSymbol).keySet()) {
      if (dateIsAfterOrSame(date, boughtDate)) {
        if (remainingToSell - pastVersion.getTickerHoldings().get(tickerSymbol).
                get(boughtDate) > 0) {
          this.updateHistory(tickerSymbol, pastVersion.getTickerHoldings().get(tickerSymbol).
                          get(boughtDate),
                  date, boughtDate);
          remainingToSell -= pastVersion.getTickerHoldings().get(tickerSymbol).get(boughtDate);
          pastVersion.getTickerHoldings().get(tickerSymbol).remove(boughtDate);
          //should not stop
        } else {
          this.updateHistory(tickerSymbol, remainingToSell, date, boughtDate);
          double afterSelling = pastVersion.getTickerHoldings().get(tickerSymbol).get(boughtDate) -
                  remainingToSell;
          double newValue = tickerHoldings.get(tickerSymbol).get(boughtDate) - remainingToSell;
          tickerHoldings.get(tickerSymbol).replace(boughtDate, newValue);
          //should stop
        }
      }
    }
    this.eraseEmptyStocks();
    this.savePortfolio();
  }

  @Override
  public void rebalance(String date, double[] weights,
                        Map<String, IStock> stocks) {
    double totalValue = this.findValueOfPortfolio(date, stocks);
    Map<String, Double> weightsMap = new HashMap<String, Double>();
    for (int i = 0; i < this.getTickerSymbols().size(); i++) {
      weightsMap.put(this.getTickerSymbols().get(i), weights[i] / 100);
    }
    for (String tickerSymbol : weightsMap.keySet()) {
      this.adjustWeight(tickerSymbol, totalValue * weightsMap.get(tickerSymbol), date, stocks);
    }
  }

  //--------------------------------HELPERS----------------------------------------------

  private void updateHistory(String tickerSymbol, double shares, String dateSold,
                             String dateBought) {
    history.get(0).add(dateSold);
    history.get(1).add(tickerSymbol);
    history.get(2).add(Double.toString(shares));
    history.get(3).add(dateBought);
  }

  private void adjustWeight(String tickerSymbol, Double valueGoal, String date,
                            Map<String, IStock> stocks) {
    Double currentPrice = portfolioDistribution(date, stocks).get(tickerSymbol);
    Double currentShares = portfolioComposition(date, stocks).get(tickerSymbol);
    double goalShares = (currentShares * valueGoal) / currentPrice;
    double differenceShares = Math.abs(currentShares - goalShares);
    try {
      if (currentPrice > valueGoal) {
        //sell
        sellStock(tickerSymbol, differenceShares, date);
      } else {
        //buy
        addStock(tickerSymbol, differenceShares, date);
      }
    } catch (Exception e) {
      throw new RuntimeException("Something went wrong when adjusting the weight of " +
              tickerSymbol + " to match the value of $" + valueGoal + "(" + e.getMessage() + ")");
    }
  }

  private boolean dateIsAfterOrSame(String date1, String date2) {
    LocalDate firstDate = LocalDate.parse(date1);
    LocalDate secondDate = LocalDate.parse(date2);
    //first date is after or the same as second
    return !secondDate.isAfter(firstDate);
  }

  private boolean enoughShares(String ticker, double toSell, String date) {
    try {
      IPortfolio pastVersion = recreatePastVersion(date);
      double total = 0;
      for (String boughtDate : pastVersion.getTickerHoldings().get(ticker).keySet()) {
        if (dateIsAfterOrSame(date, boughtDate)) {
          total += pastVersion.getTickerHoldings().get(ticker).get(boughtDate);
        }
      }
      return total >= toSell;
    } catch (Exception e) {
      throw new RuntimeException("There are no stocks for " + ticker + " in this portfolio.");
    }
  }

  private SmartPortfolio recreatePastVersion(String date) {
    SmartPortfolio pastVersion = new SmartPortfolio("past");
    SmartPortfolio pastAfterBuying = this.addEverythingBoughtBefore(date, pastVersion);
    SmartPortfolio pastFinal = this.putBackEverythingSoldAfter(date, pastAfterBuying);
    pastFinal.eraseEmptyStocks();
    return pastFinal;
  }

  private SmartPortfolio addEverythingBoughtBefore(String date, SmartPortfolio pastVersion) {
    try {
      for (String ticker : tickerHoldings.keySet()) {
        for (String boughtDate : tickerHoldings.get(ticker).keySet()) {
          if (dateIsAfterOrSame(date, boughtDate)) {
            pastVersion.addStock(ticker, tickerHoldings.get(ticker).get(boughtDate), boughtDate);
          }
        }
      }
      return pastVersion;
    } catch (Exception e) {
      throw new RuntimeException("Exception thrown in addEverythingBoughtBefore" +
              " (" + e.getMessage() + ")");
    }
  }

  private SmartPortfolio putBackEverythingSoldAfter(String date, SmartPortfolio pastVersion) {
    try {
      for (int i = 0; i < HistoryUtils.getHistorySoldDates(getHistory()).size(); i++) {
        if (!dateIsAfterOrSame(date, HistoryUtils.getHistorySoldDates(getHistory()).get(i))
                && dateIsAfterOrSame(date, HistoryUtils.getHistoryBoughtDates(getHistory()).
                get(i))) {
          pastVersion.addStock(HistoryUtils.getHistoryTickers(getHistory()).get(i),
                  HistoryUtils.getHistoryShares(getHistory()).get(i),
                  HistoryUtils.getHistoryBoughtDates(getHistory()).get(i));
        }
      }
      return pastVersion;
    }
    catch (Exception e) {
      throw new RuntimeException("Exception thrown in putBackEverythingSoldAfter" +
              " (" + e.getMessage() + ")");
    }
  }


  private void eraseEmptyStocks() {
    Map<String, Map<String, Double>> result = new HashMap<>();
    for (String ticker : tickerHoldings.keySet()) {
      for (String boughtDate : tickerHoldings.get(ticker).keySet()) {
        if (tickerHoldings.get(ticker).get(boughtDate) != 0) {
          if (!result.containsKey(ticker)) {
            result.put(ticker, new HashMap<>());
          }
          result.get(ticker).put((boughtDate), tickerHoldings.get(ticker).get(boughtDate));
        }
      }
    }
    this.tickerHoldings = result;
  }

  //--------------------------------ACCESSORS----------------------------------------------

  @Override
  public List<String> getTickerSymbols() {
    ArrayList<String> result = new ArrayList<String>();
    for (String ticker : tickerHoldings.keySet()) {
      result.add(ticker);
    }
    return result;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Map<String, Map<String, Double>> getTickerHoldings() {
    return new HashMap<>(this.tickerHoldings);
  }

  @Override
  public List<List<String>> getHistory() {
    return new ArrayList<>(history);
  }

  private static class HistoryUtils {

    private static List<String> getHistorySoldDates(List<List<String>> history) {
      return history.get(0);
    }

    private static List<String> getHistoryTickers(List<List<String>> history) {
      return history.get(1);
    }

    private static List<Double> getHistoryShares(List<List<String>> history) {
      List<Double> result = new ArrayList<Double>();
      for (String entry : history.get(2)) {
        result.add(Double.parseDouble(entry));
      }
      return result;
    }

    private static List<String> getHistoryBoughtDates(List<List<String>> history) {
      return history.get(3);
    }
  }






}
