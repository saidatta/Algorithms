package leetcode.array.binarysearch;

// https://www.youtube.com/watch?v=ER_oLmdc-nw
// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
public class CapacityShipsWithinDays {

    /**
     * This function finds the minimum capacity needed to ship all packages within D days.
     *
     * @param weights Array of package weights.
     * @param days The target number of days to ship all packages.
     * @return The minimum capacity required to achieve the goal.
     */
    public int shipWithinDays(int[] weights, int days) {
        int totalLoad = 0, maxWeight = 0;
        // Calculate the total weight of all packages and find the package with the maximum weight.
        for (int weight : weights) {
            totalLoad += weight;
            maxWeight = Math.max(maxWeight, weight);
        }

        // Set the left boundary to the heaviest package's weight and the right boundary to the total load.
        // This ensures the ship's capacity is at least able to carry the heaviest package and at most all packages at once.
        int left = maxWeight, right = totalLoad;

        // Perform a binary search between the left and right boundaries.
        while (left < right) {
            int mid = (left + right) >>> 1;
            // If the mid-capacity is feasible, search the lower half.
            if (isCapacityFeasible(weights, mid, days)) {
                right = mid;
            } else {
                // If not, search the upper half.
                left = mid + 1;
            }
        }
        // The left boundary will converge to the minimum capacity needed.
        return left;
    }

    /**
     * This function checks if it's possible to ship all packages within the given number of days
     * with the provided capacity.
     *
     * @param weights Array of package weights.
     * @param capacity The weight capacity of the ship.
     * @param days The target number of days to ship all packages.
     * @return True if it's possible to ship within the given days, false otherwise.
     */
    private boolean isCapacityFeasible(int[] weights, int capacity, int days) {
        int daysNeeded = 1, currentLoad = 0;
        for (int weight : weights) {
            currentLoad += weight;
            // If adding the current package exceeds capacity, increment the days needed
            // and reset the current load to the weight of the current package.
            if (currentLoad > capacity) {
                daysNeeded++;
                currentLoad = weight;
            }
        }

        // If the required days are less than or equal to the given days,
        // the capacity is feasible.
        return daysNeeded <= days;
    }
}
