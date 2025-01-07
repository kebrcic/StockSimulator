package managestocks.view;


import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import managestocks.view.commands.ActionListenerCommand;
import managestocks.view.commands.BuyStockCommand;
import managestocks.view.commands.CompositionCommand;
import managestocks.view.commands.CreatePortfolioCommand;
import managestocks.view.commands.FindValueCommand;
import managestocks.view.commands.SellStockCommand;

/**
 * This class represents the GUI for the Stocks Program.
 * It implements the IGUIView interface and provides the functionality for user interaction.
 */
public class StocksGUI extends JFrame implements IGUIView {
  private final JTextField portfolioNameField;
  private final JTextField dateField;
  private final JTextField stockNameField;
  private final JTextField stockNumberField;
  private final JTextField portfolioNameQueryField;
  private final JTextField dateQueryField;
  private final JButton buyButton;
  private final JButton sellButton;
  private final JButton createPortfolioButton;
  private final JButton findValueButton;
  private final JButton getCompositionButton;
  private final DefaultListModel<String> listModel;
  private final JTextField portfolioValueField;


  /**
   * Constructs a StocksGUI with the specified IViewListeners feature.
   *
   * @param feature the feature to handle user interactions.
   */
  public StocksGUI(IViewListeners feature) {
    // Set up the frame
    setTitle("Stocks Program");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(10, 10));

    // North section
    JLabel titleLabel = new JLabel("Stocks Program", JLabel.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    add(titleLabel, BorderLayout.NORTH);

    // Center and East section
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbcMain = new GridBagConstraints();
    gbcMain.insets = new Insets(10, 10, 10, 10);
    gbcMain.fill = GridBagConstraints.BOTH;
    gbcMain.weightx = 1.0;
    gbcMain.weighty = 1.0;

    // Center section (Buy/Sell)
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridBagLayout());
    centerPanel.setBorder(BorderFactory.createTitledBorder("Buy/Sell"));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;

