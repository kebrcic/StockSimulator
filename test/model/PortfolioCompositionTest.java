package managestocks.model;

import org.junit.Test;

import java.util.Map;

import managestocks.controller.IReader;
import managestocks.controller.StockGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the portfolio composition functionality.*/
public class PortfolioCompositionTest extends ModelTestExamples {

  @Test
  public void basicCompositionTest() {
    try {
      model1.populate("AMZN", new StockGenerator(IReader.accessReadable("AMZN"))
              .makeStock("AMZN"));
      portfolio1.addStock("GOOG", 15, "2020-05-05");
      portfolio1.addStock("TSLA", 5, "2020-05-05");
      portfolio1.addStock("AMZN", 20, "2020-05-05");
      Map<String, Double> actual = portfolio1.portfolioComposition("2020-05-06",
              model1.getStocks());
      assertEquals(3, actual.size());
      assertEquals(15, actual.get("GOOG"), 0.001);
      assertEquals(5, actual.get("TSLA"), 0.001);
      assertEquals(20, actual.get("AMZN"), 0.001);
    }
    catch (Exception e) {
      fail();
    }
  }

  @Test
  public void advancedCompositionTest() {
    try {
      portfolio1.addStock("GOOG", 15, "2020-05-05");
      portfolio1.addStock("TSLA", 5, "2020-05-05");

      //buying after query date (should not be reflected in composition)
      portfolio1.addStock("AMZN", 20, "2020-05-09");
      portfolio1.addStock("GOOG", 50, "2020-05-09");

      Map<String, Double> actual = portfolio1.portfolioComposition("2020-05-06",
              model1.getStocks());
      assertEquals(2, actual.size());
      assertEquals(15, actual.get("GOOG"), 0.001);
      assertEquals(5, actual.get("TSLA"), 0.001);
    }
    catch (Exception e) {
      fail();
    }
  }

  @Test
  public void advanced2() {
    try {
      portfolio1.addStock("GOOG", 15, "2020-05-05");
      portfolio1.addStock("GOOG", 20, "2020-05-06");
      assertEquals(35, portfolio1.portfolioComposition("2020-05-09",
              model1.getStocks()).get("GOOG"), 0.001);
    }
    catch (Exception e) {
      fail();
    }
  }
}
