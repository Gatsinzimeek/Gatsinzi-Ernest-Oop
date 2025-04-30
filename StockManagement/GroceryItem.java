import java.time.LocalDate;

public class GroceryItem extends StockItem {
    private final LocalDate expirationDate;
    private final double discountPercent;

    public GroceryItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier, LocalDate expirationDate, double discountPercent) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.expirationDate = expirationDate;
        this.discountPercent = discountPercent;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (1 - discountPercent / 100.0);
    }

    @Override
    public boolean validateStock() {
        boolean valid = true;
        if (quantityInStock < 0) {
            System.out.println("Quantity cannot be negative.");
            valid = false;
        }
        if (pricePerUnit <= 0) {
            System.out.println("Price per unit must be above zero.");
            valid = false;
        }
        if (discountPercent < 0 || discountPercent > 50) {
            System.out.println("Discount must be between 0% and 50%.");
            valid = false;
        }
        if (expirationDate.isBefore(LocalDate.now())) {
            System.out.println("Product is expired!");
            valid = false;
        }
        return valid;
    }

    @Override
    public void generateStockReport() {
        String status = (expirationDate.isBefore(LocalDate.now().plusDays(7))) ? "[NEAR EXPIRY]" : "";
        System.out.println("GroceryItem Report: " + itemName + " " + status + ", Quantity: " + quantityInStock + ", Price: " + pricePerUnit);
    }
}
