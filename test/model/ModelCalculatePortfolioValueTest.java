package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**Testing the method portfolioValue on the model class.*/
public class ModelCalculatePortfolioValueTest extends ModelTestExamples {

  @Test
  public void basicPortfolioValue() throws Exception {
    model1.createPortfolio("test");
    assertEquals(0, model1.calculatePortfolioValue("test", "2024-06-04"), 0.001);
    model1.addStock("test", "GOOG", 10, "2020-05-05");
    double expected = 175.13 * 10;
    double actual = model1.calculatePortfolioValue("test", "2024-06-04");
    assertEquals(expected, actual, 0.001);
    model1.addStock("test", "AAPL", 5, "2020-05-05");
    expected = expected + (194.35 * 5);
    actual = model1.calculatePortfolioValue("test", "2024-06-04");
    assertEquals(expected, actual, 0.001);
  }

}
