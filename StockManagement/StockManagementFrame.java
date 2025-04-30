import java.awt.*;
import javax.swing.*;

public class StockManagementFrame extends JFrame {
    private static final java.util.List<StockItem> stockItems = new java.util.ArrayList<>();
    private static final java.util.Set<String> productNames = new java.util.HashSet<>();
    private final JTextArea reportArea = new JTextArea();

    private static final String[] CATEGORIES = {"Electronics", "Clothing", "Groceries", "Furniture", "Perishable"};
    public StockManagementFrame() {
        setTitle("Stock Management System");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header/description
        JLabel header = new JLabel("Advanced Stock Management System - Exercise 1", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        JButton addElectronicsBtn = new JButton("Add Electronics Item");
        JButton addClothingBtn = new JButton("Add Clothing Item");
        JButton addGroceryBtn = new JButton("Add Grocery Item");
        JButton addFurnitureBtn = new JButton("Add Furniture Item");
        JButton addPerishableBtn = new JButton("Add Perishable Item");
        JButton reportBtn = new JButton("Generate Inventory Report");
        buttonPanel.add(addElectronicsBtn);
        buttonPanel.add(addClothingBtn);
        buttonPanel.add(addGroceryBtn);
        buttonPanel.add(addFurnitureBtn);
        buttonPanel.add(addPerishableBtn);
        buttonPanel.add(reportBtn);
        add(buttonPanel, BorderLayout.CENTER);

        // Report area
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        add(scrollPane, BorderLayout.SOUTH);

        // Button actions
        addElectronicsBtn.addActionListener(e -> addElectronicsItem());
        addClothingBtn.addActionListener(e -> addClothingItem());
        addGroceryBtn.addActionListener(e -> addGroceryItem());
        addFurnitureBtn.addActionListener(e -> addFurnitureItem());
        addPerishableBtn.addActionListener(e -> addPerishableItem());
        reportBtn.addActionListener(e -> generateInventoryReport());
    }

    // Helper for category selection with dropdown
    private String chooseCategory() {
        JComboBox<String> combo = new JComboBox<>(CATEGORIES);
        int result = JOptionPane.showConfirmDialog(this, combo, "Select Category", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return (String) combo.getSelectedItem();
        }
        return null;
    }

    // Helper for unique name input
    private String getUniqueNameInput(String prompt) {
        while (true) {
            String input = promptWord(prompt);
            if (input == null) return null;
            if (!productNames.add(input)) {
                showError("Product name must be unique.");
                continue;
            }
            return input;
        }
    }

    // Helper for ID input (integers only)
    private String getIdInput(String prompt) {
        while (true) {
            String input = prompt(prompt);
            if (input == null) return null;
            input = input.trim();
            if (input.isEmpty()) {
                showError("Item ID cannot be empty. Please enter an integer value.");
            } else {
                try {
                    Integer.parseInt(input);
                    return input;
                } catch (NumberFormatException e) {
                    showError("Invalid Item ID. Please enter integers only (e.g., 123).");
                }
            }
        }
    }

    // Helper for positive integer input
    private int getPositiveIntInput(String prompt) {
        while (true) {
            int value = promptInt(prompt);
            if (value < 0) {
                showError("Value must be zero or positive.");
            } else {
                return value;
            }
        }
    }

    // Helper for positive double input
    private double getPositiveDoubleInput(String prompt) {
        while (true) {
            double value = promptDouble(prompt);
            if (value <= 0) {
                showError("Value must be above zero.");
            } else {
                return value;
            }
        }
    }

    // Helper for double in range
    private double getDoubleInRangeInput(String prompt, double min, double max) {
        while (true) {
            double value = promptDouble(prompt);
            if (value < min || value > max) {
                showError("Value must be between " + min + " and " + max + ".");
            } else {
                return value;
            }
        }
    }

    // Helper for boolean input
    private boolean getBooleanInput(String prompt) {
        while (true) {
            String input = prompt(prompt + " (true/false):");
            if (input == null) return false;
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(input);
            } else {
                showError("Invalid input. Please enter 'true' or 'false'.");
            }
        }
    }

