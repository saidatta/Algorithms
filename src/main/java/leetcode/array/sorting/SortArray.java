package leetcode.array.sorting;

// https://leetcode.com/problems/sort-an-array
public class SortArray {
    // Main function to sort the array
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    // Recursive function to divide the array and merge
    private void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            // Find the middle point to divide the array into two halves
            int middle = left + (right - left) / 2;

            // Call mergeSort for first and second halves
            mergeSort(nums, left, middle);
            mergeSort(nums, middle + 1, right);

            // Merge the sorted halves
            merge(nums, left, middle, right);
        }
    }

    // Function to merge two halves of the array
    private void merge(int[] nums, int left, int middle, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(nums, left, L, 0, n1);
        System.arraycopy(nums, middle + 1, R, 0, n2);

        // Merge the temp arrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                nums[k] = L[i];
                i++;
            } else {
                nums[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            nums[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            nums[k] = R[j];
            j++;
            k++;
        }
    }
}