    gbc.gridx = 0;
    gbc.gridy = 0;
    centerPanel.add(new JLabel("Portfolio name:"), gbc);
    portfolioNameField = new JTextField(10);
    gbc.gridx = 1;
    centerPanel.add(portfolioNameField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    centerPanel.add(new JLabel("Date:"), gbc);
    dateField = new JTextFieldWithPlaceholder("yyyy-mm-dd");
    gbc.gridx = 1;
    centerPanel.add(dateField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    centerPanel.add(new JLabel("Stock:"), gbc);
    stockNameField = new JTextField(10);
    gbc.gridx = 1;
    centerPanel.add(stockNameField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    centerPanel.add(new JLabel("Shares:"), gbc);
    stockNumberField = new JTextField(10);
    gbc.gridx = 1;
    centerPanel.add(stockNumberField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    buyButton = new JButton("Buy");
    centerPanel.add(buyButton, gbc);

    gbc.gridx = 1;
    sellButton = new JButton("Sell");
    centerPanel.add(sellButton, gbc);

    gbcMain.gridx = 0;
    gbcMain.gridy = 0;
    mainPanel.add(centerPanel, gbcMain);

    // East section (Portfolio)
    JPanel eastPanel = new JPanel();
    eastPanel.setLayout(new GridBagLayout());
    eastPanel.setBorder(BorderFactory.createTitledBorder("Portfolio"));
    gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;

    gbc.gridx = 0;
    gbc.gridy = 0;
    eastPanel.add(new JLabel("Name:"), gbc);
    portfolioNameQueryField = new JTextField(10);
    gbc.gridx = 1;
    eastPanel.add(portfolioNameQueryField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    eastPanel.add(new JLabel("Date:"), gbc);
    dateQueryField = new JTextFieldWithPlaceholder("yyyy-mm-dd");
    gbc.gridx = 1;
    eastPanel.add(dateQueryField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    createPortfolioButton = new JButton("Create Portfolio");
    eastPanel.add(createPortfolioButton, gbc);

    gbc.gridy = 3;
    gbc.gridwidth = 1;
    findValueButton = new JButton("Find Value");
    eastPanel.add(findValueButton, gbc);
    portfolioValueField = new JTextField(10);
    portfolioValueField.setEditable(false);
    gbc.gridx = 1;
    eastPanel.add(portfolioValueField, gbc); // Adding the new text field next to the button

    gbc.gridy = 4;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    getCompositionButton = new JButton("Get Composition");
    eastPanel.add(getCompositionButton, gbc);

    gbcMain.gridx = 1;
    gbcMain.gridy = 0;
    mainPanel.add(eastPanel, gbcMain);

    add(mainPanel, BorderLayout.CENTER);

    // South section
    listModel = new DefaultListModel<>();
    JList<String> compositionList = new JList<>(listModel);
    JScrollPane scrollPane = new JScrollPane(compositionList);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Composition List"));
    add(scrollPane, BorderLayout.SOUTH);

    // Adding action listeners
    addFeatures(feature);
  }

  @Override
  public void addFeatures(IViewListeners feature) {
    buyButton.addActionListener(new ActionListenerCommand(new BuyStockCommand(feature,
            this)));

    sellButton.addActionListener(new ActionListenerCommand(new SellStockCommand(feature,
            this)));
    createPortfolioButton.addActionListener(new ActionListenerCommand(new CreatePortfolioCommand(
            feature, this)));
    findValueButton.addActionListener(new ActionListenerCommand(new FindValueCommand(feature,
            this)));
    getCompositionButton.addActionListener(new ActionListenerCommand(new CompositionCommand(feature,
            this)));
  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }

  @Override
  public void showMessage(String message, String title, int pane) {
    JOptionPane.showMessageDialog(this, message, "", pane);
  }

  @Override
  public void displayPortfolio(Map<String, Double> composition, String units) {
    List<String> formattedComposition = new ArrayList<String>();
    for (Map.Entry<String, Double> entry : composition.entrySet()) {
      formattedComposition.add(entry.getKey() + ": " + entry.getValue() + " " + units);
    }
    updateCompositionList(formattedComposition);
  }

  private void updateCompositionList(List<String> compositions) {
    listModel.clear();
    for (String composition : compositions) {
      listModel.addElement(composition);
    }
  }

  @Override
  public void clearBuySellInputs() {
    stockNameField.setText("");
    stockNumberField.setText("");
  }

  @Override
  public void setPortfolioValue(String value) {
    portfolioValueField.setText(value);
  }

  //--------------------------------------ACCESSORS-----------------------------------------------
  @Override
  public String getDate() {
    return dateField.getText();
  }

  @Override
  public String getPortfolioName() {
    return portfolioNameField.getText();
  }

  @Override
  public String getPortfolioNameQuery() {
    return portfolioNameQueryField.getText();
  }

  @Override
  public String getDateQuery() {
    return dateQueryField.getText();
  }

  @Override
  public int getStockNumber() {
    return Integer.parseInt(stockNumberField.getText());
  }

  @Override
  public String getStockName() {
    return stockNameField.getText().toUpperCase();
  }

  /**
   * Class to enable placeholders on the date textfield. Static and within this class because
   * it only makes sense within the GUI. A custom JTextField that displays a placeholde
   * r text when the field is empty and not focused.
   */
  public static class JTextFieldWithPlaceholder extends JTextField implements FocusListener {
    private final String placeholder;
    private boolean showingPlaceholder;

    /**
     * Constructs a JTextFieldWithPlaceholder with the specified placeholder text.
     *
     * @param placeholder the placeholder text to display when the field is empty and not focused.
     */
    public JTextFieldWithPlaceholder(final String placeholder) {
      super(placeholder);
      this.placeholder = placeholder;
      this.showingPlaceholder = true;
      super.addFocusListener(this);
      this.setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
      if (this.getText().isEmpty()) {
        super.setText("");
        showingPlaceholder = false;
        this.setForeground(Color.BLACK);
      }
    }

    @Override
    public void focusLost(FocusEvent e) {
      if (this.getText().isEmpty()) {
        super.setText(placeholder);
        showingPlaceholder = true;
        this.setForeground(Color.GRAY);
      }
    }

    @Override
    public String getText() {
      return showingPlaceholder ? "" : super.getText();
    }
  }

}