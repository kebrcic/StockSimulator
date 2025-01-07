package managestocks.model;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**Testing the constructor of the portfolio class.*/
public class PortfolioConstructorTest extends ModelTestExamples {

  @Test
  public void basicConstructor() {
    IPortfolio test = new SmartPortfolio("named");
    assertEquals("named", test.getName());
    assertEquals(new HashMap<>(), test.getTickerHoldings());
  }
}
