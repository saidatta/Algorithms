package Int.fb;

import java.util.Random;

//
public class MaxIndexMaxNumArray {
    public static int randomIndexOfMax(int[] nums) {
        // Step 1: Find the max value
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        // Step 2: Count occurrences of max value
        int count = 0;
        for (int num : nums) {
            if (num == max) {
                count++;
            }
        }

        // Step 3: Select a random occurrence of max
        Random rand = new Random();
        int randomOccurrence = rand.nextInt(count) + 1;

        // Step 4: Find the index of the randomly selected occurrence
        int occurrenceCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                occurrenceCount++;
                if (occurrenceCount == randomOccurrence) {
                    return i;
                }
            }
        }

        // This will not be reached
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 6, 4, 5, 6};
        int result = randomIndexOfMax(nums);
        System.out.println("Random index of max value: " + result);
    }
}
