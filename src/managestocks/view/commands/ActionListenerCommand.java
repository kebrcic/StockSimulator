package managestocks.view.commands;


import java.awt.event.ActionListener;

/**
 * This class acts as an adapter to use the Command pattern with ActionListener.
 */
public class ActionListenerCommand implements ActionListener {
  private Commands command;

  /**
   * Constructs an ActionListenerCommand with the specified command.
   *
   * @param command the command to be executed.
   */
  public ActionListenerCommand(Commands command) {
    this.command = command;
  }

  @Override
  public void actionPerformed(java.awt.event.ActionEvent e) {
    command.execute();
  }
}
