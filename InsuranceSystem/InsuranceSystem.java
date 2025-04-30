import java.util.*;
import java.time.LocalDate;

public class InsuranceSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<InsurancePolicy> policies = new ArrayList<>();
    private static final List<Claim> claims = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Advanced Motor Vehicle Insurance System ---");
            System.out.println("1. Add Comprehensive Policy");
            System.out.println("2. Add Third Party Policy");
            System.out.println("3. Add Collision Policy");
            System.out.println("4. Add Liability Policy");
            System.out.println("5. Add Roadside Assistance Policy");
            System.out.println("6. File a Claim");
            System.out.println("7. Generate Reports");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": policies.add(ComprehensivePolicy.inputPolicy(scanner)); break;
                case "2": policies.add(ThirdPartyPolicy.inputPolicy(scanner)); break;
                case "3": policies.add(CollisionPolicy.inputPolicy(scanner)); break;
                case "4": policies.add(LiabilityPolicy.inputPolicy(scanner)); break;
                case "5": policies.add(RoadsideAssistancePolicy.inputPolicy(scanner)); break;
                case "6": fileClaim(); break;
                case "7": generateReports(); break;
                case "8": System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void fileClaim() {
        if (policies.isEmpty()) {
            System.out.println("No policies available. Add a policy first.");
            return;
        }
        System.out.print("Enter Policy ID for claim: ");
        String pid = scanner.nextLine().trim();
        InsurancePolicy policy = null;
        for (InsurancePolicy p : policies) {
            if (p.getPolicyId().equals(pid)) { policy = p; break; }
        }
        if (policy == null) {
            System.out.println("Policy ID not found.");
            return;
        }
        Claim claim = Claim.inputClaim(scanner, policy.getCoverageAmount());
        boolean approved = policy.processClaim(claim);
        claims.add(claim);
        System.out.println("Claim status: " + claim.getClaimStatus());
    }

    private static void generateReports() {
        System.out.println("\n--- Policy Reports ---");
        double totalPremiums = 0, totalClaims = 0;
        Map<String, Integer> typeBreakdown = new HashMap<>();
        for (InsurancePolicy p : policies) {
            p.generatePolicyReport();
            totalPremiums += p.getPremiumAmount();
            String type = p.getClass().getSimpleName();
            typeBreakdown.put(type, typeBreakdown.getOrDefault(type, 0) + 1);
        }
        System.out.println("\nTotal Premiums Collected: " + totalPremiums);
        System.out.println("\n--- Claims Processed ---");
        for (Claim c : claims) {
            System.out.println(c);
            totalClaims += c.getClaimAmount();
        }
        System.out.println("Total Claims Amount: " + totalClaims);
        System.out.println("\nCoverage Breakdown by Policy Type:");
        for (Map.Entry<String, Integer> entry : typeBreakdown.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
