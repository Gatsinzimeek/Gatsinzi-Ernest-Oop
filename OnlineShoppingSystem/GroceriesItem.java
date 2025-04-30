import java.time.LocalDate;

public class GroceriesItem extends ShoppingItem {
    private LocalDate expiryDate;

    public GroceriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, LocalDate expiryDate) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.expiryDate = expiryDate;
    }

    @Override
    public void updateStock(int quantity) {
        stockAvailable -= quantity;
    }

    @Override
    public boolean addToCart(Customer customer, ShoppingCart cart, int quantity, java.util.Scanner sc) {
        if (!validateItem()) {
            System.out.println("Item expired.");
            return false;
        }
        double finalPrice = price;
        if (quantity >= 10) {
            finalPrice *= 0.95; // 5% discount for bulk
            System.out.println("Bulk discount applied!");
        }
        if (quantity <= stockAvailable) {
            cart.addItem(this, quantity, null, finalPrice);
            stockAvailable -= quantity;
            System.out.println(quantity + " x " + itemName + " added to cart.");
            return true;
        } else {
            System.out.println("Insufficient stock.");
            return false;
        }
    }

    @Override
    public void generateInvoice(Customer customer, int quantity) {
        System.out.println("Invoice: " + itemName + " x " + quantity + " | Price: " + price + " | Expiry: " + expiryDate);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && price > 0 && expiryDate.isAfter(LocalDate.now());
    }
}
