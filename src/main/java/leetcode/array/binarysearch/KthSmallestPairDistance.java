package leetcode.array.binarysearch;

import java.util.Arrays;

// https://leetcode.com/problems/find-k-th-smallest-pair-distance/
// https://www.youtube.com/watch?v=BZpF_o60STI
public class KthSmallestPairDistance {

    /**
     * Finds the k-th smallest pair distance in the array.
     *
     * @param nums The input array.
     * @param k    The k-th smallest distance to find.
     * @return The k-th smallest pair distance.
     */
    public int smallestDistancePair(int[] nums, int k) {
        // Sort the array to make pair distance calculations easier
        Arrays.sort(nums);
        int n = nums.length;
        // The smallest distance can be 0 and the largest is the difference between max and min elements
        int low = 0;
        int high = nums[n - 1] - nums[0];

        // Binary search for the correct distance
        while (low < high) {
            int mid = (low + high) / 2;
            int pairCount = 0, windowStart = 0;

            // Use a sliding window to count pairs with distance <= mid
            for (int windowEnd = 0; windowEnd < n; windowEnd++) {
                // Expand the window while the difference is larger than mid
                while (nums[windowEnd] - nums[windowStart] > mid) {
                    windowStart++;
                }
                // Count the number of pairs within the current window
                pairCount += windowEnd - windowStart;
            }

            // Adjust the search range based on the number of pairs found
            if (pairCount < k) {
                low = mid + 1; // Search in the larger distances
            } else {
                high = mid; // Search in the smaller distances
            }
        }

        // The low pointer is the k-th smallest distance
        return low;
    }

    public static void main(String[] args) {
        KthSmallestPairDistance solution = new KthSmallestPairDistance();
        System.out.println(solution.smallestDistancePair(new int[]{1, 3, 1}, 1)); // Output: 0
        System.out.println(solution.smallestDistancePair(new int[]{1, 1, 1}, 2)); // Output: 0
        System.out.println(solution.smallestDistancePair(new int[]{1, 6, 1}, 3)); // Output: 5
    }
}


//    To solve the problem of finding the k-th smallest pair distance in an array, we can use a combination of sorting,
//    binary search, and a sliding window approach. This method is efficient for large datasets.
//
//        Here's the algorithm:
//
//        1. Sort the Array: First, sort the array to make the distances between pairs easier to handle.
//        2. Binary Search for the Right Distance: Use binary search to find the k-th smallest distance. The search space
//        is between 0 (the smallest possible distance) and the difference between the maximum and minimum elements in
//        the array (the largest possible distance).
//        3. Count Pairs with Distance Less Than or Equal to Mid: During each step of the binary search, use a sliding
//        window approach to count how many pairs have a distance less than or equal to the current middle value of the
//        binary search.
//        4. Adjust Binary Search According to Pair Count: If the count of pairs is less than k, narrow the search to
//        larger distances. If the count is greater or equal to k, narrow the search to smaller distances.