package managestocks.commands;

import java.util.Map;
import java.util.Scanner;

import managestocks.controller.Utils;
import managestocks.model.IModel;
import managestocks.view.IView;

/**Function object that represents the command of returning the performance of a portfolio
 * given the dates, the name of the portfolio, and the range.*/
public class PerformancePortfolio extends Commands {

  @Override
  public void run(IModel model, IView view, Scanner scanner) {
    String portfolioName = super.portfolioNameInput(model, view, scanner);
    String dateFrom = super.dateInput(model, view, scanner, "From");
    String dateTo = super.dateInput(model, view, scanner, "To");
    Utils.checkFromDateBeforeToDate(dateFrom, dateTo);
    try {
      Map<String, Integer> dateToPortfolioVal =
              model.performancePortfolio(portfolioName, dateFrom, dateTo);
      int scale = model.getScale();
      view.displayPerformance(dateToPortfolioVal, scale, portfolioName, dateFrom, dateTo,
              "*");
    }
    catch (Exception e) {
      view.writeMessage(e.getMessage() + System.lineSeparator());
    }
  }
}
