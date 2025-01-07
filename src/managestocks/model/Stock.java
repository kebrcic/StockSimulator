package managestocks.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**Represents a company's stock data (the opening price, closing price, high price, low price and
volume for each day).*/
public class Stock implements IStock {
  private final String ticker;
  private List<String> dates;
  private List<Double> open;
  private List<Double> close;
  private List<Double> high;
  private List<Double> low;
  private List<Integer> volume;

  /**
  Initialises an empty stock.
  @param ticker represents the new stock's ticker symbol.*/
  public Stock(String ticker) {
    this.ticker = Objects.requireNonNull(ticker);
    this.dates = new ArrayList<String>();
    this.open = new ArrayList<Double>();
    this.close = new ArrayList<Double>();
    this.high = new ArrayList<Double>();
    this.low = new ArrayList<Double>();
    this.volume = new ArrayList<Integer>();
  }

  @Override
  public void populate(String date, double open, double high, double low, double close,
                       int volume) {
    this.dates.add(date);
    this.open.add(open);
    this.high.add(high);
    this.low.add(low);
    this.close.add(close);
    this.volume.add(volume);
  }

  @Override
  public String getTicker() {
    return this.ticker;
  }

  @Override
  public double getClosingPrice(String date) {
    return this.close.get(getDateIndex(date));
  }

  @Override
  public int getDateIndex(String date) {
    for (int i = 0; i < dates.size(); i++) {
      if (dates.get(i).equals(date)) {
        return i;
      }
    }
    throw new IllegalArgumentException("This stock doesn't have any data for the given date");
  }

  @Override
  public int getDataSize() {
    return this.dates.size();
  }

  public ArrayList<String> getDates() {
    //returns a copy
    return new ArrayList<String>(this.dates);
  }
}