public class Supplier {
    private final String supplierId;
    private final String companyName;
    private final String contactPerson;
    private final String phone;
    private final String email;

    public Supplier(String supplierId, String companyName, String contactPerson, String phone, String email) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
    }

    public boolean validateSupplier() {
        boolean valid = true;
        if (companyName == null || companyName.isEmpty()) {
            System.out.println("Company name cannot be empty.");
            valid = false;
        }
        if (contactPerson == null || contactPerson.isEmpty()) {
            System.out.println("Contact person cannot be empty.");
            valid = false;
        }
        if (phone == null || !phone.matches("\\d{10,15}")) {
            System.out.println("Phone number must be 10-15 digits.");
            valid = false;
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.println("Invalid email format.");
            valid = false;
        }
        return valid;
    }
    // Getters and setters (if needed)
}
