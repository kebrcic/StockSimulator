package managestocks.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for model-related tests. Allows all model test files to be
 * run at once.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ModelGainOrLossTest.class,
        ModelMovingAverageTest.class,
        ModelAddStockTest.class,
        ModelCrossoverTest.class,
        ModelCalculatePortfolioValueTest.class,
        ModelPopulateTest.class,
        ModelCreatePortfolioTest.class,
        ModelSellStockTest.class,
        PortfolioAddStockTest.class,
        PortfolioFindValueOfPortfolioTest.class,
        PortfolioConstructorTest.class,
        PortfolioSellStockTest.class,
        PortfolioRebalanceTest.class,
        PortfolioCompositionTest.class,
        StockGetClosingPriceTest.class,
        StockGetDateIndexTest.class,
        StockGetDataSizeTest.class,
        StockGetDatesTest.class,
        StockPopulateTest.class
})

public class ModelTestSuite {
}
