package managestocks.model;

import org.junit.Test;

import managestocks.controller.IReader;
import managestocks.controller.StockGenerator;

import static org.junit.Assert.assertEquals;

/**Testing the method populate on the model class.*/
public class ModelPopulateTest extends ModelTestExamples {

  @Test
  public void basicPopulate() {
    assertEquals(4, model1.getStocks().size());
    model1.populate("TSLA", new StockGenerator(IReader.accessReadable("TSLA"))
            .makeStock("TSLA"));
    assertEquals(4, model1.getStocks().size());
    //Google is already in the stocks map
    model1.populate("AMZN", new StockGenerator(IReader.accessReadable("AMZN"))
            .makeStock("AMZN"));
    assertEquals(5, model1.getStocks().size());
  }
}
