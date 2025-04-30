
public class ElectronicsItem extends StockItem {
    private int warranty;
    private double discount;

    public ElectronicsItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier, int warranty, double discount) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.warranty = warranty;
        this.discount = discount;
    }

    public boolean validateStock() {
        return quantityInStock > 0 && pricePerUnit > 0;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return this.quantityInStock * this.pricePerUnit;
    }

    @Override
    public void generateStockReport() {
        System.out.println("ElectronicsItem Report: " + itemName + ", Quantity: " + quantityInStock + ", Price: " + pricePerUnit);
    }
}
