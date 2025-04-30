public abstract class ShoppingItem {
    protected String itemId;
    protected String itemName;
    protected String itemDescription;
    protected double price;
    protected int stockAvailable;

    public ShoppingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.stockAvailable = stockAvailable;
    }

    public abstract void updateStock(int quantity);
    public abstract boolean addToCart(Customer customer, ShoppingCart cart, int quantity, java.util.Scanner sc);
    public abstract void generateInvoice(Customer customer, int quantity);
    public abstract boolean validateItem();
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public int getStockAvailable() { return stockAvailable; }
}
