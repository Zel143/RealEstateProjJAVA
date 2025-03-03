import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RealEstateFrame extends JFrame {
    private JTextArea displayArea;
    private LotManager lotManager;

    public RealEstateFrame() {
        setTitle("Real Estate Management System");
        lotManager = new LotManager();
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        setLayout(new BorderLayout());
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(6, 2, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Welcome message
        displayArea.setText("""
                           Welcome to Real Estate Management System
                           
                           Use the controls below to manage properties:
                           - Add Lot: Create a new property listing
                           - Search Lot: Find a property by ID
                           - Sell Lot: Mark a property as sold
                           - Reserve Lot: Place a hold on a property
                           - Generate Report: View all property listings
                           """);

        addControl(controlPanel, "Add Lot:", e -> {
            JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
            JTextField blockField = new JTextField();
            JTextField lotNumberField = new JTextField();
            JTextField sizeField = new JTextField();
            JTextField priceField = new JTextField();
            
            inputPanel.add(new JLabel("Block Number:"));
            inputPanel.add(blockField);
            inputPanel.add(new JLabel("Lot Number:"));
            inputPanel.add(lotNumberField);
            inputPanel.add(new JLabel("Size (sqm):"));
            inputPanel.add(sizeField);
            inputPanel.add(new JLabel("Price ($):"));
            inputPanel.add(priceField);
            
            int result = JOptionPane.showConfirmDialog(this, inputPanel, 
                    "Enter Lot Details", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String lotDetails = blockField.getText() + "," + 
                                        lotNumberField.getText() + "," + 
                                        sizeField.getText() + "," + 
                                        priceField.getText();
                    displayArea.setText(lotManager.addLot(lotDetails));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, 
                            "Please enter valid numbers for all fields", 
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addControl(controlPanel, "Search Lot:", e -> {
            String lotId = getInput("Enter lot ID (e.g., Lot1):", "Search Lot");
            if (lotId != null && !lotId.isEmpty()) {
                displayArea.setText(lotManager.searchLot(lotId));
            }
        });

        addControl(controlPanel, "Sell Lot:", e -> {
            String lotId = getInput("Enter lot ID to mark as sold (e.g., Lot1):", "Sell Lot");
            if (lotId != null && !lotId.isEmpty()) {
                displayArea.setText(lotManager.sellLot(lotId));
            }
        });

        addControl(controlPanel, "Reserve Lot:", e -> {
            String lotId = getInput("Enter lot ID to reserve (e.g., Lot1):", "Reserve Lot");
            if (lotId != null && !lotId.isEmpty()) {
                displayArea.setText(lotManager.reserveLot(lotId));
            }
        });

        JLabel reportLabel = new JLabel("View all properties:");
        JButton reportButton = new JButton("Generate Report");
        reportButton.addActionListener(e -> displayArea.setText(lotManager.generateReport()));
        controlPanel.add(reportLabel);
        controlPanel.add(reportButton);
        
        JLabel clearLabel = new JLabel("Clear display:");
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> displayArea.setText(""));
        controlPanel.add(clearLabel);
        controlPanel.add(clearButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void addControl(JPanel panel, String label, ActionListener actionListener) {
        JLabel jLabel = new JLabel(label);
        JButton button = new JButton(label.split(" ")[0]);
        button.addActionListener(actionListener);
        panel.add(jLabel);
        panel.add(button);
    }

    private String getInput(String message, String title) {
        return JOptionPane.showInputDialog(this, message, title, JOptionPane.QUESTION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RealEstateFrame frame = new RealEstateFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}