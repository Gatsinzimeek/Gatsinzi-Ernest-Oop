import java.util.*;

public class ShoppingCart {
    private String cartId;
    private List<CartItem> cartItems;
    private double totalPrice;
    private Customer customer;

    public ShoppingCart(String cartId, Customer customer) {
        this.cartId = cartId;
        this.cartItems = new ArrayList<>();
        this.totalPrice = 0;
        this.customer = customer;
    }

    public void addItem(ShoppingItem item, int quantity) {
        addItem(item, quantity, null, item.getPrice());
    }

    public void addItem(ShoppingItem item, int quantity, String option, double price) {
        for (CartItem ci : cartItems) {
            if (ci.item == item && Objects.equals(ci.option, option)) {
                if (ci.quantity + quantity > item.getStockAvailable()) {
                    System.out.println("Cannot add more than available stock.");
                    return;
                }
                ci.quantity += quantity;
                totalPrice += price * quantity;
                return;
            }
        }
        if (quantity > item.getStockAvailable()) {
            System.out.println("Cannot add more than available stock.");
            return;
        }
        cartItems.add(new CartItem(item, quantity, option, price));
        totalPrice += price * quantity;
    }

    public void removeItem(ShoppingItem item, String option) {
        cartItems.removeIf(ci -> ci.item == item && Objects.equals(ci.option, option));
    }

    public double getTotalPrice() { return totalPrice; }
    public List<CartItem> getCartItems() { return cartItems; }
    public Customer getCustomer() { return customer; }
}
