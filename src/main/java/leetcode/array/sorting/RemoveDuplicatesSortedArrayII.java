package leetcode.array.sorting;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
public class RemoveDuplicatesSortedArrayII {

    /**
     * Removes duplicates such that duplicates are allowed at most twice.
     *
     * @param nums The input integer array.
     * @return The length of the modified array.
     */
    public int removeDuplicates(int[] nums) {
        // If the array is empty, no need to process further.
        if (nums.length == 0) {
            return 0;
        }

        // `writePointer` is the position where we want to write the next element.
        int writePointer = 1;

        // `occurrences` keeps track of the count of any given number.
        int occurrences = 1;

        // Traverse the array starting from the second element.
        for (int readPointer = 1; readPointer < nums.length; readPointer++) {
            // If the current element is the same as the previous one, increment occurrences.
            if (nums[readPointer] == nums[readPointer - 1]) {
                occurrences++;
            } else {
                // If current element is different, reset occurrences.
                occurrences = 1;
            }

            // If occurrences <= 2, write the element at the writePointer position.
            if (occurrences <= 2) {
                nums[writePointer] = nums[readPointer];
                writePointer++;
            }
        }

        // Return the position of the last written pointer which is the length of modified array.
        return writePointer;
    }
}
