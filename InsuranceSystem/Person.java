import java.time.LocalDate;
import java.util.Scanner;

public class Person {
    private String personId;
    private String fullName;
    private LocalDate dob;
    private String email;
    private String phone;

    public Person(String personId, String fullName, LocalDate dob, String email, String phone) {
        this.personId = personId;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public static Person inputPerson(Scanner scanner) {
        String id, name, email, phone;
        LocalDate dob = null;
        while (true) {
            System.out.print("Person ID: ");
            id = scanner.nextLine().trim();
            if (!id.matches("[A-Za-z0-9-_]+")) {
                System.out.println("Invalid Person ID. Only letters, numbers, hyphens, and underscores allowed.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Full Name: ");
            name = scanner.nextLine().trim();
            if (!name.matches("[A-Za-z ]+")) {
                System.out.println("Name must contain only letters and spaces.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Date of Birth (yyyy-mm-dd): ");
            String dobStr = scanner.nextLine().trim();
            try {
                dob = LocalDate.parse(dobStr);
                if (dob.isAfter(LocalDate.now().minusYears(18))) {
                    System.out.println("Policyholder must be at least 18 years old.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            }
        }
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("Invalid email format.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Phone (10-15 digits): ");
            phone = scanner.nextLine().trim();
            if (!phone.matches("\\d{10,15}")) {
                System.out.println("Phone number must be 10-15 digits.");
                continue;
            }
            break;
        }
        return new Person(id, name, dob, email, phone);
    }

    public String getPersonId() { return personId; }
    public String getFullName() { return fullName; }
    public LocalDate getDob() { return dob; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public boolean validatePerson() {
        if (personId == null || !personId.matches("[A-Za-z0-9-_]+")) return false;
        if (fullName == null || !fullName.matches("[A-Za-z ]+")) return false;
        if (dob == null || dob.isAfter(LocalDate.now().minusYears(18))) return false;
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) return false;
        if (phone == null || !phone.matches("\\d{10,15}")) return false;
        return true;
    }

    public String toString() {
        return fullName + " (" + personId + ", DOB: " + dob + ", Email: " + email + ", Phone: " + phone + ")";
    }
}
