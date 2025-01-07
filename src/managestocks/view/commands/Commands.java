package managestocks.view.commands;

/**
 * Represents the commands to execute once a button is clicked.
 * This interface defines the execute method for command pattern implementations.
 * Classes implementing this interface will provide specific implementations of the execute method.
 */
public interface Commands {

  /**
   * Executes the command, delegating to a part of the controller.
   */
  public void execute();
}

