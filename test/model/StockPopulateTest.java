package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**Testing the method populate on the stock class.*/
public class StockPopulateTest extends ModelTestExamples {

  @Test
  public void basicPopulate() {
    IStock stock = new Stock("AMZN");
    stock.populate("2020-10-10", 1, 2, 3, 4, 5);
    assertEquals("2020-10-10", stock.getDates().get(0));
    assertEquals(4, stock.getClosingPrice("2020-10-10"), 0.001);
  }
}
