import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RealEstateFrame extends JFrame {
    private JTextArea displayArea;
    private LotManager lotManager;

    public RealEstateFrame() {
        lotManager = new LotManager();
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        setLayout(new BorderLayout());
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 2));

        addControl(controlPanel, "Add Lot:", e -> {
            String lotDetails = getInput("Enter lot details:");
            if (lotDetails != null) {
                displayArea.setText(lotManager.addLot(lotDetails));
            }
        });

        addControl(controlPanel, "Search Lot:", e -> {
            String lotId = getInput("Enter lot ID:");
            if (lotId != null) {
                displayArea.setText(lotManager.searchLot(lotId));
            }
        });

        addControl(controlPanel, "Sell Lot:", e -> {
            String lotId = getInput("Enter lot ID:");
            if (lotId != null) {
                displayArea.setText(lotManager.sellLot(lotId));
            }
        });

        addControl(controlPanel, "Reserve Lot:", e -> {
            String lotId = getInput("Enter lot ID:");
            if (lotId != null) {
                displayArea.setText(lotManager.reserveLot(lotId));
            }
        });

        JButton reportButton = new JButton("Generate Report");
        reportButton.addActionListener(e -> displayArea.setText(lotManager.generateReport()));
        controlPanel.add(reportButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void addControl(JPanel panel, String label, ActionListener actionListener) {
        JLabel jLabel = new JLabel(label);
        JButton button = new JButton(label.split(" ")[0]);
        button.addActionListener(actionListener);
        panel.add(jLabel);
        panel.add(button);
    }

    private String getInput(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RealEstateFrame frame = new RealEstateFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}