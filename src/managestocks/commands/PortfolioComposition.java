package managestocks.commands;

import java.util.Map;
import java.util.Scanner;

import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of returning the composition of a portfolio.*/
public class PortfolioComposition extends Commands {
  @Override
  public void run(IModel model, IView view, Scanner scanner) {
    String portfolioName = super.portfolioNameInput(model, view, scanner);
    String date = super.dateInput(model, view, scanner, "");
    try {
      Map<String, Double> compositionMap = model.portfolioComposition(portfolioName, date);
      view.displayPortfolio(compositionMap, "");
    }
    catch (Exception e) {
      view.writeMessage(e.getMessage() + System.lineSeparator());
    }
  }
}

