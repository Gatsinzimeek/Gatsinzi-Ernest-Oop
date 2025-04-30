import java.time.LocalDate;
import java.util.Scanner;

public class ComprehensivePolicy extends InsurancePolicy {
    public ComprehensivePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, double premiumAmount, LocalDate start, LocalDate end) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, start, end);
    }

    @Override
    public void calculatePremium() {
        int age = LocalDate.now().getYear() - vehicle.getVehicleYear();
        if (age < 5) {
            premiumAmount = coverageAmount * 0.03;
        } else if (age < 10) {
            premiumAmount = coverageAmount * 0.05;
        } else {
            premiumAmount = coverageAmount * 0.08;
        }
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
        if (!vehicle.validateVehicle()) {
            System.out.println("Vehicle validation failed.");
            return false;
        }
        claim.claimStatus = "Approved";
        System.out.println("Claim approved for amount: " + claim.getClaimAmount());
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("--- Comprehensive Policy Report ---");
        System.out.println("Policy ID: " + policyId);
        System.out.println("Holder: " + policyHolder);
        System.out.println("Vehicle: " + vehicle);
        System.out.println("Coverage: " + coverageAmount);
        System.out.println("Premium: " + premiumAmount);
        System.out.println("Start: " + policyStartDate + " End: " + policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        if (!vehicle.validateVehicle()) {
            System.out.println("Vehicle details invalid for comprehensive policy.");
            return false;
        }
        int year = vehicle.getVehicleYear();
        int thisYear = LocalDate.now().getYear();
        if (year < thisYear - 20) {
            System.out.println("Vehicle too old for comprehensive coverage.");
            return false;
        }
        return true;
    }

    public static ComprehensivePolicy inputPolicy(Scanner scanner) {
        System.out.println("--- Comprehensive Policy Input ---");
        int pidInt;
        while (true) {
            System.out.print("Policy ID (integer): ");
            String pidStr = scanner.nextLine().trim();
            try {
                pidInt = Integer.parseInt(pidStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Policy ID. Please enter an integer value.");
            }
        }
        String pid = String.valueOf(pidInt);
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
        ComprehensivePolicy cp = new ComprehensivePolicy(pid, v, p, coverage, 0, start, end);
        cp.calculatePremium();
        return cp;
    }
}
