import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InsuranceSystemFrame extends JFrame {
    private JTextArea outputArea;

    public InsuranceSystemFrame() {
        setTitle("Advanced Motor Vehicle Insurance System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 8, 8));
        String[] options = {
            "Add Comprehensive Policy",
            "Add Third Party Policy",
            "Add Collision Policy",
            "Add Liability Policy",
            "Add Roadside Assistance Policy",
            "File a Claim",
            "Generate Reports",
            "Exit"
        };
        for (String opt : options) {
            JButton btn = new JButton(opt);
            btn.addActionListener(e -> handleMenu(opt));
            buttonPanel.add(btn);
        }
        add(buttonPanel, BorderLayout.WEST);
    }

    private void handleMenu(String opt) {
        switch (opt) {
            case "Add Comprehensive Policy":
                addComprehensivePolicy();
                break;
            case "Add Third Party Policy":
                addThirdPartyPolicy();
                break;
            case "Add Collision Policy":
                addCollisionPolicy();
                break;
            case "Add Liability Policy":
                addLiabilityPolicy();
                break;
            case "Add Roadside Assistance Policy":
                addRoadsideAssistancePolicy();
                break;
            case "File a Claim":
                fileAClaim();
                break;
            case "Generate Reports":
                generateReports();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    // --- Menu Methods (Stubs with Validation Examples) ---
    private void addComprehensivePolicy() {
        String policyNumber = promptInt("Enter Policy Number (integer):");
        String owner = promptNonEmpty("Enter Owner Name:");
        String vehicle = promptNonEmpty("Enter Vehicle Info:");
        String premium = promptDouble("Enter Premium Amount:");
        // Add more fields and validations as needed
        showInfo("Comprehensive Policy added for " + owner + ".");
    }
    private void addThirdPartyPolicy() {
        // Similar to addComprehensivePolicy, with relevant fields
        showInfo("Third Party Policy added.");
    }
    private void addCollisionPolicy() {
        showInfo("Collision Policy added.");
    }
    private void addLiabilityPolicy() {
        showInfo("Liability Policy added.");
    }
    private void addRoadsideAssistancePolicy() {
        showInfo("Roadside Assistance Policy added.");
    }
    private void fileAClaim() {
        showInfo("Claim filed.");
    }
    private void generateReports() {
        showInfo("Report generated.");
    }

    // --- Validation Helpers ---
    private String promptNonEmpty(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, message);
            if (input == null) return null;
            input = input.trim();
            if (!input.isEmpty()) return input;
            showError("Input cannot be empty.");
        }
    }
    private String promptInt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, message);
            if (input == null) return null;
            try {
                Integer.parseInt(input.trim());
                return input.trim();
            } catch (Exception e) {
                showError("Please enter a valid integer.");
            }
        }
    }
    private String promptDouble(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, message);
            if (input == null) return null;
            try {
                Double.parseDouble(input.trim());
                return input.trim();
            } catch (Exception e) {
                showError("Please enter a valid number.");
            }
        }
    }
    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    private void showInfo(String msg) {
        outputArea.append(msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InsuranceSystemFrame().setVisible(true));
    }
}
