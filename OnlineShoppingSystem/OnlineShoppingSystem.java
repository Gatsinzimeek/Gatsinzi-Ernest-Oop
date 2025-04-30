import java.time.LocalDate;
import java.util.*;

public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<ShoppingItem> inventory = new ArrayList<>();
        SalesReport report = new SalesReport();

        // Sample Inventory
        inventory.add(new ElectronicsItem("E1", "Smartphone", "Latest 5G phone", 699.99, 20, 24));
        Map<String, Integer> sizeStock = new HashMap<>();
        sizeStock.put("S", 10); sizeStock.put("M", 15); sizeStock.put("L", 8); sizeStock.put("XL", 5);
        inventory.add(new ClothingItem("C1", "T-Shirt", "Cotton T-Shirt", 19.99, 38, sizeStock, true));
        inventory.add(new GroceriesItem("G1", "Rice", "5kg bag", 12.99, 50, LocalDate.now().plusMonths(6)));
        inventory.add(new BooksItem("B1", "Java Programming", "Comprehensive guide", 39.99, 12, "978-1234567890", "2nd", "High"));
        inventory.add(new AccessoriesItem("A1", "Wristband", "Silicone wristband", 4.99, 30, Arrays.asList("Red", "Blue", "Green")));

        // Customer Registration
        System.out.println("Enter your details:");
        System.out.print("Customer ID: ");
        int cidInt = -1;
        while (cidInt < 0) {
            while (!sc.hasNextInt()) {
                System.out.print("Invalid. Enter numeric Customer ID: ");
                sc.next();
            }
            cidInt = sc.nextInt();
            sc.nextLine(); // consume newline
            if (cidInt < 0) {
                System.out.print("Customer ID must be positive. Try again: ");
            }
        }
        String cid = String.valueOf(cidInt);
        System.out.print("Name: ");
        String name = sc.nextLine();
        while (name == null || name.trim().isEmpty() || !name.matches("[a-zA-Z ]+")) {
            System.out.print("Invalid. Enter Name (letters only): ");
            name = sc.nextLine();
        }
        System.out.print("Email: ");
        String email = sc.nextLine();
        while (email == null || !email.contains("@")) {
            System.out.print("Invalid. Enter Email: ");
            email = sc.nextLine();
        }
        System.out.print("Address: ");
        String address = sc.nextLine();
        while (address == null || address.length() <= 5) {
            System.out.print("Invalid. Enter Address (min 6 chars): ");
            address = sc.nextLine();
        }
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        while (phone == null || phone.length() < 7) {
            System.out.print("Invalid. Enter Phone (min 7 digits): ");
            phone = sc.nextLine();
        }
        Customer customer = new Customer(cid, name, email, address, phone);
        if (!customer.validateDetails()) {
            System.out.println("Invalid customer details. Exiting.");
            return;
        }

        boolean running = true;
        while (running) {
            ShoppingCart cart = new ShoppingCart(UUID.randomUUID().toString(), customer);
            boolean shopping = true;
            while (shopping) {
            System.out.println("\nAvailable Items:");
            for (int i = 0; i < inventory.size(); i++) {
                ShoppingItem item = inventory.get(i);
                System.out.println((i+1) + ". " + item.getItemName() + " (Stock: " + item.getStockAvailable() + ") - $" + item.getPrice());
            }
            System.out.print("Select item number to add to cart (0 to checkout): ");
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            int choice = sc.nextInt();
            if (choice == 0) break;
            if (choice < 1 || choice > inventory.size()) {
                System.out.println("Invalid selection.");
                continue;
            }
            ShoppingItem selected = inventory.get(choice-1);
            int qty = -1;
            while (qty <= 0) {
                System.out.print("Enter quantity: ");
                while (!sc.hasNextInt()) {
                    System.out.print("Please enter a valid quantity: ");
                    sc.next();
                }
                qty = sc.nextInt();
                sc.nextLine(); // consume newline
                if (qty <= 0) {
                    System.out.println("Quantity must be positive.");
                }
            }
            if (qty > selected.getStockAvailable()) {
                System.out.println("Cannot add more than available stock.");
                continue;
            }
            boolean added = selected.addToCart(customer, cart, qty, sc);
            if (!added) {
                System.out.println("Failed to add item to cart.");
            }
        }

        System.out.println("\nYour Cart:");
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (CartItem ci : cart.getCartItems()) {
                System.out.println(ci.quantity + " x " + ci.item.getItemName() + (ci.option != null ? " (" + ci.option + ")" : "") + " @ $" + ci.price);
            }
            System.out.println("Total Price: $" + cart.getTotalPrice());
        }

        // Payment
        String payMethod = "";
        while (true) {
            System.out.print("Enter payment method (CreditCard/PayPal): ");
            payMethod = sc.nextLine();
            if (payMethod.equals("CreditCard") || payMethod.equals("PayPal")) break;
            System.out.println("Invalid payment method. Please enter 'CreditCard' or 'PayPal'.");
        }
        double amt = -1;
        while (true) {
            System.out.print("Enter amount to pay: ");
            while (!sc.hasNextDouble()) {
                System.out.print("Please enter a valid amount: ");
                sc.next();
            }
            amt = sc.nextDouble();
            sc.nextLine();
            if (amt < 0) {
                System.out.println("Amount must be non-negative.");
                continue;
            }
            Payment payment = new Payment(UUID.randomUUID().toString(), payMethod, amt, LocalDate.now());
            if (!payment.validatePayment(cart.getTotalPrice())) {
                System.out.println("Amount paid does not match total price. Please try again.");
                continue;
            }
            System.out.println("Payment successful!");
            report.recordOrder(cart, payment);

            // Generate Invoice
            System.out.println("\n--- Invoice ---");
            System.out.println("Customer: " + customer.getCustomerName());
            for (CartItem ci : cart.getCartItems()) {
                ci.item.generateInvoice(customer, ci.quantity);
            }
            System.out.println("Total Paid: $" + payment.getAmountPaid());
            System.out.println("Payment Method: " + payment.getPaymentMethod());
            System.out.println("Shipping to: " + customer.getAddress());
            System.out.println("----------------");
            report.generateReport();
            break;
        }
        // Ask if user wants to shop again
        System.out.print("Do you want to shop again? (yes/no): ");
        String again = sc.nextLine();
        if (!again.equalsIgnoreCase("yes")) {
            running = false;
            System.out.println("Thank you for shopping with us!");
        }
    }
    sc.close();
}}
