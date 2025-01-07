package managestocks.commands;

import java.util.Scanner;

import managestocks.model.IModel;
import managestocks.view.IView;

/** Represents all the commands that the user can trigger and can be executed by the program.*/
public interface ICommand {
  /**Executes the command on the given model.
   *
   * @param model represents the stocks model
   * @param view object to display to the sceen
   */
  public void run(IModel model, IView view, Scanner scanner);

}
