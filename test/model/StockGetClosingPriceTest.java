package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**Testing the method getClosingPrice on the stock class.*/
public class StockGetClosingPriceTest extends ModelTestExamples {

  @Test
  public void basicGetClosingPrice() {
    assertEquals(2225.55, stockGOOG.getClosingPrice("2021-04-05"), 0.001);
  }
}
