package managestocks.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents the model class to manage and store stocks and portfolios.
 */
public class Model implements IModel {
  private final List<IPortfolio> portfolio;
  private final Map<String, IStock> stocks;
  private int scale;

  /**
   * Initialises the model with an empty Portfolio list and an empty stocks map.
   */
  public Model() {
    this.portfolio = new ArrayList<IPortfolio>();
    this.stocks = new HashMap<String, IStock>();
    this.scale = 0;
  }

  @Override
  public void populate(String tickerSymbol, IStock stock) {
    //if this map doesn't contain this stock, the add it.
    //otherwise ignore.
    if (!this.stocks.containsKey(tickerSymbol)) {
      this.stocks.put(tickerSymbol, stock);
    }
  }

  @Override
  public void populate(IPortfolio portfolio) {
    //if this map doesn't contain this stock, the add it.
    //otherwise ignore.
    if (!this.portfolio.contains(portfolio)) {
      this.portfolio.add(portfolio);
    }
  }

  @Override
  public Map<String, IStock> getStocks() {
    return this.stocks;
  }

  @Override
  public List<IPortfolio> getPortfolios() {
    return this.portfolio;
  }

  //--------------------------------ACTUAL FUNCTIONS----------------------------------------------

  @Override
  public double gainOrLoss(String tickerSymbol, String dateFrom, String dateTo) {
    IStock stock = stocks.get(tickerSymbol);
    return stock.getClosingPrice(dateTo) - stock.getClosingPrice(dateFrom);
  }

  @Override
  public double movingAverage(String tickerSymbol, String date, int days)
          throws IllegalArgumentException {
    IStock stock = stocks.get(tickerSymbol);
    if (stock.getDataSize() < days + stock.getDateIndex(date)) {
      throw new IllegalArgumentException("Not enough data to calculate average " +
              "for the requested number of days");
    }
    double sum = 0;
    int count = 0;
    int indexDate = this.stocks.get(tickerSymbol).getDateIndex(date);
    for (int i = indexDate; i < indexDate + days; i++) {
      sum += stock.getClosingPrice(this.stocks.get(tickerSymbol).getDates().get(i));
      count += 1;
    }
    return sum / count;
  }

  @Override
  public ArrayList<String> crossover(String tickerSymbol, String date, int days)
          throws IllegalArgumentException {
    ArrayList<String> crossoverDays = new ArrayList<String>();
    IStock stock = stocks.get(tickerSymbol);
    if (stock.getDataSize() < days + stock.getDateIndex(date)) {
      throw new IllegalArgumentException("Not enough data to calculate crossover " +
              "for the requested number of days");
    }
    int indexCurrentDate = this.stocks.get(tickerSymbol).getDateIndex(date);
    for (int i = indexCurrentDate; i < indexCurrentDate + days; i++) {
      String currentDate = this.stocks.get(tickerSymbol).getDates().get(i);
      if (stock.getClosingPrice(currentDate) > this.movingAverage(tickerSymbol,
              currentDate, days)) {
        crossoverDays.add(currentDate);
      }
    }
    return crossoverDays;
  }

  @Override
  public void addStock(String portfolioName, String tickerSymbol, double shares, String date) {
    try {
      this.validateDate(tickerSymbol, date);
    }
    catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    try {
      IPortfolio portfolio = this.findPortfolio(portfolioName);
      portfolio.addStock(tickerSymbol, shares, date);
    }
    catch (Exception e) {
      this.createPortfolio(portfolioName);
      this.addStock(portfolioName, tickerSymbol, shares, date);
    }
  }

  @Override
  public void removeStock(String portfolioName, String tickerName, double shares, String date) {
    this.validateDate(tickerName, date);
    IPortfolio portfolio = this.findPortfolio(portfolioName);
    try {
      portfolio.sellStock(tickerName, shares, date);
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to sell stock: " + e.getMessage());
    }
  }

  @Override
  public double calculatePortfolioValue(String portfolioName, String date) {
    double value = 0.0;
    if (this.portfolioExists(portfolioName)) {
      IPortfolio portfolio = this.findPortfolio(portfolioName);
      value = portfolio.findValueOfPortfolio(date, this.stocks);
    } else {
      throw new IllegalStateException("Non-existent portfolio name");
    }
    return value;
  }

