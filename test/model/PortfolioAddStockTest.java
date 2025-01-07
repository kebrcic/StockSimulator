package managestocks.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**Testing the method addStock on the portfolio class.*/
public class PortfolioAddStockTest extends ModelTestExamples {

  @Test
  public void basicAddStock() throws Exception {
    assertTrue(portfolio1.getStockHoldings().isEmpty());
    portfolio1.addStock("GOOG", 10, "2020-05-05");
    assertEquals(1, portfolio1.getStockHoldings().size());
    Map<String, Double> expected = new HashMap<String, Double>();
    expected.put("2020-05-05", 10.0);
    Map<String, Double> actual = portfolio1.getTickerHoldings().get("GOOG");
    assertEquals(expected, actual);
    portfolio1.addStock("GOOG", 15, "2020-05-06");
    assertEquals(2, portfolio1.getTickerHoldings().get("GOOG").size());
    double total = 0;
    for (String boughtDate : portfolio1.getTickerHoldings().get("GOOG").keySet()) {
      total += portfolio1.getTickerHoldings().get("GOOG").get(boughtDate);
    }
    assertEquals(25, total, 0.001);
  }
}
