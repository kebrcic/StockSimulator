DESIGN

Design patterns used:
-MVC pattern
-Command pattern
-Abstraction
-Mocks
-Adapter pattern

We decided to use the model-view-controller design pattern to divide the program into its respective
sections. The controller is in charge of receiving input from the user, sending it to the model
to be processed and then calls the view to return an output. The view renders the messages on the
console, including exceptions, and the model stores and processes the data. In fact the model
 populates its fields and processes the data with data structures. Once it's populated,
  it performs all calculations. By following this design pattern, we are allowing the single
   responsibility principle, as well as the open/closed principle and also ensuring low coupling.

-the command-design-pattern was also utilised to reduce repetitive code
and allow for future commands to be added easily. Abstraction was also used to reduce the
 amount of code.

-Interfaces were created to allow union between data, dynamic dispatch, and enforce methods on
 classes that implement the interface.
--------------------------------------------------------------------------------------------------
 CHANGES MADE
 -Utils class was added to reduce duplicate code. All input validation methods are static and
 within this class.
 -Abstract controller class was created, that implements the IController interface, to
 reduce duplicate code, and place all the methods that every controller will require.
 The setup() method was placed as it creates the folders for the program to allow persistent storage
 (feature that all controllers need). Furthermore, the runModel() was also placed to enforce the
 method on all its subclasses(all controllers need this, and might change on every implementation).

A new controller was created ControllerGUI that extended the abstract Controller class
to support the new GUI view.
--------------------------------------------------------------------------------------------------
STOCKS
-Each Stock was represented by having a tickerName to identify the name of the company emitting the
stock and a series of arrayLists with matching indices to represent all the information about a
specific stock on each date (closing price, open price, volume, high price, and low price). As
the arrayLists have matching indices, the use of these matching data structures mimic a table were
each column represents an arraylist and each row is each index of each arrayList.

PORTFOLIO
To represent any type of portfolio, a class was created, containing the fields: String name
representing the name of the portfolio to identify it, and a Map that maps each StringTicker
(KEYS) to another Map that maps the dateBought to the number of shares bought. In this way
the portfolio stores data in an organized and easy-to-access way: per each Stock the amount of
 shares and date bough is stored. Furthermore, the Portfolio has a field of type PortfolioLog
 to store every selling transaction to be able to perform all calculations correctly.
 In addition, the portfolio contains a History, consisting of a List of Lists were:
 -index 0: list of sold dates
 -index 1: ticker symbols
 -index 2: shares
 -index 3: bought dates
 to allow selling and buying in chronological order.

MODEL
To represent the model we have an arrayList of portfolios as a user can have more than 1 of them, or
none, and a HashMap that maps each ticker symbol to and IStock. We also have a field scale
which represents the scale used to display the performance of the portfolio.

CONTROLLER AND COMMANDS
In our program, the `ControllerGUI` is represented using the `IViewListeners` interface.
This design choice allows for a clear separation of concerns and enhances flexibility.
The `IViewListeners` interface abstracts the methods that handle user interactions.
This enables the `StocksGUI` to interact with the controller without needing to know
the implementation details. It also allows for easy replacement or modification of the
controller logic without impacting the view.

The graphical user interface (GUI) is implemented in `StocksGUI` and follows the command design
pattern. Each user action (like buying or selling a stock, creating a portfolio, etc.) is
encapsulated in a separate command class (e.g., `BuyStockCommand`, `SellStockCommand`,
`CreatePortfolioCommand`).
These command classes implement the `Command` interface and contain the specific functionality for
each action.
 This approach has several advantages:
 1. Extensibility: New commands can be added without modifying existing code. This adheres to
  the Open/Closed Principle of SOLID design, where the software is open for extension but closed
  for modification.
 2. Separation of Concerns: The view (`StocksGUI`) is responsible for the presentation and user
  interaction, while the commands handle the business logic. This makes the code easier to manage
  and understand.
 3. Reusability: Command classes can be reused in different contexts, providing consistent
  behavior across the application.

The `ActionListenerCommand` class acts as an adapter that allows us to use command objects with
Java Swing's `ActionListener`. When a user interacts with the GUI (e.g., clicking a button), the
corresponding command's `execute` method is called, which in turn invokes the appropriate method
on the controller (`IViewListeners`). The controller then delegates the actual functionality to
the model.

READING AND FETCHING DATA
An interface was implemented to allow the program to read data from multiple sources, including csv
files and the API. This allows the program an easy way to extend to new ways to read data, only
by creating a class and implementing the interface.

VIEW
An graphical user interface was created to allow the program to render outputs. The GUI has a
delegate of the IViewListeners, representing the part of the controller it can access, to delegate
functionality every time a button is pressed.