  @Override
  public void createPortfolio(String name) throws IllegalArgumentException {
    if (this.portfolioExists(name)) {
      throw new IllegalStateException("there's already an existing portfolio with the name "
              + name);
    } else {
      try {
        IPortfolio portfolio = new SmartPortfolio(name);
        portfolio.savePortfolio();
        this.portfolio.add(portfolio);

      } catch (Exception e) {
        throw new RuntimeException("Error when saving portfolio: " + e.getMessage());
      }
    }
  }

  @Override
  public Map<String, Double> portfolioComposition(String portfolioName, String date) {
    if (this.portfolioExists(portfolioName)) {
      IPortfolio portfolio = this.findPortfolio(portfolioName);
      return portfolio.portfolioComposition(date, this.stocks);
    } else {
      throw new IllegalArgumentException("Portfolio " + portfolioName + " doesn't exist.");
    }
  }

  @Override
  public Map<String, Double> portfolioDistribution(String portfolioName, String date) {
    if (this.portfolioExists(portfolioName)) {
      IPortfolio portfolio = this.findPortfolio(portfolioName);
      for (String tickerSymbol : portfolio.getTickerSymbols()) {
        this.validateDate(tickerSymbol, date);
      }
      return portfolio.portfolioDistribution(date, this.stocks);
    } else {
      throw new IllegalArgumentException("Portfolio " + portfolioName + " doesn't exist.");
    }
  }

  @Override
  public void rebalancePortfolio(String portfolioName, String date,
                                 Map<String, Double> tickerSymbolToWeights) {
    System.out.println(tickerSymbolToWeights.keySet());
    System.out.println(tickerSymbolToWeights.values());

  }

  @Override
  public Map<String, Integer> performancePortfolio(String portfolioName, String dateFrom,
                                                   String dateTo) {
    int days = 0;
    int months = 0;
    int years = 0;

    boolean everyDay = false;
    boolean everyMonth = false;
    boolean everyYear = false;

    LocalDate dateF = LocalDate.parse(dateFrom);
    LocalDate dateT = LocalDate.parse(dateTo);

    Period period = dateF.until(dateT);
    if ((period.getYears() >= 5) && (period.getYears() <= 30)) {
      years = period.getYears();
      everyYear = true;
    } else if ((period.getYears() * 12 + period.getMonths()) >= 5) {
      months = period.getYears() * 12 + period.getMonths();
      everyMonth = true;
    } else if (period.getYears() * 365 + period.getMonths() * 30 + period.getDays() + 1 < 5) {
      days = period.getYears() * 365 + period.getMonths() * 30 + period.getDays();
    } else {
      days = period.getYears() * 365 + period.getMonths() * 30 + period.getDays() + 1;
      everyDay = true;
    }

    int every = 0;
    TemporalUnit unit = null;
    if (everyDay) {
      every = this.determineEveryWhat(days, 5);
      unit = ChronoUnit.DAYS;
    } else if (everyMonth) {
      every = this.determineEveryWhat(months, 5);
      unit = ChronoUnit.MONTHS;
    } else if (everyYear) {
      every = this.determineEveryWhat(years, 5);
      unit = ChronoUnit.YEARS;
    }

    //timeStamps to display
    List<LocalDate> timeStamps = this.generateTimeStampsToDisplay(dateF, dateT, every,
            unit);

    //corresponding portfolio values to each time stamp.
    List<Double> correspondingPortfolioValues =
            this.generatePortfolioValuesForTimeStamps(timeStamps, portfolioName);

    //determine the scale
    this.scale = this.calculateScale(correspondingPortfolioValues);

    //scaled portfolio values according to the scale
    List<Integer> valScaled = this.portfolioValuesScaled(correspondingPortfolioValues, scale);

    //map of dates as string to portfolio values scaled
    return this.mapDateToScaledPValues(timeStamps, valScaled);
  }


  //------------------------------HELPERS FOR PERFORMANCE COMMAND-----------------------------------
  private int determineEveryWhat(int totalPeriod, int startingVal) {
    int every = 0;
    int j = startingVal;
    boolean out = false;
    while (j < 31 && !out) {
      for (int i = 1; i < totalPeriod; i++) {
        if (totalPeriod / i == j) {
          every = i;
          out = true;
          break;
        }
      }
      j++;
    }
    return every;
  }

