public class FurnitureItem extends StockItem {
    private double weight;
    private boolean assembled;

    public FurnitureItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier, double weight, boolean assembled) {
        super(itemId, itemName, quantityInStock, pricePerUnit, category, supplier);
        this.weight = weight;
        this.assembled = assembled;
    }

    public boolean validateStock() {
        // Example validation: quantity and price must be positive
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
        System.out.println("FurnitureItem Report: " + itemName + ", Quantity: " + quantityInStock + ", Price: " + pricePerUnit);
    }
}

