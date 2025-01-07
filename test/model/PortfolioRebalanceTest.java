package managestocks.model;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the portfolio rebalance functionality.*/
public class PortfolioRebalanceTest extends ModelTestExamples {

  @Test
  public void basicRebalanceTest() {
    try {
      portfolio1.addStock("GOOG", 10, "2020-05-05");
      portfolio1.addStock("TSLA", 10, "2020-05-05");
      assertEquals(2, portfolio1.getStockHoldings().size());

      double goog = 1347.3 * 10;
      double tsla = 782.58 * 10;
      double total = goog + tsla;
      assertEquals(goog + tsla, portfolio1
              .findValueOfPortfolio("2020-05-06", model1.getStocks()), 0.001);
      assertEquals("GOOG", portfolio1.getTickerSymbols().get(0));

      double[] weights = {60.0, 40.0};
      double expectedGOOG = (goog + tsla) * 0.6;
      double expectedTSLA = (goog + tsla) * 0.4;
      portfolio1.rebalance("2020-05-06", weights, model1.getStocks());
      assertEquals(2, portfolio1.getStockHoldings().size());

      Map<String,Double> actual = portfolio1.portfolioDistribution("2020-05-06",
              model1.getStocks());
      Map<String, Double> composition = portfolio1
              .portfolioComposition("2020-05-06", model1.getStocks());
      double totalShares = composition.get("GOOG") + composition.get("TSLA");

      assertEquals(expectedGOOG, actual.get("GOOG"), 0.001);
      assertEquals(expectedTSLA, actual.get("TSLA"), 0.001);
    }
    catch (Exception e) {
      fail();
    }
  }
}
