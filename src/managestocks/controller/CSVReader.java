package managestocks.controller;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;


/**Handles the translation of a stored file into a StringReader, which has type
Readable. Class only possesses one static method.*/
public class CSVReader implements IReader {

  /**
  Converts a given file into a StringReader, which has type Readable.
  Loops over the .read() method while there is still data to be converted in the file.*
  @param file previously stored file for stock data.
          @return a Readable StringReader with the data in the given file.
          */
  public static Readable convertToReadable(File file) {
    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = new FileInputStream(file);
      int b;

      while ((b = in.read()) != -1) {
        output.append((char)b);
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException("error");
    }
    return new StringReader(output.toString());
  }
}
