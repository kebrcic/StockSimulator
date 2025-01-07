package managestocks.controller;

import java.io.InputStreamReader;

import managestocks.model.IModel;
import managestocks.model.Model;
import managestocks.view.ConsoleView;
import managestocks.view.IView;

/**
Simple controller for program. Allows the user to create, modify and see the value of
portfolios, and also perform stock calculations, such as gain or loss function, x-day
moving average and crossovers over x-days.
*/
public class SimpleController {

  /**Main method.
   * @param args main method arguments parameter.*/
  public static void main(String[] args) {
    IModel stockModel = new Model();
    Appendable app = System.out;
    IView view = new ConsoleView(app);
    IController controller = new SmartControllerImpl(view, new InputStreamReader(System.in));
    controller.runModel(stockModel);
  }

}
