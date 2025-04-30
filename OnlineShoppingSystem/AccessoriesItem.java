import java.util.*;

public class AccessoriesItem extends ShoppingItem {
    private List<String> varieties;
    private List<String> reviews;
    private List<Integer> ratings;

    public AccessoriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, List<String> varieties) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.varieties = varieties;
        this.reviews = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    @Override
    public void updateStock(int quantity) {
        stockAvailable -= quantity;
    }

    @Override
    public boolean addToCart(Customer customer, ShoppingCart cart, int quantity, java.util.Scanner sc) {
        System.out.print("Enter variety: " + varieties + ": ");
        String variety = sc.next();
        if (!varieties.contains(variety)) {
            System.out.println("Variety not available.");
            return false;
        }
        if (quantity > stockAvailable) {
            System.out.println("Insufficient stock.");
            return false;
        }
        cart.addItem(this, quantity, variety, price);
        stockAvailable -= quantity;
        System.out.println(quantity + " x " + itemName + " (" + variety + ") added to cart.");
        return true;
    }

    @Override
    public void generateInvoice(Customer customer, int quantity) {
        System.out.println("Invoice: " + itemName + " x " + quantity + " | Price: " + price);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && price > 0 && !varieties.isEmpty();
    }

    public void addReview(String review, int rating) {
        reviews.add(review);
        ratings.add(rating);
    }

    public double getAverageRating() {
        return ratings.stream().mapToInt(i -> i).average().orElse(0.0);
    }
}
