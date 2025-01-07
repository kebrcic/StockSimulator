package managestocks.model;

import org.junit.Test;

import java.util.Map;

import managestocks.controller.IReader;
import managestocks.controller.StockGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the portfolio distribution functionality.*/
public class PortfolioDistributionTest extends ModelTestExamples {

  @Test
  public void basicDistributionTest() {
    try {
      model1.populate("AMZN", new StockGenerator(IReader.accessReadable("AMZN"))
              .makeStock("AMZN"));


      portfolio1.addStock("GOOG", 20, "2020-05-05");
      portfolio1.addStock("TSLA", 5, "2020-05-05");
      portfolio1.addStock("AMZN", 10, "2020-05-05");
      Map<String, Double> actual = portfolio1
              .portfolioDistribution("2020-05-06", model1.getStocks());

      assertEquals(3, actual.size());

      double expectedGOOG = 20 * 1347.3;
      double expectedTSLA = 5 * 782.58;
      double expectedABNB = 10 * 2351.26;
      assertEquals(expectedGOOG, actual.get("GOOG"), 0.001);
      assertEquals(expectedTSLA, actual.get("TSLA"), 0.001);
      assertEquals(expectedABNB, actual.get("AMZN"), 0.001);
    }
    catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void splitBoughtDistributionTest() {
    try {
      portfolio1.addStock("GOOG", 5, "2020-05-05");
      portfolio1.addStock("GOOG", 5, "2020-05-03");
      portfolio1.addStock("TSLA", 10, "2020-05-05");
      Map<String, Double> actual = portfolio1
              .portfolioDistribution("2020-05-06", model1.getStocks());

      double expectedGOOG = 10 * 1347.3;
      double expectedTSLA = 10 * 782.58;
      assertEquals(expectedGOOG, actual.get("GOOG"), 0.001);
      assertEquals(expectedTSLA, actual.get("TSLA"), 0.001);
    }
    catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
