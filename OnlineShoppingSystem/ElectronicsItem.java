public class ElectronicsItem extends ShoppingItem {
    private int warrantyMonths;
    private boolean registered;

    public ElectronicsItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, int warrantyMonths) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.warrantyMonths = warrantyMonths;
        this.registered = false;
    }

    @Override
    public void updateStock(int quantity) {
        stockAvailable -= quantity;
    }

    @Override
    public boolean addToCart(Customer customer, ShoppingCart cart, int quantity, java.util.Scanner sc) {
        if (validateItem() && quantity <= stockAvailable) {
            cart.addItem(this, quantity);
            System.out.println(quantity + " x " + itemName + " added to cart.");
            return true;
        } else {
            System.out.println("Insufficient stock or invalid item.");
            return false;
        }
    }

    @Override
    public void generateInvoice(Customer customer, int quantity) {
        System.out.println("Invoice: " + itemName + " x " + quantity + " | Price: " + price + " | Warranty: " + warrantyMonths + " months");
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && price > 0;
    }

    public void registerProduct() {
        registered = true;
        System.out.println(itemName + " registered for warranty.");
    }
}
