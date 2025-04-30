public class Product {
    private final String productId;
    private final String productName;
    private final String brand;
    private final String supplier;
    private final int stockQuantity;

    public Product(String productId, String productName, String brand, String supplier, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.supplier = supplier;
        this.stockQuantity = stockQuantity;
    }

    public boolean validateProduct() {
        if (productName == null || productName.isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return false;
        }
        if (stockQuantity < 0) {
            System.out.println("Stock quantity cannot be negative.");
            return false;
        }
        if (brand == null || brand.isEmpty()) {
            System.out.println("Brand cannot be empty.");
            return false;
        }
        return true;
    }
    // Getters and setters (if needed)
}
