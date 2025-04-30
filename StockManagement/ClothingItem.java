import java.util.Set;

public class ClothingItem extends StockItem {
    private final Set<String> availableSizes;
    private final Set<String> availableColors;
    private final double discountPercent;

    public ClothingItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier, Set<String> availableSizes, Set<String> availableColors, double discountPercent) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.availableSizes = availableSizes;
        this.availableColors = availableColors;
        this.discountPercent = discountPercent;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
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
        if (availableSizes == null || availableSizes.isEmpty()) {
            System.out.println("At least one size must be available.");
            valid = false;
        }
        if (availableColors == null || availableColors.isEmpty()) {
            System.out.println("At least one color must be available.");
            valid = false;
        }
        return valid;
    }

    @Override
    public void generateStockReport() {
        String colorList = String.join(", ", availableColors);
        String sizeList = String.join(", ", availableSizes);
        System.out.println("ClothingItem Report: " + itemName + " | Colors: " + colorList + " | Sizes: " + sizeList + ", Quantity: " + quantityInStock + ", Price: " + pricePerUnit);
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (1 - discountPercent / 100.0);
    }
}
