package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the sell stock functionality.*/
public class ModelSellStockTest extends ModelTestExamples {
  @Test
  public void invalidDateTest() {
    try {
      model1.createPortfolio("sellTest");
      model1.addStock("sellTest", "GOOG", 10, "2020-05-05");
      model1.removeStock("sellTest", "GOOG", 10, "2020-05-10");
      fail();
    }
    catch (Exception e) {
      assertEquals("Invalid date: the stock GOOG does not have any data for" +
              " the given date", e.getMessage());
    }
  }

  @Test
  public void notEnoughSharesTest() {
    try {
      model1.createPortfolio("sellTest2");
      model1.addStock("sellTest2", "GOOG", 10, "2020-05-05");
      model1.removeStock("sellTest2", "GOOG", 11, "2020-05-6");
      fail();
    }
    catch (Exception e) {
      assertEquals("Invalid date: the stock GOOG does not have any data for the" +
              " given date", e.getMessage());
    }
  }
}
