import java.time.LocalDate;
import java.util.Scanner;

public class RoadsideAssistancePolicy extends InsurancePolicy {
    private boolean isCommercial;
    private boolean registrationVerified;
    private boolean inspectionVerified;

    public RoadsideAssistancePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, double premiumAmount, LocalDate start, LocalDate end, boolean isCommercial, boolean registrationVerified, boolean inspectionVerified) {
        super(policyId, vehicle, policyHolder, coverageAmount, premiumAmount, start, end);
        this.isCommercial = isCommercial;
        this.registrationVerified = registrationVerified;
        this.inspectionVerified = inspectionVerified;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = coverageAmount * (isCommercial ? 0.07 : 0.04);
        if (!registrationVerified || !inspectionVerified) premiumAmount += 100;
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
        if (!registrationVerified || !inspectionVerified) {
            System.out.println("Vehicle registration and inspection must be verified. Claim denied.");
            return false;
        }
        claim.claimStatus = "Approved";
        System.out.println("Claim approved for amount: " + claim.getClaimAmount());
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("--- Roadside Assistance Policy Report ---");
        System.out.println("Policy ID: " + policyId);
        System.out.println("Holder: " + policyHolder);
        System.out.println("Vehicle: " + vehicle);
        System.out.println("Coverage: " + coverageAmount);
        System.out.println("Premium: " + premiumAmount);
        System.out.println("Commercial: " + (isCommercial ? "Yes" : "No"));
        System.out.println("Registration Verified: " + (registrationVerified ? "Yes" : "No"));
        System.out.println("Inspection Verified: " + (inspectionVerified ? "Yes" : "No"));
        System.out.println("Start: " + policyStartDate + " End: " + policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        if (!vehicle.validateVehicle()) {
            System.out.println("Vehicle details invalid for roadside assistance policy.");
            return false;
        }
        if (!registrationVerified || !inspectionVerified) {
            System.out.println("Vehicle registration and inspection must be verified.");
            return false;
        }
        return true;
    }

    public static RoadsideAssistancePolicy inputPolicy(Scanner scanner) {
        System.out.println("--- Roadside Assistance Policy Input ---");
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
        boolean isComm = false, reg = false, insp = false;
        while (true) {
            System.out.print("Is Commercial Vehicle? (true/false): ");
            String commStr = scanner.nextLine().trim().toLowerCase();
            if (commStr.equals("true") || commStr.equals("false")) {
                isComm = Boolean.parseBoolean(commStr);
                break;
            } else {
                System.out.println("Enter 'true' or 'false'.");
            }
        }
        while (true) {
            System.out.print("Registration Verified? (true/false): ");
            String regStr = scanner.nextLine().trim().toLowerCase();
            if (regStr.equals("true") || regStr.equals("false")) {
                reg = Boolean.parseBoolean(regStr);
                break;
            } else {
                System.out.println("Enter 'true' or 'false'.");
            }
        }
        while (true) {
            System.out.print("Inspection Verified? (true/false): ");
            String inspStr = scanner.nextLine().trim().toLowerCase();
            if (inspStr.equals("true") || inspStr.equals("false")) {
                insp = Boolean.parseBoolean(inspStr);
                break;
            } else {
                System.out.println("Enter 'true' or 'false'.");
            }
        }
        RoadsideAssistancePolicy rp = new RoadsideAssistancePolicy(pid, v, p, coverage, 0, start, end, isComm, reg, insp);
        rp.calculatePremium();
        return rp;
    }
}
