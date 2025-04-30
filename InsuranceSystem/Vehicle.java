import java.util.Scanner;

public class Vehicle {
    private String vehicleId; // Keep as String for compatibility, but enforce integer value
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private String vehicleType; // e.g. Car, Truck, Motorcycle, Commercial

    public Vehicle(String vehicleId, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleType = vehicleType;
    }

    public static Vehicle inputVehicle(Scanner scanner) {
        String id, make, model, type;
        int year;
        while (true) {
            System.out.print("Vehicle ID (integer): ");
            String idStr = scanner.nextLine().trim();
            try {
                Integer.parseInt(idStr);
                id = idStr;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Vehicle ID. Please enter an integer value.");
            }
        }
        while (true) {
            System.out.print("Make: ");
            make = scanner.nextLine().trim();
            if (make.isEmpty()) {
                System.out.println("Make cannot be empty.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Model: ");
            model = scanner.nextLine().trim();
            if (model.isEmpty()) {
                System.out.println("Model cannot be empty.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Year: ");
            String yearStr = scanner.nextLine().trim();
            try {
                year = Integer.parseInt(yearStr);
                int thisYear = java.time.LocalDate.now().getYear();
                if (year < 1980 || year > thisYear) {
                    System.out.println("Year must be between 1980 and " + thisYear + ".");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year. Please enter a valid number.");
            }
        }
        while (true) {
            System.out.print("Type (Car/Truck/Motorcycle/Commercial): ");
            type = scanner.nextLine().trim();
            if (!type.matches("(?i)Car|Truck|Motorcycle|Commercial")) {
                System.out.println("Type must be one of: Car, Truck, Motorcycle, Commercial.");
                continue;
            }
            break;
        }
        return new Vehicle(id, make, model, year, type);
    }

    public String getVehicleId() { return vehicleId; }
    public String getVehicleMake() { return vehicleMake; }
    public String getVehicleModel() { return vehicleModel; }
    public int getVehicleYear() { return vehicleYear; }
    public String getVehicleType() { return vehicleType; }

    public void updateVehicle(Scanner scanner) {
        System.out.println("--- Update Vehicle Details ---");
        Vehicle updated = inputVehicle(scanner);
        this.vehicleId = updated.vehicleId;
        this.vehicleMake = updated.vehicleMake;
        this.vehicleModel = updated.vehicleModel;
        this.vehicleYear = updated.vehicleYear;
        this.vehicleType = updated.vehicleType;
    }

    public boolean validateVehicle() {
        int thisYear = java.time.LocalDate.now().getYear();
        if (vehicleId == null || !vehicleId.matches("\\d+")) return false;
        if (vehicleMake == null || vehicleMake.isEmpty()) return false;
        if (vehicleModel == null || vehicleModel.isEmpty()) return false;
        if (vehicleYear < 1980 || vehicleYear > thisYear) return false;
        if (vehicleType == null || !vehicleType.matches("(?i)Car|Truck|Motorcycle|Commercial")) return false;
        return true;
    }

    public String toString() {
        return vehicleYear + " " + vehicleMake + " " + vehicleModel + " (" + vehicleType + ") [" + vehicleId + "]";
    }
}
