package managestocks.view;

import java.io.IOException;

import java.util.Map;

/**
 * Class that renders messages to the console.
 */
public class ConsoleView implements IView {
  private final Appendable out;

  /**
   * Constructs a ConsoleView with the specified Appendable output.
   *
   * @param out the Appendable output to which messages will be rendered.
   */
  public ConsoleView(Appendable out) {
    this.out = out;
  }

  @Override
  public void writeMessage(String message) throws IllegalStateException {
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override
  public void displayPortfolio(Map<String, Double> composition, String units) {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Double> entry : composition.entrySet()) {
      String key = entry.getKey();
      Double value = entry.getValue();
      sb.append(key).append(": ").append(value.toString()).append(units).append("\n");
    }
    writeMessage(sb.toString());
  }

  @Override
  public void displayPerformance(Map<String, Integer> datesToScaledPValues, int scale,
                                 String portfolioName, String dateF, String dateT,
                                 String scaleSymbol) {
    StringBuilder allData = new StringBuilder();
    String title = "Performance of portfolio " + portfolioName + " from " + dateF + " to " + dateT;
    allData.append(title).append("\n");
    for (Map.Entry<String, Integer> entry : datesToScaledPValues.entrySet()) {
      StringBuilder individualBarGraph = new StringBuilder();
      String date = entry.getKey();
      individualBarGraph.append(date).append(": ");

      Integer numberOfAsterisks = entry.getValue();
      StringBuilder symbol = new StringBuilder();
      for (int i = 0; i < numberOfAsterisks; i++) {
        symbol.append(scaleSymbol);
      }
      individualBarGraph.append(symbol);
      allData.append(individualBarGraph).append("\n");
    }
    allData.append("\n").append("scale: ").append(scaleSymbol).append(" ").append("= ").
            append(scale);
    writeMessage(allData.toString());
  }


}