    // Helper for date input
    private java.time.LocalDate getDateInput(String prompt, boolean notPast) {
        while (true) {
            String input = prompt(prompt + " (yyyy-mm-dd):");
            if (input == null) return null;
            try {
                java.time.LocalDate date = java.time.LocalDate.parse(input);
                if (notPast && date.isBefore(java.time.LocalDate.now())) {
                    showError("Date cannot be in the past.");
                } else {
                    return date;
                }
            } catch (Exception e) {
                showError("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
    }

    private String prompt(String message) {
        return JOptionPane.showInputDialog(this, message);
    }
    private int promptInt(String message) {
        while (true) {
            String input = prompt(message);
            if (input == null) return -1;
            try { return Integer.parseInt(input); } catch (Exception e) { showError("Enter a valid integer."); }
        }
    }
    private double promptDouble(String message) {
        while (true) {
            String input = prompt(message);
            if (input == null) return -1;
            try { return Double.parseDouble(input); } catch (Exception e) { showError("Enter a valid number."); }
        }
    }
    private String promptWord(String message) {
        while (true) {
            String input = prompt(message);
            if (input == null) return null;
            if (input.trim().matches("[a-zA-Z ]+")) return input.trim();
            showError("Enter letters only.");
        }
    }
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addElectronicsItem() {
        String id = getIdInput("Item ID:");
        if (id == null) return;
        String name = getUniqueNameInput("Item Name:");
        if (name == null) return;
        int qty = getPositiveIntInput("Quantity In Stock:");
        double price = getPositiveDoubleInput("Price Per Unit:");
        String cat = chooseCategory();
        if (cat == null) return;
        String supplier = promptWord("Supplier:");
        int warranty = (int) getDoubleInRangeInput("Warranty (months, 0-60):", 0, 60);
        double discount = getDoubleInRangeInput("Discount (%):", 0, 100);
        ElectronicsItem item = new ElectronicsItem(id, name, qty, price, cat, supplier, warranty, discount);
        if (item.validateStock()) {
            stockItems.add(item);
            showInfo("Electronics item added.");
        } else {
            productNames.remove(name);
        }
    }
    private void addClothingItem() {
        String id = getIdInput("Item ID:");
        if (id == null) return;
        String name = getUniqueNameInput("Item Name:");
        if (name == null) return;
        int qty = getPositiveIntInput("Quantity In Stock:");
        double price = getPositiveDoubleInput("Price Per Unit:");
        String cat = chooseCategory();
        if (cat == null) return;
        String supplier = promptWord("Supplier:");
        String sizesStr = prompt("Sizes (comma-separated):");
        java.util.Set<String> sizes = new java.util.HashSet<>();
        if (sizesStr != null && !sizesStr.trim().isEmpty()) for (String s : sizesStr.split(",")) sizes.add(s.trim());
        String colorsStr = prompt("Colors (comma-separated):");
        java.util.Set<String> colors = new java.util.HashSet<>();
        if (colorsStr != null && !colorsStr.trim().isEmpty()) for (String s : colorsStr.split(",")) colors.add(s.trim());
        double discount = getDoubleInRangeInput("Discount (%):", 0, 100);
        ClothingItem item = new ClothingItem(id, name, qty, price, cat, supplier, sizes, colors, discount);
        if (item.validateStock()) {
            stockItems.add(item);
            showInfo("Clothing item added.");
        } else {
            productNames.remove(name);
        }
    }
    private void addGroceryItem() {
        String id = getIdInput("Item ID:");
        if (id == null) return;
        String name = getUniqueNameInput("Item Name:");
        if (name == null) return;
        int qty = getPositiveIntInput("Quantity In Stock:");
        double price = getPositiveDoubleInput("Price Per Unit:");
        String cat = chooseCategory();
        if (cat == null) return;
        String supplier = promptWord("Supplier:");
        java.time.LocalDate exp = getDateInput("Expiration Date", true);
        if (exp == null) return;
        double discount = getDoubleInRangeInput("Discount (%):", 0, 100);
        GroceryItem item = new GroceryItem(id, name, qty, price, cat, supplier, exp, discount);
        if (item.validateStock()) {
            stockItems.add(item);
            showInfo("Grocery item added.");
        } else {
            productNames.remove(name);
        }
    }
    private void addFurnitureItem() {
        String id = getIdInput("Item ID:");
        if (id == null) return;
        String name = getUniqueNameInput("Item Name:");
        if (name == null) return;
        int qty = getPositiveIntInput("Quantity In Stock:");
        double price = getPositiveDoubleInput("Price Per Unit:");
        String cat = chooseCategory();
        if (cat == null) return;
        String supplier = promptWord("Supplier:");
        double weight = getPositiveDoubleInput("Weight (kg):");
        boolean packed = getBooleanInput("Well Packed");
        FurnitureItem item = new FurnitureItem(id, name, qty, price, cat, supplier, weight, packed);
        if (item.validateStock()) {
            stockItems.add(item);
            showInfo("Furniture item added.");
        } else {
            productNames.remove(name);
        }
    }
    private void addPerishableItem() {
        String id = getIdInput("Item ID:");
        if (id == null) return;
        String name = getUniqueNameInput("Item Name:");
        if (name == null) return;
        int qty = getPositiveIntInput("Quantity In Stock:");
        double price = getPositiveDoubleInput("Price Per Unit:");
        String cat = chooseCategory();
        if (cat == null) return;
        String supplier = promptWord("Supplier:");
        java.time.LocalDate exp = getDateInput("Expiration Date", true);
        if (exp == null) return;
        int shelf = (int) getDoubleInRangeInput("Shelf Life (days, 1-14):", 1, 14);
        PerishableItem item = new PerishableItem(id, name, qty, price, cat, supplier, exp, shelf);
        if (item.validateStock()) {
            stockItems.add(item);
            showInfo("Perishable item added.");
        } else {
            productNames.remove(name);
        }
    }
    private void generateInventoryReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Inventory Report ---\n");
        for (StockItem item : stockItems) {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.PrintStream ps = new java.io.PrintStream(baos);
            java.io.PrintStream oldOut = System.out;
            System.setOut(ps);
            item.generateStockReport();
            System.out.flush();
            System.setOut(oldOut);
            sb.append(baos.toString());
        }
        reportArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new StockManagementFrame().setVisible(true);
        });
    }
}
