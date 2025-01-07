package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the method createPortfolio on the model class.*/
public class ModelCreatePortfolioTest extends ModelTestExamples {

  @Test
  public void basicCreatePortfolio() {
    assertEquals(0, model1.getPortfolios().size());
    model1.createPortfolio("test");
    assertEquals(1, model1.getPortfolios().size());
    model1.createPortfolio("test2");
    assertEquals(2, model1.getPortfolios().size());
  }

  @Test
  public void throwingCreatePortfolio() {
    try {
      model1.createPortfolio("test");
      model1.createPortfolio("test");
      fail("Allowed creation of two portfolios with the same name");
    }
    catch (Exception e) {
      assertEquals("there's already an existing portfolio with the name test",
              e.getMessage());
    }
  }
}
