package managestocks.controller;

import org.junit.Before;
import org.junit.Test;



import managestocks.model.MockModel;


import managestocks.view.IViewListeners;


import static org.junit.Assert.assertEquals;

/**Testing the controller.*/
public class ControllerGUITest {
  private IViewListeners controller;
  private StringBuilder log;

  @Before
  public void setUp() {
    log = new StringBuilder();
    MockModel model = new MockModel(log);
    controller = new ControllerGUI(model);
  }

  @Test
  public void testHandleCreatePortfolio() {
    controller.handleCreatePortfolio("TestPortfolio");
    assertEquals("createPortfolio called", log.toString());
  }

  @Test
  public void testHandleBuyStock() throws Exception {
    controller.handleBuyStock("TestPortfolio", "AAPL", 10,
            "2024-06-19");
    assertEquals("addStock called", log.toString());
  }

  @Test
  public void testHandleSellStock() throws Exception {
    controller.handleSellStock("TestPortfolio", "AAPL", 10,
            "2024-06-19");
    assertEquals("removeStock called", log.toString());
  }

  @Test
  public void testHandleComposition() throws Exception {
    controller.handleGetPortfolioComposition("TestPortfolio", "2024-06-19");
    assertEquals("portfolioComposition called", log.toString());
  }

  @Test
  public void testHandleValue() throws Exception {
    controller.handleGetPortfolioValue("TestPortfolio", "2024-06-19");
    assertEquals("calculatePortfolioValue called", log.toString());
  }
}