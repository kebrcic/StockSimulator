package managestocks.controller;

import managestocks.model.IModel;

/**Represents the controller of the program, receives input from the user and processes it on the
 *  model accordingly.*/
public interface IController {

  /**Runs the given model with the input from the user.*/
  public void runModel(IModel model);
}
