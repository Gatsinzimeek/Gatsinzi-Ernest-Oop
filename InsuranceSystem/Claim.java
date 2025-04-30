import java.time.LocalDate;
import java.util.Scanner;

public class Claim {
    private String claimId;
    private double claimAmount;
    private LocalDate claimDate;
    public String claimStatus; // Pending, Approved, Rejected

    public Claim(String claimId, double claimAmount, LocalDate claimDate, String claimStatus) {
        this.claimId = claimId;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
    }

    public static Claim inputClaim(Scanner scanner, double maxAmount) {
        String id, status;
        double amount;
        LocalDate date = null;
        while (true) {
            System.out.print("Claim ID: ");
            id = scanner.nextLine().trim();
            if (!id.matches("[A-Za-z0-9-_]+")) {
                System.out.println("Invalid Claim ID. Only letters, numbers, hyphens, and underscores allowed.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Claim Amount: ");
            String amtStr = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(amtStr);
                if (amount <= 0) {
                    System.out.println("Claim amount must be positive.");
                    continue;
                }
                if (amount > maxAmount) {
                    System.out.println("Claim amount cannot exceed policy coverage: " + maxAmount);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
        while (true) {
            System.out.print("Claim Date (yyyy-mm-dd): ");
            String dateStr = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(dateStr);
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Claim date cannot be in the future.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
        while (true) {
            System.out.print("Claim Status (Pending/Approved/Rejected): ");
            status = scanner.nextLine().trim();
            if (!status.matches("(?i)Pending|Approved|Rejected")) {
                System.out.println("Status must be one of: Pending, Approved, Rejected.");
                continue;
            }
            break;
        }
        return new Claim(id, amount, date, status);
    }

    public String getClaimId() { return claimId; }
    public double getClaimAmount() { return claimAmount; }
    public LocalDate getClaimDate() { return claimDate; }
    public String getClaimStatus() { return claimStatus; }

    public boolean validateClaim(double maxAmount) {
        if (claimId == null || !claimId.matches("[A-Za-z0-9-_]+")) return false;
        if (claimAmount <= 0 || claimAmount > maxAmount) return false;
        if (claimDate == null || claimDate.isAfter(LocalDate.now())) return false;
        if (claimStatus == null || !claimStatus.matches("(?i)Pending|Approved|Rejected")) return false;
        return true;
    }

    public String toString() {
        return "Claim [ID: " + claimId + ", Amount: " + claimAmount + ", Date: " + claimDate + ", Status: " + claimStatus + "]";
    }
}
