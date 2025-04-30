import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    // Prompt for integer input with retries and clear error messages
private static int getIntInput(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter an integer.");
            continue;
        }
        try {
            int value = Integer.parseInt(input);
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer (e.g., 42).");
        }
    }
}

    // Prompt for double input with retries and clear error messages
private static double getDoubleInput(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter a number.");
            continue;
        }
        try {
            double value = Double.parseDouble(input);
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number (e.g., 19.99).");
        }
    }
}

    private static final String[] CATEGORIES = {"Electronics", "Clothing", "Groceries", "Furniture", "Perishable"};

    private static String chooseCategory() {
    while (true) {
        System.out.println("Select a category:");
        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.printf("%d. %s\n", i + 1, CATEGORIES[i]);
        }
        System.out.print("Enter category number: ");
        String input = scanner.nextLine().trim();
        try {
            int idx = Integer.parseInt(input) - 1;
            if (idx >= 0 && idx < CATEGORIES.length) {
                return CATEGORIES[idx];
            }
        } catch (NumberFormatException ignored) {}
        System.out.println("Invalid selection. Please choose a valid category by number.");
    }
}

// Prompt for a single word or phrase (letters and spaces only)
private static String getWordInput(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter letters only.");
        } else if (input.matches("[a-zA-Z ]+")) {
            return input;
        } else {
            System.out.println("Invalid input. Please enter letters and spaces only (no numbers or special characters).");
        }
    }
}

    private static String getLineInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }

    private static String getIdInput(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Item ID cannot be empty. Please enter an integer value.");
        } else {
            try {
                Integer.parseInt(input);
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Item ID. Please enter integers only (e.g., 123).");
            }
        }
    }
}

    // Prompt for a unique product name (letters and spaces only)
