package leetcode.array.traversal;

// https://leetcode.com/problems/monotonic-array/description
public class MonotonicArray {
    /**
     * Checks if the given array is monotonic.
     *
     * @param nums The input array.
     * @return True if the array is monotonic, false otherwise.
     */
    public static boolean isMonotonic(int[] nums) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                decreasing = false;
            } else if (nums[i] < nums[i - 1]) {
                increasing = false;
            }
        }

        return increasing || decreasing;
    }

    public static void main(String[] args) {
        System.out.println(isMonotonic(new int[]{1, 2, 2, 3})); // true
        System.out.println(isMonotonic(new int[]{6, 5, 4, 4})); // true
        System.out.println(isMonotonic(new int[]{1, 3, 2}));    // false
    }
}
