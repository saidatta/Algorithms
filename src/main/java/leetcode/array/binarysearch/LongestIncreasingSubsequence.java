package leetcode.array.binarysearch;

// https://leetcode.com/problems/longest-increasing-subsequence/description/
public class LongestIncreasingSubsequence {
    /**
     * Determines the length of the longest increasing subsequence in the given array.
     *
     * @param nums The input array of numbers.
     * @return Length of the longest increasing subsequence.
     */
    public int lengthOfLIS(int[] nums) {
        // `tails` is an array storing the smallest tail of all increasing subsequences
        // of length `i + 1`. tails[i] is the value of the last element of the subsequence.
        int[] tails = new int[nums.length];

        // Represents the length of the longest increasing subsequence found so far.
        int size = 0;

        // Iterate through each number in the input.
        for (int num : nums) {
            // Use binary search to find the position of `num` in tails array.
            int left = 0, right = size;
            while (left != right) {
                int mid = (left + right) >>> 1;

                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // Update the tails array with the new number.
            tails[left] = num;

            // If `num` is the largest among all end elements of current LIS,
            // then increment the length of LIS.
            if (left == size) {
                ++size;
            }
        }

        return size;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence solver = new LongestIncreasingSubsequence();
        int[] test0 = {8,9,1,2,3};
        System.out.println(solver.lengthOfLIS(test0));  // Expected output: 4

        // Test cases
        int[] test1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(solver.lengthOfLIS(test1));  // Expected output: 4

        int[] test2 = {0, 1, 0, 3, 2, 3};
        System.out.println(solver.lengthOfLIS(test2));  // Expected output: 4

        int[] test3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println(solver.lengthOfLIS(test3));  // Expected output: 1
    }
}

/**
 * This algorithm uses a dynamic programming approach optimized with binary search. The tails array keeps track
 * of the smallest possible ending elements for all increasing subsequences of different lengths. Whenever a new
 * number is encountered from the input array, binary search is used to find its position in tails, and the value
 * in tails is replaced if necessary.
 *
 * Crucially, if a value in tails is replaced, it will be with a number smaller than the existing one, ensuring
 * the potential for longer subsequences in the future. A replacement in tails doesn't decrease the length of
 * the longest increasing subsequence found so far since the replaced position is not at the end of tails.
 *
 * @param nums The input array of numbers.
 * @return Length of the longest increasing subsequence.
 */
