package managestocks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the method movingAverage on the model class.*/
public class ModelMovingAverageTest extends ModelTestExamples {

  @Test
  public void basicMovingAverage() {
    double actual = model1.movingAverage("GOOG", "2015-11-11", 5);
    double expected = (735.4 + 728.32 + 724.89 + 733.76 + 731.25) / 5;
    assertEquals(expected, actual, 0.001);

    actual = model1.movingAverage("GOOG", "2017-10-20", 10);
    expected = (988.2 + 984.45 + 992.81 + 992.18 + 992 + 989.68 + 987.83 + 989.25 + 972.6 + 977) /
            10;
    assertEquals(expected, actual, 0.001);
  }

  @Test
  public void throwingMovingAverage() {
    try {
      model1.movingAverage("GOOG", "2014-03-28", 30);
      fail("Allowed date input to which there is not enough data to go back" +
              "the given number of days");
    }
    catch (IllegalArgumentException e) {
      assertEquals("Not enough data to calculate average " +
              "for the requested number of days", e.getMessage());
    }
  }
}
