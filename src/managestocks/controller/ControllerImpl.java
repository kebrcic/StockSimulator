package managestocks.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import managestocks.commands.AddStock;
import managestocks.commands.CrossOver;
import managestocks.commands.GainOrLossCommand;
import managestocks.commands.ICommand;
import managestocks.commands.MovingAverage;
import managestocks.commands.CreatePortfolio;
import managestocks.commands.PortfolioValue;
import managestocks.model.IModel;



import managestocks.view.IView;

/**Represents the controller of the StockModel program, receives requests from the user and
 *  processes them on the model.*/
public class ControllerImpl extends Controller {
  protected IView view;
  protected Readable in;
  protected Map<String, ICommand> commands;

  /**
   *Initialises the parameters out, and in, as well as the hashMap commands to store the String
   *  representing the command key, and the value being the corresponding function object.
   * @param view Represents an Appendable object to display in the screen to the user
   * @param in Represents a Readable object to read from the user
   */
  public ControllerImpl(IView view, Readable in) {
    this.view = Objects.requireNonNull(view);
    this.in = Objects.requireNonNull(in);
    this.commands = new HashMap<String, ICommand>();
    commands.put("gainorloss", new GainOrLossCommand());
    commands.put("average", new MovingAverage());
    commands.put("crossover", new CrossOver());
    commands.put("buystock", new AddStock());
    commands.put("p", new CreatePortfolio());
    commands.put("portfoliovalue", new PortfolioValue());
  }

  @Override
  public void runModel(IModel model) {
    this.setUp();
    displayMenu();
    Scanner s = new Scanner(this.in);
    while (s.hasNext()) {
      String command = s.next().toLowerCase();
      view.writeMessage("command: " + command + System.lineSeparator());
      ICommand commandRun = null;
      //commands where the tickerSymbol isn't necessary
      if (command.equals("e")) {
        break;
      }
      else if (this.commands.containsKey(command)) {
        try {
          commandRun = this.commands.getOrDefault(command, null);
          commandRun.run(model, view, s);
        }
        catch (Exception e) {
          view.writeMessage(e.getMessage() + System.lineSeparator());
        }
      }
      else {
        view.writeMessage("Invalid command inputted " + System.lineSeparator());
      }
      displayMenu();
    }
    farewellMessage();
  }

  void displayMenu() {
    view.writeMessage("\nStock Trader Menu:" + System.lineSeparator());
    view.writeMessage("GainOrLoss" + System.lineSeparator());
    view.writeMessage("Average" + System.lineSeparator());
    view.writeMessage("Crossovers" + System.lineSeparator());
    view.writeMessage("Create Portfolio [type p]" + System.lineSeparator());
    view.writeMessage("PortfolioValue" + System.lineSeparator());
    view.writeMessage("BuyStock" + System.lineSeparator());

  }

  void farewellMessage() throws IllegalStateException {
    view.writeMessage("Thank you for using this program!");
  }

}
