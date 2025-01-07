
In order to make this program work, the user must first go through a few steps:
--Navigate to the /Users folder, and into the current user's user folder
  (Example: /Users/John)
--Then, the user must create a folder named StocksProgram . If the folder is named
  something other than this, the program won't work.
--Next thing, the user should place the .jar file inside this folder
--Finally, in order to run the program, the user should use the terminal to navigate
  to the newly created StocksProgram folder and type the following command:
  java -jar Stocks.jar for the GUI program.
--java -jar Stocks.jar -text for the text based program.

  Then press enter (or return, depending on operating system) and the program will start.

----------------------------------------TEXT BASED PROGRAM------------------------------------------
  ----IMPORTANT- FOR ALL DATE INPUTS, EVERY TIME THE PROGRAM ASKS THE USER FOR A MONTH OR A DAY,
  THE NUMBER INPUTED CAN BE OF A SINGLE DIGIT AND IT WILL STILL BE PROCESSED ALTHOUGH THE PROGRAM
  SAYS "day(dd)" or "month(mm)".
  -For example
  "Enter date"
  "Year(yyyy):" 2004
  "Month(mm):" 4
  "Day(dd):" 7
  ---or---
  "Enter date"
  "Year(yyyy):" 2004
  "Month(mm):" 04
  "Day(dd):" 07

  Both are valid inputs--------

  *****Also for all commands that the user triggers, the program asks for every input it needs to
   execute the command clearly**********
----------------------------------------------------------------------------------------------------
PORTFOLIO COMMANDS

 To create a portfolio inside the program, simply type "p" and then press enter.
    For example "p"
    The program will ask for a portfolio name, a message will be displayed if the operation was done
    correctly.
    You can then use the BuyStock then give it a stock ticker symbol
    Then enter the desired portfolio name, press enter, then the desired number of shares and press
     enter and finally the date when you are buying the stock. The program will first ask for a
      year, then a month and then a day.
    For example "buystock"
                "GOOG"
                "newPortfolio"
                "10"
                "2024"
                "03"
                "09"
                "buystock"
                "TSLA"
                "newPortfolio"
                "7"
                "2024"
                "05"
                "17"

   Then to sell a stock enter the desired portfolio name, press enter, then the desired number of
    shares and press enter and finally the date when you are selling the stock.
     The program will first ask for a year, then a month and then a day.
     For example "sellstock"
                 "GOOG"
                 "newPortfolio"
                 "10"
                 "2024"
                 "03"
                 "09"

    Then to get the value of this portfolio, use the command "portfoliovalue", and give it a name
    and then a date. The program will first ask for a year, then a month and then a day.
    For example "portfoliovalue"
                "newPortfolio"
                "2024"
                "06"
                "02"

    Then to get the composition of a portfolio, use the command "portfoliocomposition", and give it
     a name and then a date. For the date, the program will first ask for a year, then a month and
     then a day.
    For example "portfoliocomposition"
                "newPortfolio"
                "2024"
                "06"
                "02"

    Then to get the distribution of a portfolio, use the command "portfoliodistribution",
    and give it a name and then a date. For the date, the program will first ask for a year,
     then a month and then a day.
    For example "portfoliodistribution"
                "newPortfolio"
                "2024"
                "06"
                "02"

   Then to get the performance of a portfolio, use the command "portfolioperformance",
    and give it a name and then a starting date and then an ending date. For the date, the program
     will first ask for a year, then a month and then a day.
    For example "portfolioperformance"
                "newPortfolio"
                "2024"
                "01"
                "02"
                "2024"
                "06"
                "09"

   Finally, to rebalance a portfolio, use the command "rebalance",
    and give it a name and then a date. For the date, the program will first ask for a year, then a
     month and then a day.
    For example "rebalance"
                "newPortfolio"
                "2024"
                "01"
                "02"


STOCKS COMMANDS

 To calculate the moving average of a stock input "average" and then the program will ask for the
  ticker symbol of the stock.
    Then the program will ask you to type the date. The program will first ask for a year, then
     a month and then a day.
    Then the console will ask you to type the number of days to go back, an integer is expected.
    For example: "average"
                 "aapl"
                 "2024"
                 "5"
                 "28"
                 "5"

 To calculate the gain or loss of a particular stock between two dates, the user has to first
    input "gainorloss" and then the program will ask for the ticker symbol of the stock.
    The program will then ask the user to type the "from" date and then the "to" date to calculate
    the gain or loss between these two dates. For every date input, the program will first ask for
     a year, then a month and then a day.
    For example: "gainorloss"
                  "goog"
                  "2024"
                  "05"
                  "17"
                  "2024"
                  "05"
                  "31"

 To calculate the crossover days of a stock, the user must first input the command "crossover" and
    then the program will ask for the ticker symbol of the stock. The program will then ask the user
     to input the "from", it will first ask for a year, then a month and then a day. Then it will
      ask for the number of crossover days, expecting an integer input.
    For example: "crossover"
                 "goog"
                 "2024"
                 "05"
                 "31"
                 "20"


 To exit the program, the user has to type "e", and after a farewell message will be rendered.