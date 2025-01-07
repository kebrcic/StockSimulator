package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**Testing the portfolio sell stock functionality.*/
public class PortfolioSellStockTest extends ModelTestExamples {

  @Test
  public void basicSellStockTest() {
    try {
      assertTrue(portfolio1.getTickerHoldings().isEmpty());
      portfolio1.addStock("GOOG", 10, "2020-05-05");
      assertEquals(10, portfolio1.getStockHoldings().get("GOOG"), 0.001);
      portfolio1.sellStock("GOOG", 10, "2020-05-06");
      assertTrue(portfolio1.getStockHoldings().isEmpty());

    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void partialSaleTest() {
    try {
      assertTrue(portfolio1.getTickerHoldings().isEmpty());
      portfolio1.addStock("GOOG", 10, "2020-05-05");
      assertEquals(10, portfolio1.getStockHoldings().get("GOOG"), 0.001);
      portfolio1.sellStock("GOOG", 5, "2020-05-06");
      assertEquals(5, portfolio1.getStockHoldings().get("GOOG"), 0.001);
    }
    catch (Exception e) {
      fail();
    }
  }
}