  private List<LocalDate> generateTimeStampsToDisplay(LocalDate fromDate, LocalDate toDate,
                                                      int everyWhat, TemporalUnit unit) {
    List<LocalDate> timeStamps = new ArrayList<LocalDate>();
    while (!fromDate.isAfter(toDate)) {
      if (fromDate.isEqual(toDate)) {
        timeStamps.add(toDate);
        break;
      }
      LocalDate timeStamp = fromDate;
      timeStamps.add(timeStamp);
      timeStamp = fromDate.plus(everyWhat, unit);
      fromDate = timeStamp;
    }
    return timeStamps;
  }

  private List<Double> generatePortfolioValuesForTimeStamps(List<LocalDate> timestamps,
                                                            String portfolioName) {
    List<Double> correspondingPortfolioValues = new ArrayList<Double>();
    for (LocalDate timestamp : timestamps) {
      String currDate = timestamp.toString();
      Double pVal = this.calculatePortfolioValue(portfolioName, currDate);
      correspondingPortfolioValues.add(pVal);
    }
    return correspondingPortfolioValues;
  }

  private List<Integer> portfolioValuesScaled(List<Double> portfolioVals, int scale) {
    List<Integer> scaledVals = new ArrayList<Integer>();
    for (Double val : portfolioVals) {
      scaledVals.add((int) (val / scale));
    }
    return scaledVals;
  }

  private Map<String, Integer> mapDateToScaledPValues(List<LocalDate> dates,
                                                      List<Integer> scaledPVals) {
    Map<String, Integer> dateToScaledVal = new LinkedHashMap<String, Integer>();
    for (int i = 0; i < dates.size(); i++) {
      dateToScaledVal.put(dates.get(i).toString(), scaledPVals.get(i));
    }
    return dateToScaledVal;
  }

  private int calculateScale(List<Double> portfolioValues) {
    if (portfolioValues.isEmpty()) {
      throw new IllegalArgumentException("The ArrayList cannot be empty.");
    }
    int totalDigits = 0;
    int count = 0;
    for (double value : portfolioValues) {
      int digits = (int) Math.log10(Math.abs(value)) + 1;
      totalDigits += digits;
      count++;
    }
    int averageDigits = (int) Math.floor((double) totalDigits / count);
    return (int) Math.pow(10, (averageDigits - 1));
  }

  //--------------------------------HELPERS----------------------------------------------

  @Override
  public List<String> getTickerFromPortfolio(String portfolioName) {
    IPortfolio portfolio = this.findPortfolio(portfolioName);
    return portfolio.getTickerSymbols();
  }

  @Override
  public IPortfolio loadPortfolio(String portfolioName) {
    try {
      IPortfolio portfolio = new SmartPortfolio(portfolioName).loadPortfolio(portfolioName);
      return portfolio;
    } catch (Exception e) {
      throw new RuntimeException("Cannot load portfolio.");
    }
  }

  //returns true if there is data for the given date.
  private void validateDate(String tickerSymbol, String date) {
    try {
      for (String ticker : this.stocks.keySet()) {
        if (ticker.equals(tickerSymbol)) {
          this.stocks.get(ticker).getDateIndex(date);
        }
      }
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Invalid date: the stock " + tickerSymbol + " does not have any" +
              " data for the given date");
    }
  }

  @Override
  public IPortfolio findPortfolio(String portfolioName) {
    IPortfolio thePortfolio = null;
    for (IPortfolio portfolio : this.portfolio) {
      if (portfolio.getName().equals(portfolioName)) {
        thePortfolio = portfolio;
        break;
      }
    }
    if (thePortfolio == null) {
      throw new IllegalStateException("Portfolio not found");
    }
    return thePortfolio;
  }

  private boolean portfolioExists(String portfolioName) {
    boolean flag = true;
    try {
      IPortfolio portfolio = this.findPortfolio(portfolioName);
    } catch (Exception e) {
      flag = false;
    }
    return flag;
  }

  @Override
  public int getScale() {
    return this.scale;
  }

}
