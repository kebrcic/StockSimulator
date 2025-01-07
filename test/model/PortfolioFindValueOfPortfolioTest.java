package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing the method findValueOfPortfolio on the portfolio class.
 */
public class PortfolioFindValueOfPortfolioTest extends ModelTestExamples {

  @Test
  public void basicFindValueOfPortfolio() {
    try {
      portfolio1.addStock("GOOG", 15, "2020-05-05");
      portfolio1.addStock("AAPL", 10, "2020-05-05");
      double expected = 15 * 175.13 + 10 * 194.35;
      double actual = portfolio1.findValueOfPortfolio("2024-06-04", model1.getStocks());
      assertEquals(expected, actual, 0.001);
    } catch (Exception e) {
      fail(e.getMessage());
    }

  }

  @Test
  public void advancedFindValueOfPortfolio() {
    try {
      double goog15 = 15 * 169.83;
      double tsla30 = 30 * 184.76;
      double tsla20 = 20 * 184.76;

      //buying before query date and checking on query date
      portfolio1.addStock("GOOG", 15, "2020-05-05");
      assertEquals(goog15, portfolio1
              .findValueOfPortfolio("2024-05-06", model1.getStocks()), 0.001);
      portfolio1.addStock("TSLA", 30, "2020-05-05");
      assertEquals(goog15 + tsla30, portfolio1
              .findValueOfPortfolio("2024-05-06", model1.getStocks()), 0.001);

      //checking value is zero before buying anything
      assertEquals(0, portfolio1.findValueOfPortfolio("2019-05-06", model1.getStocks()), 0.001);

      //buying after query date (should not be included)
      portfolio1.addStock("ABNB", 10, "2024-06-04");
      assertEquals(goog15 + tsla30, portfolio1
              .findValueOfPortfolio("2024-05-06", model1.getStocks()), 0.001);

      //selling before query date
      portfolio1.sellStock("TSLA", 10, "2020-05-06");
      assertEquals(goog15 + tsla20, portfolio1
              .findValueOfPortfolio("2024-05-06", model1.getStocks()), 0.001);

      //selling after query date (should not be included)
      portfolio1.sellStock("GOOG", 15, "2024-06-04");

      //expected standing on date of query:
      //15 GOOG
      //20 TSLA
      double expected = tsla20 + goog15;
      double actual = portfolio1.findValueOfPortfolio("2024-05-06", model1.getStocks());
      assertEquals(expected, actual, 0.001);

    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void advanced2() {
    try {
      portfolio1.addStock("GOOG", 5, "2020-05-05");
      portfolio1.addStock("TSLA", 10, "2020-05-05");
      assertEquals(0.0, portfolio1.findValueOfPortfolio("2019-05-28", model1.getStocks()), 0.001);
    }
    catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void throwingFindValueOfPortfolio() {
    try {
      portfolio1.addStock("NKE", 5, "2020-05-05");
      double actual = portfolio1.findValueOfPortfolio("2024-06-04", model1.getStocks());
      fail("Didn't throw exception even though model is missing data");
    } catch (Exception e) {
      assertEquals("Error in findValueOfPortfolio method: No stock found for ticker NKE in map",
              e.getMessage());
    }
  }
}
