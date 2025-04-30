import java.util.Map;

public class ClothingItem extends ShoppingItem {
    private Map<String, Integer> sizeStock;
    private boolean seasonal;

    public ClothingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, Map<String, Integer> sizeStock, boolean seasonal) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.sizeStock = sizeStock;
        this.seasonal = seasonal;
    }

    @Override
    public void updateStock(int quantity) {
        // Not used directly; handled per size
    }

    @Override
    public boolean addToCart(Customer customer, ShoppingCart cart, int quantity, java.util.Scanner sc) {
        System.out.print("Enter size (S/M/L/XL): ");
        String size = sc.next();
        if (!sizeStock.containsKey(size) || sizeStock.get(size) < quantity) {
            System.out.println("Insufficient stock for size " + size);
            return false;
        }
        double finalPrice = price;
        if (seasonal) {
            finalPrice *= 0.9; // 10% discount
            System.out.println("Seasonal discount applied!");
        }
        cart.addItem(this, quantity, size, finalPrice);
        sizeStock.put(size, sizeStock.get(size) - quantity);
        System.out.println(quantity + " x " + itemName + " (" + size + ") added to cart.");
        return true;
    }

    @Override
    public void generateInvoice(Customer customer, int quantity) {
        System.out.println("Invoice: " + itemName + " x " + quantity + " | Price: " + price + (seasonal ? " (Seasonal Discount)" : ""));
    }

    @Override
    public boolean validateItem() {
        return price > 0 && sizeStock.values().stream().anyMatch(v -> v > 0);
    }
}
