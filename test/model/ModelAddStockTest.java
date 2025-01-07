package managestocks.model;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the method addStock on the model class.*/
public class ModelAddStockTest extends ModelTestExamples {

  @Test
  public void invalidDateTest() throws Exception {
    assertEquals(0, model1.getPortfolios().size());
    model1.addStock("create", "GOOG", 10, "2020-05-05");
    assertEquals(1, model1.getPortfolios().size());
    try {
      model1.addStock("create", "GOOG", 10, "2020-05-03");
      fail("Allowed date input for which there is no data");
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid date: the stock GOOG does not " +
              "have any data for the given date", e.getMessage());
    }
  }
}