private static String getUniqueNameInput(String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Name cannot be empty. Please enter a valid name (letters and spaces only).");
        } else if (!input.matches("[a-zA-Z ]+")) {
            System.out.println("Invalid name. Only letters and spaces are allowed. No numbers or special characters.");
        } else if (!productNames.add(input)) {
            System.out.println("Product name must be unique. Please enter a different name.");
        } else {
            return input;
        }
    }
}

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<StockItem> stockItems = new ArrayList<>();
    private static final Set<String> productNames = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("--- Advanced Stock Management System ---");
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:\n1. Add Electronics Item\n2. Add Clothing Item\n3. Add Grocery Item\n4. Add Furniture Item\n5. Add Perishable Item\n6. Generate Inventory Report\n7. Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput("");
            switch (choice) {
                case 1 -> addElectronicsItem();
                case 2 -> addClothingItem();
                case 3 -> addGroceryItem();
                case 4 -> addFurnitureItem();
                case 5 -> addPerishableItem();
                case 6 -> generateInventoryReport();
                case 7 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
        System.out.println("Exiting...");
    }

    private static void addElectronicsItem() {
    String id = getIdInput("Item ID: ");
    String name = getUniqueNameInput("Item Name: ");
    int qty;
    while (true) {
        qty = getIntInput("Quantity In Stock: ");
        if (qty >= 0) break;
        System.out.println("Quantity must be zero or positive.");
    }
    double price;
    while (true) {
        price = getDoubleInput("Price Per Unit: ");
        if (price > 0) break;
        System.out.println("Price per unit must be above zero.");
    }
    String cat = chooseCategory();
    String supplier = getWordInput("Supplier: ");
    int warranty;
    while (true) {
        warranty = getIntInput("Warranty (months, 0-60): ");
        if (warranty >= 0 && warranty <= 60) break;
        System.out.println("Warranty must be between 0 and 60 months.");
    }
    double discount;
    while (true) {
        discount = getDoubleInput("Discount (%): ");
        if (discount >= 0 && discount <= 50) break;
        System.out.println("Discount must be between 0% and 50%.");
    }
    ElectronicsItem item = new ElectronicsItem(id, name, qty, price, cat, supplier, warranty, discount);
    if (item.validateStock()) {
        stockItems.add(item);
        System.out.println("Electronics item added.");
    } else {
        productNames.remove(name);
    }
}

    private static void addClothingItem() {
    String id = getIdInput("Item ID: ");
    String name = getUniqueNameInput("Item Name: ");
    int qty;
    while (true) {
        qty = getIntInput("Quantity In Stock: ");
        if (qty >= 0) break;
        System.out.println("Quantity must be zero or positive.");
    }
    double price;
    while (true) {
        price = getDoubleInput("Price Per Unit: ");
        if (price > 0) break;
        System.out.println("Price per unit must be above zero.");
    }
    String cat = chooseCategory();
    String supplier = getWordInput("Supplier: ");
    Set<String> sizes;
    while (true) {
        String sizesInput = getLineInput("Sizes (comma-separated): ");
        sizes = new HashSet<>();
        for (String s : sizesInput.split(",")) {
            String size = s.trim();
            if (!size.isEmpty()) sizes.add(size);
        }
        if (!sizes.isEmpty()) break;
        System.out.println("At least one size must be specified.");
    }
    Set<String> colors;
    while (true) {
        String colorsInput = getLineInput("Colors (comma-separated): ");
        colors = new HashSet<>();
        for (String c : colorsInput.split(",")) {
            String color = c.trim();
            if (!color.isEmpty()) colors.add(color);
        }
        if (!colors.isEmpty()) break;
        System.out.println("At least one color must be specified.");
    }
    double discount;
    while (true) {
        discount = getDoubleInput("Discount (%): ");
        if (discount >= 0 && discount <= 50) break;
        System.out.println("Discount must be between 0% and 50%.");
    }
    ClothingItem item = new ClothingItem(id, name, qty, price, cat, supplier, sizes, colors, discount);
    if (item.validateStock()) {
        stockItems.add(item);
        System.out.println("Clothing item added.");
    } else {
        productNames.remove(name);
    }
}

    private static void addGroceryItem() {
    String id = getIdInput("Item ID: ");
    String name = getUniqueNameInput("Item Name: ");
    int qty;
    while (true) {
        qty = getIntInput("Quantity In Stock: ");
        if (qty >= 0) break;
        System.out.println("Quantity must be zero or positive.");
    }
    double price;
    while (true) {
        price = getDoubleInput("Price Per Unit: ");
        if (price > 0) break;
        System.out.println("Price per unit must be above zero.");
    }
    String cat = chooseCategory();
    String supplier = getWordInput("Supplier: ");
    LocalDate exp = null;
    while (true) {
        String expInput = getLineInput("Expiration Date (yyyy-mm-dd): ");
        try {
            exp = LocalDate.parse(expInput);
            if (exp.isBefore(LocalDate.now())) {
                System.out.println("Expiration date cannot be in the past.");
            } else {
                break;
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
        }
    }
    double discount;
    while (true) {
        discount = getDoubleInput("Discount (%): ");
        if (discount >= 0 && discount <= 50) break;
        System.out.println("Discount must be between 0% and 50%.");
    }
    GroceryItem item = new GroceryItem(id, name, qty, price, cat, supplier, exp, discount);
    if (item.validateStock()) {
        stockItems.add(item);
        System.out.println("Grocery item added.");
    } else {
        productNames.remove(name);
    }
}

    private static void addFurnitureItem() {
    String id = getIdInput("Item ID: ");
    String name = getUniqueNameInput("Item Name: ");
    int qty;
    while (true) {
        qty = getIntInput("Quantity In Stock: ");
        if (qty >= 0) break;
        System.out.println("Quantity must be zero or positive.");
    }
    double price;
    while (true) {
        price = getDoubleInput("Price Per Unit: ");
        if (price > 0) break;
        System.out.println("Price per unit must be above zero.");
    }
    String cat = chooseCategory();
    String supplier = getWordInput("Supplier: ");
    double weight;
    while (true) {
        weight = getDoubleInput("Weight (kg): ");
        if (weight > 0) break;
        System.out.println("Weight must be a positive number.");
    }
    boolean packed = false;
    while (true) {
        String packedInput = getLineInput("Well Packed (true/false): ").toLowerCase();
        if (packedInput.equals("true") || packedInput.equals("false")) {
            packed = Boolean.parseBoolean(packedInput);
            break;
        } else {
            System.out.println("Invalid input. Please enter 'true' or 'false'.");
        }
    }
    FurnitureItem item = new FurnitureItem(id, name, qty, price, cat, supplier, weight, packed);
    if (item.validateStock()) {
        stockItems.add(item);
        System.out.println("Furniture item added.");
    } else {
        productNames.remove(name);
    }
}

    private static void addPerishableItem() {
    String id = getIdInput("Item ID: ");
    String name = getUniqueNameInput("Item Name: ");
    int qty;
    while (true) {
        qty = getIntInput("Quantity In Stock: ");
        if (qty >= 0) break;
        System.out.println("Quantity must be zero or positive.");
    }
    double price;
    while (true) {
        price = getDoubleInput("Price Per Unit: ");
        if (price > 0) break;
        System.out.println("Price per unit must be above zero.");
    }
    String cat = chooseCategory();
    String supplier = getWordInput("Supplier: ");
    LocalDate exp = null;
    while (true) {
        String expInput = getLineInput("Expiration Date (yyyy-mm-dd): ");
        try {
            exp = LocalDate.parse(expInput);
            if (exp.isBefore(LocalDate.now())) {
                System.out.println("Expiration date cannot be in the past.");
            } else {
                break;
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
        }
    }
    int shelf;
    while (true) {
        shelf = getIntInput("Shelf Life (days, 1-14): ");
        if (shelf > 0 && shelf <= 14) break;
        System.out.println("Shelf life for perishable items must be short (1-14 days).");
    }
    PerishableItem item = new PerishableItem(id, name, qty, price, cat, supplier, exp, shelf);
    if (item.validateStock()) {
        stockItems.add(item);
        System.out.println("Perishable item added.");
    } else {
        productNames.remove(name);
    }
}

    private static void generateInventoryReport() {
        System.out.println("\n--- Inventory Report ---");
        double totalValue = 0;
        for (StockItem item : stockItems) {
            item.generateStockReport();
            totalValue += item.calculateStockValue();
        }
        System.out.println("\nTotal Inventory Value: " + totalValue);
        System.out.println("\n--- Expired or Nearly Expired Items ---");
        for (StockItem item : stockItems) {
            if (item instanceof GroceryItem) {
                GroceryItem g = (GroceryItem) item;
                if (!g.validateStock()) {
                    System.out.println("[EXPIRED/NEAR EXPIRY] " + g.itemName);
                }
            } else if (item instanceof PerishableItem) {
                PerishableItem p = (PerishableItem) item;
                if (!p.validateStock()) {
                    System.out.println("[EXPIRED/NEAR EXPIRY] " + p.itemName);
                }
            }
        }
        System.out.println("\n--- Discounts and Sales Performance ---");
        for (StockItem item : stockItems) {
            if (item instanceof ClothingItem) {
                System.out.println(item.itemName + " | Discounted Stock Value: " + ((ClothingItem)item).calculateStockValue());
            } else if (item instanceof GroceryItem) {
                System.out.println(item.itemName + " | Discounted Stock Value: " + ((GroceryItem)item).calculateStockValue());
            } else if (item instanceof ElectronicsItem) {
                System.out.println(item.itemName + " | Discounted Stock Value: " + ((ElectronicsItem)item).calculateStockValue());
            }
        }
    }
}
