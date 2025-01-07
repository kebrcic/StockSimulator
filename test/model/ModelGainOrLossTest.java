package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the method gainOrLoss on the model class.*/
public class ModelGainOrLossTest extends ModelTestExamples {

  @Test
  public void basicGainOrLoss() {
    double actual = model1.gainOrLoss("GOOG",
           "2024-05-30", "2024-06-03");
    //from closing price: 173.56
    // to closing price: 174.42
    double expected = 174.42 - 173.56;
    assertEquals(expected, actual, 0.001);

    actual = model1.gainOrLoss("GOOG",
            "2014-03-27", "2024-04-10");
    //from closing price: 558.46
    //to closing price: 157.66
    expected = 157.66 - 558.46;
    assertEquals(expected, actual, 0.001);
  }

  @Test
  public void throwingGainOrLoss() {
    try {
      model1.gainOrLoss("GOOG",
              "2023-50-05", "2024-30-03");
      fail("Allowed invalid month number");
    }
    catch (IllegalArgumentException e) {
      //pass
    }

    try {
      model1.gainOrLoss("GOOG",
              "2023-06-50", "2024-06-90");
      fail("Allowed invalid day number");
    }
    catch (IllegalArgumentException e) {
      //pass
    }
  }
}
