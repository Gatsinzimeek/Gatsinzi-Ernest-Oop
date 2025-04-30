import java.time.LocalDate;
import java.util.*;

public class Payment {
    private String paymentId;
    private String paymentMethod;
    private double amountPaid;
    private LocalDate transactionDate;
    private static final Set<String> VALID_METHODS = new HashSet<>(Arrays.asList("CreditCard", "PayPal"));

    public Payment(String paymentId, String paymentMethod, double amountPaid, LocalDate transactionDate) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.transactionDate = transactionDate;
    }

    public boolean validatePayment(double expectedAmount) {
        if (!VALID_METHODS.contains(paymentMethod)) {
            System.out.println("Invalid payment method.");
            return false;
        }
        if (amountPaid != expectedAmount) {
            System.out.println("Amount paid does not match total price.");
            return false;
        }
        return true;
    }

    public String getPaymentMethod() { return paymentMethod; }
    public double getAmountPaid() { return amountPaid; }
}
