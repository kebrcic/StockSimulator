package managestocks.controller;

import java.io.File;

import managestocks.model.IModel;

/**
 * An abstract class representing the controller for the Stocks Program.
 * It implements the IController interface and provides the basic setup
 * for the directory structure needed by the application. All controllers require this.
 */
public abstract class Controller implements IController {

  public abstract void runModel(IModel model);

  protected void setUp() {
    new File(System.getProperty("user.home") + "/StocksProgram/res").mkdirs();
    new File(System.getProperty("user.home") + "/StocksProgram/res/stocks").mkdirs();
    new File(System.getProperty("user.home") + "/StocksProgram/res/portfolios").mkdirs();
    new File(System.getProperty("user.home") + "/StocksProgram/res/logs").mkdirs();
  }
}
