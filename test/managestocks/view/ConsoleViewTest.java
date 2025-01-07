package managestocks.view;

import org.junit.Test;


import static org.junit.Assert.assertFalse;

import java.util.LinkedHashMap;
import java.util.Map;

/**Tests for the consoleView.*/
public class ConsoleViewTest {

  @Test
  public void displayPerformance() {
    Map<String, Integer> map = new LinkedHashMap<String, Integer>();
    map.put("2024-01-01", 5);
    map.put("2024-02-01", 1);
    map.put("2024-03-01", 10);
    map.put("2024-04-01", 8);
    map.put("2024-05-01", 7);
    IView view = new ConsoleView(System.out);
    view.displayPerformance(map, 1000, "techPortfolio", "2024-01-01",
            "2024-05-01", "*");
    assertFalse(map.isEmpty());
  }
}