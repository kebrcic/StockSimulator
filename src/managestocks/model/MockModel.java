package managestocks.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**Represents a Mock Model.*/
public class MockModel implements IModel {
  private Appendable log;

  /**Initialises a mock model object.
   *
   * @param log to store the message when the command is called, for testing.
   */
  public MockModel(Appendable log) {
    this.log = log;
  }

  @Override
  public double gainOrLoss(String tickerSymbol, String dateFrom, String dateTo) {
    return 0;
  }

  @Override
  public double movingAverage(String tickerSymbol, String date, int days) {
    return 0;
  }

  @Override
  public ArrayList<String> crossover(String tickerSymbol, String date, int x) {
    return null;
  }
  //----------------------------------------METHODS TESTED------------------------------------------

  @Override
  public void addStock(String portfolioName, String tickerName, double shares, String date)
          throws Exception {
    try {
      log.append("addStock called");
    }
    catch (Exception e) {
      e.getMessage();
    }
  }

  @Override
  public void removeStock(String portfolioName, String tickerName, double shares, String date) {
    try {
      log.append("removeStock called");
    }
    catch (Exception e) {
      e.getMessage();
    }
  }

  @Override
  public Map<String, Double> portfolioComposition(String portfolioName, String date) {
    try {
      log.append("portfolioComposition called");
    }
    catch (Exception e) {
      e.getMessage();
    }
    return Map.of();
  }

  @Override
  public double calculatePortfolioValue(String portfolioName, String date) {
    try {
      log.append("calculatePortfolioValue called");
    }
    catch (Exception e) {
      e.getMessage();
    }
    return 0.0;
  }

  @Override
  public void createPortfolio(String name) {
    try {
      log.append("createPortfolio called");
    }
    catch (Exception e) {
      e.getMessage();
    }
  }
  //-----------------------------------------------------------------------------------------------

  @Override
  public void populate(String tickerSymbol, IStock stock) {
    try {
      log.append("");
    }
    catch (Exception e) {
      e.getMessage();
    }
  }

  @Override
  public void populate(IPortfolio portfolio) {
    try {
      log.append("");
    }
    catch (Exception e) {
      e.getMessage();
    }
  }

  @Override
  public Map<String, IStock> getStocks() {
    return Map.of();
  }

  @Override
  public List<IPortfolio> getPortfolios() {
    return List.of();
  }

  @Override
  public Map<String, Double> portfolioDistribution(String portfolioName, String date) {
    return Map.of();
  }

  @Override
  public List<String> getTickerFromPortfolio(String portfolioName) {
    return List.of();
  }

  @Override
  public IPortfolio loadPortfolio(String portfolioName) {
    return null;
  }

  @Override
  public void rebalancePortfolio(String portfolioName, String date,
                                 Map<String, Double> tickerSymbolToWeights) {
    try {
      log.append("");
    }
    catch (Exception e) {
      e.getMessage();
    }
  }

  @Override
  public Map<String, Integer> performancePortfolio(String portfolioName, String dateFrom,
                                                   String dateTo) {
    return Map.of();
  }

  @Override
  public int getScale() {
    return 0;
  }

  @Override
  public IPortfolio findPortfolio(String portfolioName) {
    return null;
  }
}
