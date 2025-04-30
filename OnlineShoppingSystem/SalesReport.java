import java.util.*;

public class SalesReport {
    private double totalRevenue = 0;
    private Map<String, Integer> itemSales = new HashMap<>();
    private Map<String, Integer> paymentBreakdown = new HashMap<>();
    private int totalOrders = 0;
    private List<String> shippingStatus = new ArrayList<>();

    public void recordOrder(ShoppingCart cart, Payment payment) {
        totalOrders++;
        totalRevenue += payment.getAmountPaid();
        for (CartItem ci : cart.getCartItems()) {
            itemSales.put(ci.item.getItemName(), itemSales.getOrDefault(ci.item.getItemName(), 0) + ci.quantity);
        }
        paymentBreakdown.put(payment.getPaymentMethod(), paymentBreakdown.getOrDefault(payment.getPaymentMethod(), 0) + 1);
        shippingStatus.add("Order for " + cart.getCustomer().getCustomerName() + ": Shipped");
    }

    public void generateReport() {
        System.out.println("\n--- Sales Report ---");
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Orders: " + totalOrders);
        System.out.println("Item Sales:");
        for (String item : itemSales.keySet()) {
            System.out.println("  " + item + ": " + itemSales.get(item));
        }
        System.out.println("Payment Breakdown:");
        for (String method : paymentBreakdown.keySet()) {
            System.out.println("  " + method + ": " + paymentBreakdown.get(method));
        }
        System.out.println("Order Fulfillment:");
        for (String status : shippingStatus) {
            System.out.println("  " + status);
        }
        System.out.println("--------------------\n");
    }
}
