import java.time.LocalDate;
import java.util.Scanner;

public class LiabilityPolicy extends InsurancePolicy {
    private boolean medicalCheckupPassed;
    private boolean extendedDisabilityCoverage;

    public LiabilityPolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, double premiumAmount, LocalDate start, LocalDate end, boolean medicalCheckupPassed, boolean extendedDisabilityCoverage) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, start, end);
        this.medicalCheckupPassed = medicalCheckupPassed;
        this.extendedDisabilityCoverage = extendedDisabilityCoverage;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = coverageAmount * 0.025;
        if (extendedDisabilityCoverage) premiumAmount += 300;
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
        if (!medicalCheckupPassed) {
            System.out.println("Medical checkup not passed. Claim denied.");
            return false;
        }
        claim.claimStatus = "Approved";
        System.out.println("Claim approved for amount: " + claim.getClaimAmount());
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("--- Liability Policy Report ---");
        System.out.println("Policy ID: " + policyId);
        System.out.println("Holder: " + policyHolder);
        System.out.println("Vehicle: " + vehicle);
        System.out.println("Coverage: " + coverageAmount);
        System.out.println("Premium: " + premiumAmount);
        System.out.println("Medical Checkup Passed: " + (medicalCheckupPassed ? "Yes" : "No"));
        System.out.println("Extended Disability Coverage: " + (extendedDisabilityCoverage ? "Yes" : "No"));
        System.out.println("Start: " + policyStartDate + " End: " + policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        if (!vehicle.validateVehicle()) {
            System.out.println("Vehicle details invalid for liability policy.");
            return false;
        }
        if (!medicalCheckupPassed) {
            System.out.println("Medical checkup required for liability policy.");
            return false;
        }
        return true;
    }

    public static LiabilityPolicy inputPolicy(Scanner scanner) {
        System.out.println("--- Liability Policy Input ---");
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
        boolean medCheck = false, extDis = false;
        while (true) {
            System.out.print("Medical Checkup Passed (true/false): ");
            String mcStr = scanner.nextLine().trim().toLowerCase();
            if (mcStr.equals("true") || mcStr.equals("false")) {
                medCheck = Boolean.parseBoolean(mcStr);
                break;
            } else {
                System.out.println("Enter 'true' or 'false'.");
            }
        }
        while (true) {
            System.out.print("Extended Disability Coverage (true/false): ");
            String edStr = scanner.nextLine().trim().toLowerCase();
            if (edStr.equals("true") || edStr.equals("false")) {
                extDis = Boolean.parseBoolean(edStr);
                break;
            } else {
                System.out.println("Enter 'true' or 'false'.");
            }
        }
        LiabilityPolicy lp = new LiabilityPolicy(pid, v, p, coverage, 0, start, end, medCheck, extDis);
        lp.calculatePremium();
        return lp;
    }
}
