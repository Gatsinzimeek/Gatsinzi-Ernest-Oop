import java.time.LocalDate;

public abstract class InsurancePolicy {
    protected String policyId;
    protected Vehicle vehicle;
    protected Person policyHolder;
    protected double coverageAmount;
    protected double premiumAmount;
    protected LocalDate policyStartDate;
    protected LocalDate policyEndDate;

    public InsurancePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, double premiumAmount, LocalDate policyStartDate, LocalDate policyEndDate) {
        this.policyId = policyId;
        this.vehicle = vehicle;
        this.policyHolder = policyHolder;
        this.coverageAmount = coverageAmount;
        this.premiumAmount = premiumAmount;
        this.policyStartDate = policyStartDate;
        this.policyEndDate = policyEndDate;
    }

    public abstract void calculatePremium();
    public abstract boolean processClaim(Claim claim);
    public abstract void generatePolicyReport();
    public abstract boolean validatePolicy();

    public String getPolicyId() { return policyId; }
    public Vehicle getVehicle() { return vehicle; }
    public Person getPolicyHolder() { return policyHolder; }
    public double getCoverageAmount() { return coverageAmount; }
    public double getPremiumAmount() { return premiumAmount; }
    public LocalDate getPolicyStartDate() { return policyStartDate; }
    public LocalDate getPolicyEndDate() { return policyEndDate; }
}
