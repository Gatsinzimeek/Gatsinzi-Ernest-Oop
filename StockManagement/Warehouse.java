public class Warehouse {
    private final String warehouseId;
    private final String location;
    private final int capacity;
    private final String managerName;

    public Warehouse(String warehouseId, String location, int capacity, String managerName) {
        this.warehouseId = warehouseId;
        this.location = location;
        this.capacity = capacity;
        this.managerName = managerName;
    }

    public void trackStockMovement(String itemName, int quantity, String movementType) {
        System.out.printf("%s: %d units of %s %s warehouse %s\n", movementType, quantity, itemName, movementType.equals("IN") ? "added to" : "removed from", warehouseId);
    }

    public boolean validateWarehouse() {
        boolean valid = true;
        if (location == null || location.isEmpty()) {
            System.out.println("Warehouse location cannot be empty.");
            valid = false;
        }
        if (capacity <= 0) {
            System.out.println("Warehouse capacity must be above zero.");
            valid = false;
        }
        if (managerName == null || managerName.isEmpty()) {
            System.out.println("Manager name cannot be empty.");
            valid = false;
        }
        return valid;
    }
    // Getters and setters (if needed)
}
