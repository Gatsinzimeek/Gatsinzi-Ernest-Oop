import java.time.LocalDate;

public class PerishableItem extends StockItem {
    private final LocalDate expirationDate;
    private final int shelfLifeDays;

    public PerishableItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier, LocalDate expirationDate, int shelfLifeDays) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.expirationDate = expirationDate;
        this.shelfLifeDays = shelfLifeDays;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit;
    }


    @Override
    public void generateStockReport() {
        String status = (expirationDate.isBefore(LocalDate.now())) ? "[EXPIRED]" : (expirationDate.isBefore(LocalDate.now().plusDays(3)) ? "[DISPOSE SOON]" : "");
        System.out.println("[PerishableItem] " + itemName + " | Stock: " + quantityInStock + " | Price: " + pricePerUnit + " | Expiry: " + expirationDate + " " + status);
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
        if (shelfLifeDays <= 0 || shelfLifeDays > 14) {
            System.out.println("Shelf life for perishable items must be short (1-14 days).");
            valid = false;
        }
        if (expirationDate.isBefore(LocalDate.now())) {
            System.out.println("Product is expired! Needs disposal.");
            valid = false;
        }
        return valid;
    }
}
