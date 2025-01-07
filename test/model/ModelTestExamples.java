package managestocks.model;

import org.junit.Before;

import managestocks.controller.IReader;
import managestocks.controller.StockGenerator;

/**
 * All examples used for testing model methods.
 */
public abstract class ModelTestExamples {
  IModel model1;
  IPortfolio portfolio1;
  IStock stockGOOG;
  IStock stockAAPL;

  @Before
  public void setUp() {
    model1 = new Model();
    model1.populate("GOOG", new StockGenerator(IReader.accessReadable("GOOG"))
            .makeStock("GOOG"));
    model1.populate("AAPL", new StockGenerator(IReader.accessReadable("AAPL"))
            .makeStock("AAPL"));
    model1.populate("TSLA", new StockGenerator(IReader.accessReadable("TSLA"))
            .makeStock("TSLA"));
    model1.populate("ABNB", new StockGenerator(IReader.accessReadable("ABNB"))
            .makeStock("ABNB"));
    portfolio1 = new SmartPortfolio("portfolio1");
    stockGOOG = new StockGenerator(IReader.accessReadable("GOOG")).makeStock("GOOG");
    stockAAPL = new StockGenerator(IReader.accessReadable("AAPL")).makeStock("AAPL");
  }
}
