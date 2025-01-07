package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**Testing the method getDates on the stock class.*/
public class StockGetDatesTest extends ModelTestExamples {

  @Test
  public void basicGetDates() {
    IStock stock = new Stock("TSLA");
    stock.populate("2020-05-05", 0,0,0,0,0);
    stock.populate("2020-04-04", 0,0,0,0,0);
    assertEquals(2, stock.getDates().size());
    assertEquals("2020-05-05", stock.getDates().get(0));
    assertEquals("2020-04-04", stock.getDates().get(1));
  }

}
