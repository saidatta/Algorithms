package leetcode.design.product;

// https://leetcode.com/problems/design-parking-system/description/
public class ParkingSystem {
    private final int[] slots;

    public ParkingSystem(int big, int medium, int small) {
        // Initialize slots array with an extra slot for ease of indexing (1-based index for car types)
        this.slots = new int[]{0, big, medium, small};
    }

    public boolean addCar(int carType) {
        // Check if there is an available slot for the car type
        if (slots[carType] > 0) {
            // Decrease the count of available slots for that car type and return true
            slots[carType]--;
            return true;
        }
        // If no slots are available, return false
        return false;
    }

    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        System.out.println(parkingSystem.addCar(1)); // true
        System.out.println(parkingSystem.addCar(2)); // true
        System.out.println(parkingSystem.addCar(3)); // false
        System.out.println(parkingSystem.addCar(1)); // false
    }
}

