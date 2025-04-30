public class CartItem {
    ShoppingItem item;
    int quantity;
    String option;
    double price;
    public CartItem(ShoppingItem item, int quantity, String option, double price) {
        this.item = item;
        this.quantity = quantity;
        this.option = option;
        this.price = price;
    }
}
