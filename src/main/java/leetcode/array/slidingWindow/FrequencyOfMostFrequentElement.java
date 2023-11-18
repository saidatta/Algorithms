package leetcode.array.slidingWindow;

import java.util.Arrays;

// https://leetcode.com/problems/frequency-of-the-most-frequent-element
// https://www.youtube.com/watch?v=vgBrQ0NM5vE
import java.util.Arrays;

public class FrequencyOfMostFrequentElement {

    /**
     * Finds the maximum frequency of an element after performing at most k increments.
     *
     * @param nums The input array.
     * @param k    The maximum number of increments allowed.
     * @return The maximum frequency of any element after increments.
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);  // Sort the array to enable sliding window technique
        int left = 0;       // Initialize the left pointer of the window
        long totalIncrement = 0;  // Total increments used within the current window

        for (int right = 0; right < nums.length; right++) {
            // Current element at the right end of the window
            int currentElement = nums[right];
            totalIncrement += currentElement;

            // Calculate the current window size
            long windowSize = right - left + 1;
            // Total needed to make all elements equal to currentElement
            long totalNeeded = windowSize * currentElement;

            // If total needed exceeds k + total increments, move the left pointer
            if (totalNeeded > k + totalIncrement) {
                totalIncrement -= nums[left];
                left++; // Contract the window from the left
            }
        }

        // The maximum frequency is the size of the largest window found
        return nums.length - left;
    }

    public static void main(String[] args) {
        FrequencyOfMostFrequentElement solution = new FrequencyOfMostFrequentElement();
        System.out.println(solution.maxFrequency(new int[]{1,2,4}, 5)); // Output: 3
        System.out.println(solution.maxFrequency(new int[]{1,4,8,13}, 5)); // Output: 2
        System.out.println(solution.maxFrequency(new int[]{3,9,6}, 2)); // Output: 1
    }
}

