package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the method getDateIndex on the stock class.*/
public class StockGetDateIndexTest extends ModelTestExamples {

  @Test
  public void basicGetDateIndex() {
    assertEquals(3, stockGOOG.getDateIndex("2024-06-04"));
    assertEquals(203, stockGOOG.getDateIndex("2023-08-17"));
    assertEquals(2563, stockGOOG.getDateIndex("2014-04-02"));
  }

  @Test
  public void throwingGetDateIndex() {
    try {
      stockGOOG.getDateIndex("2014-03-25");
      fail("Didn't throw exception when asked for non-existent date");
    }
    catch (Exception e) {
      assertEquals("This stock doesn't have any data for the given date",
              e.getMessage());
    }

    try {
      stockGOOG.getDateIndex("2021-03-20");
      fail("Didn't throw exception when asked for non-existent date");
    }
    catch (Exception e) {
      assertEquals("This stock doesn't have any data for the given date",
              e.getMessage());
    }
  }
}
