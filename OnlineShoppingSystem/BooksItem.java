public class BooksItem extends ShoppingItem {
    private String isbn;
    private String edition;
    private String printQuality;

    public BooksItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String isbn, String edition, String printQuality) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.isbn = isbn;
        this.edition = edition;
        this.printQuality = printQuality;
    }

    @Override
    public void updateStock(int quantity) {
        stockAvailable -= quantity;
    }

    @Override
    public boolean addToCart(Customer customer, ShoppingCart cart, int quantity, java.util.Scanner sc) {
        if (!validateItem() || quantity > stockAvailable) {
            System.out.println("Book not available in requested quantity/edition/quality.");
            return false;
        }
        cart.addItem(this, quantity);
        stockAvailable -= quantity;
        System.out.println(quantity + " x " + itemName + " added to cart.");
        return true;
    }

    @Override
    public void generateInvoice(Customer customer, int quantity) {
        System.out.println("Invoice: " + itemName + " x " + quantity + " | Price: " + price + " | ISBN: " + isbn + " | Edition: " + edition + " | Print: " + printQuality);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && price > 0 && edition != null && printQuality != null;
    }
}
