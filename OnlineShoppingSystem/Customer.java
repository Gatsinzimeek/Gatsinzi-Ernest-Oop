public class Customer {
    private String customerId;
    private String customerName;
    private String email;
    private String address;
    private String phone;

    public Customer(String customerId, String customerName, String email, String address, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public boolean validateDetails() {
        return customerId != null && !customerId.isEmpty() &&
               customerName != null && !customerName.isEmpty() &&
               email != null && email.contains("@") &&
               address != null && address.length() > 5 &&
               phone != null && phone.length() >= 7;
    }

    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}
