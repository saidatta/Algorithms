package leetcode.array.schedules;

// https://leetcode.com/problems/car-pooling/description/
// bucket srot
public class CarpPooling {
    private static final int NUM_PASSENGERS_IDX = 0;
    private static final int START_IDX = 1;
    private static final int DROPOFF_IDX = 2;
    public boolean carPooling(int[][] trips, int capacity) {
        int[] passengerChanges = new int[1001]; // Assuming locations are up to 1000

        // Record the changes in the number of passengers at each location
        for (int[] trip : trips) {
            passengerChanges[trip[START_IDX]] += trip[NUM_PASSENGERS_IDX]; // Pickup
            passengerChanges[trip[DROPOFF_IDX]] -= trip[NUM_PASSENGERS_IDX]; // Drop-off
        }

        // Simulate the trips
        int currentPassengers = 0;
        for (int passengers : passengerChanges) {
            currentPassengers += passengers;
            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        CarpPooling solution = new CarpPooling();
        int[][] trips1 = {{2, 1, 5}, {3, 3, 7}};
        int capacity1 = 4;
        System.out.println(solution.carPooling(trips1, capacity1));  // Output: false

        int[][] trips2 = {{2, 1, 5}, {3, 3, 7}};
        int capacity2 = 5;
        System.out.println(solution.carPooling(trips2, capacity2));  // Output: true
    }
}
