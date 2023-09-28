package leetcode.array.binarysearch;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
/**
 * Solution for the problem: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 * This class finds the kth smallest element in a sorted matrix.
 */
public class KthSmallestSortedMatrix {

    /**
     * Returns the kth smallest element in the matrix.
     *
     * @param matrix The sorted input matrix.
     * @param k The rank of the desired element.
     * @return The kth smallest element.
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // Initialize the search range with the smallest and largest elements of the matrix.
        int low = matrix[0][0], high = matrix[n - 1][n - 1];

        // Use binary search to find the kth smallest element.
        while (low <= high) {
            int midValue = low + (high - low) / 2;
            int numOfElementsLessThanMid = getNumOfElementsLessThanOrEqual(matrix, midValue);

            if (numOfElementsLessThanMid < k) {
                low = midValue + 1;
            } else {
                high = midValue - 1;
            }
        }
        return low;
    }

    /**
     * Returns the number of elements in the matrix less than or equal to the given value.
     *
     * @param matrix The input matrix.
     * @param value The value to compare with.
     * @return The number of elements less than or equal to the given value.
     */
    private int getNumOfElementsLessThanOrEqual(int[][] matrix, int value) {
        int n = matrix.length;
        int count = 0;
        int row = 0, col = n - 1;

        // Start from the top right corner of the matrix and compare with the given value.
        while (row < n && col >= 0) {
            if (matrix[row][col] <= value) {
                count += col + 1;
                row++;
            } else {
                col--;
            }
        }
        return count;
    }
}

// Initialization:
//        Set low to the smallest element in the matrix (matrix[0][0]).
//        Set high to the largest element in the matrix (matrix[n-1][n-1]).

// Binary Search:
//        While low is less than or equal to high:
//        Set mid to the average of low and high.
//        Count the number of elements less than or equal to mid (use a helper function to achieve this).
//        If this count is less than k, set low to mid + 1.
//        Otherwise, set high to mid - 1.
//  Return low.
//        The helper function countLessEqual should take in the mid value and matrix and return the count of numbers less than or equal to mid:
//
//        Start from the top right corner of the matrix.
//        If the current number is less than or equal to mid, add the row index + 1 to the count and move left.
//        Otherwise, move down.


//    This approach does not use more than O(1) memory (not counting the input itself) and its time complexity can be
//    approximated to O(n * log(max-min)), where max is the largest element and min is the smallest element in the
//    matrix. The log(max-min) part comes from the binary search.