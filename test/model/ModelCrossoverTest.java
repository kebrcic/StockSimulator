package managestocks.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**Testing the method crossover on the model class.*/
public class ModelCrossoverTest extends ModelTestExamples {

  @Test
  public void basicCrossover() {
    List<String> actual = model1.crossover("GOOG", "2014-09-08", 6);
    List<String> expected = new ArrayList<String>();
    expected.add("2014-09-08");
    expected.add("2014-09-05");
    expected.add("2014-09-04");
    expected.add("2014-09-03");
    expected.add("2014-09-02");
    assertEquals(expected, actual);

    try {
      model1.crossover("GOOG", "2014-04-02", 7);
      fail("Allowed number of days for which there isn't enough data");
    }
    catch (Exception e) {
      assertEquals("Not enough data to calculate crossover for the requested number " +
              "of days", e.getMessage());
    }
  }
}
