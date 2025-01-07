-------------------------Stock Management Program --------------------------

***the "res" folder contains details on how to run the program, UML diagrams, and an explanation for the design of the code***

This program is a command-line and GUI application that allows users to manage stocks and portfolios.
 It provides the following features.

1. Stock Data Retrieval:
   - Retrieves stock data from the AlphaVantage API (https://www.alphavantage.co/) for a given stock
    ticker symbol. This feature works.
   - Caches retrieved stock data in CSV files for future use, avoiding redundant API calls. This
   feature works.


2. Stock Calculations:
   - Calculates the gain or loss for a given stock over a specified period. This feature works.
   - Computes the moving average of a stock's closing prices for a given number of days.
   This feature works.
   - Identifies the crossover days when the stock's closing price crossed over the moving average.
   This feature works.


3. Portfolio Management:
   - Creates and manages portfolios of stocks. This feature works.
   - Adds stocks to portfolios, specifying the number of shares. This feature works.
   - Calculates the total value of a portfolio on a specific date. This feature works.
   - Removes stocks from portfolios. This feature works.
   - Determine the composition and distribution of a portfolio. This feature works.
   - Rebalances a portfolio. This feature works.
   - Displays the performance of a portfolio on a time-range, scaling the portfolio values
     accordingly. This feature works.
   - Saves portfolios permanently. This feature works.

4. User Interface:
   - Provides a command-line interface for users to interact with the program. This feature works.
   - Supports the following commands which were already described:
     - GainOrLoss. This feature works.
     - Average. This feature works.
     - Crossovers. This feature works.
     - Create Portfolio [type 'p']. This feature works.
     - BuyStock. This feature works.
     - PortfolioValue. This feature works.
     - SellStock. This feature works.
     - PortfolioComposition. This feature works.
     - PortfolioDistribution. This feature works.
     - Rebalance. This feature works.
     - PortfolioPerformance. This feature works.
     - Exit [type 'e']: Exits the program. This feature works.

5. Graphical User Interface:
   - Provides a GUI for users to interact with the program. This feature works.
   - Supports the following commands which were already described:
     - Create Portfolio. This feature works.
     - BuyStock. This feature works.
     - PortfolioValue. This feature works.
     - SellStock. This feature works.
     - PortfolioComposition. This feature works.
     - Exit: Exits the program. This feature works.




