import java.time.LocalDate;
import java.util.Scanner;

public class CollisionPolicy extends InsurancePolicy {
    private boolean safeDriverDiscount;
    private boolean safetyCheckPassed;

    public CollisionPolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, double premiumAmount, LocalDate start, LocalDate end, boolean safeDriverDiscount, boolean safetyCheckPassed) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, start, end);
        this.safeDriverDiscount = safeDriverDiscount;
        this.safetyCheckPassed = safetyCheckPassed;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = coverageAmount * 0.06;
        if (safeDriverDiscount) premiumAmount *= 0.85; // 15% discount
    }

    @Override
    public boolean processClaim(Claim claim) {
        if (!validatePolicy()) {
            System.out.println("Policy validation failed. Cannot process claim.");
            return false;
        }
        if (!claim.validateClaim(coverageAmount)) {
            System.out.println("Claim validation failed. Amount exceeds coverage or invalid claim.");
            return false;
        }
        if (!safetyCheckPassed) {
            System.out.println("Vehicle safety check not passed. Claim denied.");
            return false;
        }
        claim.claimStatus = "Approved";
        System.out.println("Claim approved for amount: " + claim.getClaimAmount());
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("--- Collision Policy Report ---");
        System.out.println("Policy ID: " + policyId);
        System.out.println("Holder: " + policyHolder);
        System.out.println("Vehicle: " + vehicle);
        System.out.println("Coverage: " + coverageAmount);
        System.out.println("Premium: " + premiumAmount);
        System.out.println("Safe Driver Discount: " + (safeDriverDiscount ? "Yes" : "No"));
        System.out.println("Safety Check Passed: " + (safetyCheckPassed ? "Yes" : "No"));
        System.out.println("Start: " + policyStartDate + " End: " + policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        if (!vehicle.validateVehicle()) {
            System.out.println("Vehicle details invalid for collision policy.");
            return false;
        }
        if (!safetyCheckPassed) {
            System.out.println("Vehicle safety check required for collision policy.");
            return false;
        }
        return true;
    }

    public static CollisionPolicy inputPolicy(Scanner scanner) {
        System.out.println("--- Collision Policy Input ---");
        System.out.print("Policy ID: ");
        String pid = scanner.nextLine().trim();
        Vehicle v = Vehicle.inputVehicle(scanner);
        Person p = Person.inputPerson(scanner);
        double coverage;
        while (true) {
            System.out.print("Coverage Amount: ");
            String cStr = scanner.nextLine().trim();
            try {
                coverage = Double.parseDouble(cStr);
                if (coverage <= 0) {
                    System.out.println("Coverage must be positive.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid coverage amount.");
            }
        }
        LocalDate start, end;
        while (true) {
            System.out.print("Policy Start Date (yyyy-mm-dd): ");
            String sStr = scanner.nextLine().trim();
            try {
                start = LocalDate.parse(sStr);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format.");
            }
        }
        while (true) {
            System.out.print("Policy End Date (yyyy-mm-dd): ");
            String eStr = scanner.nextLine().trim();
            try {
                end = LocalDate.parse(eStr);
                if (end.isBefore(start)) {
                    System.out.println("End date must be after start date.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format.");
            }
        }
        boolean safeDriver = false, safetyCheck = false;
        while (true) {
            System.out.print("Safe Driver Discount (true/false): ");
            String sdStr = scanner.nextLine().trim().toLowerCase();
            if (sdStr.equals("true") || sdStr.equals("false")) {
                safeDriver = Boolean.parseBoolean(sdStr);
                break;
            } else {
                System.out.println("Enter 'true' or 'false'.");
            }
        }
        while (true) {
            System.out.print("Safety Check Passed (true/false): ");
            String scStr = scanner.nextLine().trim().toLowerCase();
            if (scStr.equals("true") || scStr.equals("false")) {
                safetyCheck = Boolean.parseBoolean(scStr);
                break;
            } else {
                System.out.println("Enter 'true' or 'false'.");
            }
        }
        CollisionPolicy cp = new CollisionPolicy(pid, v, p, coverage, 0, start, end, safeDriver, safetyCheck);
        cp.calculatePremium();
        return cp;
    }
}
