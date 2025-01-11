# Stock Management Program

## Overview
The **Stock Management Program** is a command-line and GUI-based application that allows users to efficiently manage stocks and portfolios. It supports a wide range of features, from retrieving stock data to advanced portfolio management.

---

## Folder Structure
- **`res` Folder**:
  - Contains details on how to run the program.
  - Includes UML diagrams.
  - Provides an explanation of the design and architecture of the code.

---

## Features

### 1. Stock Data Retrieval
- **Retrieve Stock Data**:
  - Retrieves stock data from the [AlphaVantage API](https://www.alphavantage.co/) using a stock ticker symbol.
  - This feature is fully functional.
- **Caching**:
  - Caches retrieved stock data in CSV files for future use, reducing redundant API calls.
  - This feature is fully functional.

### 2. Stock Calculations
- **Gain or Loss**:
  - Calculates the gain or loss for a given stock over a specified period.
  - This feature is fully functional.
- **Moving Average**:
  - Computes the moving average of a stock's closing prices over a specified number of days.
  - This feature is fully functional.
- **Crossover Days**:
  - Identifies the days when the stock's closing price crosses over the moving average.
  - This feature is fully functional.

### 3. Portfolio Management
- **Portfolio Creation and Management**:
  - Creates and manages portfolios of stocks.
  - This feature is fully functional.
- **Adding Stocks**:
  - Adds stocks to portfolios with a specified number of shares.
  - This feature is fully functional.
- **Portfolio Valuation**:
  - Calculates the total value of a portfolio on a specific date.
  - This feature is fully functional.
- **Removing Stocks**:
  - Removes stocks from portfolios.
  - This feature is fully functional.
- **Portfolio Composition and Distribution**:
  - Determines the composition and distribution of stocks in a portfolio.
  - This feature is fully functional.
- **Rebalancing**:
  - Rebalances a portfolio to meet specific criteria.
  - This feature is fully functional.
- **Portfolio Performance**:
  - Displays the performance of a portfolio over a specified time range.
  - This feature is fully functional.
- **Persistent Storage**:
  - Saves portfolios permanently for future use.
  - This feature is fully functional.

### 4. User Interface (CLI)
- Provides a command-line interface for interacting with the program.
- Supports the following commands (all are fully functional):
  - `GainOrLoss`
  - `Average`
  - `Crossovers`
  - `Create Portfolio [type 'p']`
  - `BuyStock`
  - `PortfolioValue`
  - `SellStock`
  - `PortfolioComposition`
  - `PortfolioDistribution`
  - `Rebalance`
  - `PortfolioPerformance`
  - `Exit [type 'e']`: Exits the program.

### 5. Graphical User Interface (GUI)
- Provides a graphical interface for easier interaction.
- Supports the following commands (all are fully functional):
  - `Create Portfolio`
  - `BuyStock`
  - `PortfolioValue`
  - `SellStock`
  - `PortfolioComposition`
  - `Exit`: Exits the program.

---

## How to Run
1. **Command-Line Interface**:
   - Run the main class for the CLI application.
   - Follow the prompts to interact with the program.
2. **Graphical User Interface**:
   - Launch the GUI by running the specified main class.
   - Use the graphical interface to manage stocks and portfolios.

---

This program provides a comprehensive solution for managing stocks and portfolios, with robust functionality and user-friendly interfaces for both novice and experienced users.
