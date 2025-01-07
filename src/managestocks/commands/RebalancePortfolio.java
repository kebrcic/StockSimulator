package managestocks.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import managestocks.controller.Utils;
import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of rebalancing a portfolio.*/
public class RebalancePortfolio extends Commands {
  @Override
  public void run(IModel model, IView view, Scanner scanner) {
    String portfolioName = super.portfolioNameInput(model, view, scanner);
    String date = super.dateInput(model, view, scanner, "");

    List<String> tickerSymbolsOnPortfolio = model.getTickerFromPortfolio(portfolioName);
    Map<String, Double> tickerSymbolsToWeights = new HashMap<String, Double>();

    for (String s : tickerSymbolsOnPortfolio) {
      view.writeMessage("Type the weight to rebalance " + s + ":");
      double weight = scanner.nextDouble();
      Utils.checkInputNonNegative(weight);
      tickerSymbolsToWeights.put(s, weight);
    }
    try {
      this.checkWeightsSum(tickerSymbolsToWeights);
      model.rebalancePortfolio(portfolioName, date, tickerSymbolsToWeights);
    }
    catch (Exception e) {
      view.writeMessage(e.getMessage() + System.lineSeparator());
    }
  }

  //Validates that the inputted weights sum to 100. Otherwise, an IllegalArgumentException
  // is thrown.
  private void checkWeightsSum(Map<String, Double> tickerSymbolsToWeights) {
    double sum = 0.0;
    for (String tickerSymbol : tickerSymbolsToWeights.keySet()) {
      sum += tickerSymbolsToWeights.get(tickerSymbol);
    }
    if (sum != 100.0) {
      throw new IllegalArgumentException("Weights must sum to 100");
    }

  }
}
