import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineShoppingSystemFrame extends JFrame {
    private JTextArea outputArea;

    public OnlineShoppingSystemFrame() {
        setTitle("Online Shopping System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 8, 8));
        String[] options = {
            "Register Customer",
            "Browse Items",
            "Add Item to Cart",
            "View Cart",
            "Checkout",
            "Generate Sales Report",
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
            case "Register Customer":
                registerCustomer();
                break;
            case "Browse Items":
                browseItems();
                break;
            case "Add Item to Cart":
                addItemToCart();
                break;
            case "View Cart":
                viewCart();
                break;
            case "Checkout":
                checkout();
                break;
            case "Generate Sales Report":
                generateSalesReport();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    // --- Menu Methods (Stubs with Validation Examples) ---
    private void registerCustomer() {
        String id = promptInt("Enter Customer ID (integer):");
        String name = promptNonEmpty("Enter Name:");
        showInfo("Customer registered: " + name + " (ID: " + id + ")");
    }
    private void browseItems() {
        showInfo("Browsing items...");
    }
    private void addItemToCart() {
        String itemId = promptInt("Enter Item ID (integer):");
        String qty = promptInt("Enter Quantity (integer):");
        showInfo("Added Item " + itemId + " x" + qty + " to cart.");
    }
    private void viewCart() {
        showInfo("Viewing cart...");
    }
    private void checkout() {
        showInfo("Checkout complete.");
    }
    private void generateSalesReport() {
        showInfo("Sales report generated.");
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
    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    private void showInfo(String msg) {
        outputArea.append(msg + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OnlineShoppingSystemFrame().setVisible(true));
    }
}
