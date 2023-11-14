package leetcode.dp.array;

// https://leetcode.com/problems/the-number-of-beautiful-subsets/description/
// https://leetcode.com/problems/the-number-of-beautiful-subsets/solutions/3363862/c-java-python-evolve-brute-force-to-dp-explained-7-approaches/
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class BeautifulSubsets {
    /**
     * Counts the number of beautiful subsets in the array.
     *
     * @param nums The input array of integers.
     * @param k    The difference value to check for in subsets.
     * @return The count of beautiful subsets.
     */
    public static int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array to handle duplicates effectively
        Map<Integer, Integer> seen = new HashMap<>(); // Tracks the count of each element in the current path
        int n = nums.length; // The length of the array
        return dfs(nums, k, seen, n, 0) - 1; // Start DFS from the first element; subtract 1 to exclude the empty subset
    }

    /**
     * Performs a depth-first search to count beautiful subsets.
     *
     * @param nums  The array of integers.
     * @param k     The difference value to check for in subsets.
     * @param seen  Map to keep track of elements seen in the current path.
     * @param n     The length of the array.
     * @param index The current index in the array.
     * @return The count of beautiful subsets including and beyond the current index.
     */
    private static int dfs(int[] nums, int k, Map<Integer, Integer> seen, int n, int index) {
        if (index == n) {
            // Base case: reached the end of the array
            return 1;
        }
        // Count of beautiful subsets
        int count = 0;
        // Check if adding the current element violates the beautiful subset condition
        if (seen.getOrDefault(nums[index] - k, 0) == 0) {
            // doesnt exist a number that causes the subtraction to be k. Include the current element
            seen.put(nums[index], seen.getOrDefault(nums[index], 0) + 1);
            // Count subsets including the current element
            count += dfs(nums, k, seen, n, index + 1);
            // Backtrack: remove the current element
            seen.put(nums[index], seen.get(nums[index]) - 1);
        }
        // Count subsets excluding the current element
        count += dfs(nums, k, seen, n, index + 1);
        return count;
    }

    public static void main(String[] args) {
        System.out.println(beautifulSubsets(new int[]{2, 4, 6}, 2)); // Output: 4
        System.out.println(beautifulSubsets(new int[]{1}, 1)); // Output: 1
        System.out.println(beautifulSubsets(new int[]{2, -1, 2}, 3)); // Output: 3
    }
}
