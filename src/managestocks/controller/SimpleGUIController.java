package managestocks.controller;


import java.io.InputStreamReader;

import managestocks.model.IModel;
import managestocks.model.Model;
import managestocks.view.ConsoleView;
import managestocks.view.IGUIView;
import managestocks.view.IView;
import managestocks.view.IViewListeners;
import managestocks.view.StocksGUI;

/**
 * This class represents the entry point for the Stocks Program GUI.
 * It sets up the model, controller, and view, and starts the application.
 */
public class SimpleGUIController {

  /**
   * The main method that sets up and starts the Stocks Program GUI.
   *
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      // No arguments provided, start the GUI
      startGUI();
    } else if (args.length == 1 && args[0].equals("-text")) {
      // -text argument provided, start the text-based interface
      startTextInterface();
    } else {
      // Invalid arguments provided, display error message and quit
      System.err.println("Invalid command-line arguments. Use either:");
      System.err.println("java -jar Program.jar -text : to start the text-based interface");
      System.err.println("java -jar Program.jar : to start the graphical user interface");
      System.exit(1);
    }
  }

  private static void startGUI() {
    // Create the model
    IModel stockModel = new Model();
    // Create the controller and pass the model to it
    ControllerGUI controller = new ControllerGUI(stockModel);
    // CONTROLLERS ACTUALLY USED
    IViewListeners viewListeners = controller;
    IController modelController = controller;
    // Create the view and pass the controller (as IViewListeners) to it
    IGUIView viewGUI = new StocksGUI(viewListeners);
    // Set the view in the controller
    controller.setView(viewGUI);
    // Run the controller with the model
    modelController.runModel(stockModel);
  }

  private static void startTextInterface() {
    // Create the model
    IModel stockModel = new Model();
    Appendable app = System.out;
    IView view = new ConsoleView(app);
    // Create the controller and pass the model and view to it
    ControllerImpl controller = new ControllerImpl(view, new InputStreamReader(System.in));
    // Run the controller with the model
    controller.runModel(stockModel);
  }

}
