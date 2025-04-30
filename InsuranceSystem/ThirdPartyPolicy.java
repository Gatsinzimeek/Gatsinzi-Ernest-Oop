import java.time.LocalDate;
import java.util.Scanner;

public class ThirdPartyPolicy extends InsurancePolicy {
    private String additionalCoverage; // e.g. "Fire", "Flood", "None"

    public ThirdPartyPolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, double premiumAmount, LocalDate start, LocalDate end, String additionalCoverage) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, start, end);
        this.additionalCoverage = additionalCoverage;
    }

    @Override
    public void calculatePremium() {
        double base = 0.02; // base rate
        if (vehicle.getVehicleType().equalsIgnoreCase("Truck")) base = 0.03;
        if (vehicle.getVehicleType().equalsIgnoreCase("Motorcycle")) base = 0.04;
        // Engine capacity simulation: if model contains "V6" or "V8" increase rate
        if (vehicle.getVehicleModel().toUpperCase().contains("V6") || vehicle.getVehicleModel().toUpperCase().contains("V8")) base += 0.01;
        premiumAmount = coverageAmount * base;
        if (!additionalCoverage.equalsIgnoreCase("None")) premiumAmount += 500;
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
        claim.claimStatus = "Approved";
        System.out.println("Claim approved for amount: " + claim.getClaimAmount());
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("--- Third Party Policy Report ---");
        System.out.println("Policy ID: " + policyId);
        System.out.println("Holder: " + policyHolder);
        System.out.println("Vehicle: " + vehicle);
        System.out.println("Coverage: " + coverageAmount);
        System.out.println("Premium: " + premiumAmount);
        System.out.println("Additional Coverage: " + additionalCoverage);
        System.out.println("Start: " + policyStartDate + " End: " + policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        if (!vehicle.validateVehicle()) {
            System.out.println("Vehicle details invalid for third party policy.");
            return false;
        }
        if (coverageAmount < 1000) {
            System.out.println("Minimum coverage for third party policy is 1000.");
            return false;
        }
        return true;
    }

    public static ThirdPartyPolicy inputPolicy(Scanner scanner) {
        System.out.println("--- Third Party Policy Input ---");
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
                if (coverage < 1000) {
                    System.out.println("Coverage must be at least 1000.");
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
        String acov;
        while (true) {
            System.out.print("Additional Coverage (Fire/Flood/None): ");
            acov = scanner.nextLine().trim();
            if (!acov.matches("(?i)Fire|Flood|None")) {
                System.out.println("Must be Fire, Flood, or None.");
                continue;
            }
            break;
        }
        ThirdPartyPolicy tpp = new ThirdPartyPolicy(pid, v, p, coverage, 0, start, end, acov);
        tpp.calculatePremium();
        return tpp;
    }
}
