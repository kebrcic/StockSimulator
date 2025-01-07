package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**Testing the method getDataSize on the stock class.*/
public class StockGetDataSizeTest extends ModelTestExamples {

  @Test
  public void basicGetDataSize() {
    int actual = stockGOOG.getDataSize();
    int expected = 2568;
    assertEquals(expected, actual);
  }
}
