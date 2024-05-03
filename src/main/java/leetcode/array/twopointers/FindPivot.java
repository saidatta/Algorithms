package leetcode.array.twopointers;

// https://leetcode.com/problems/find-the-pivot-integer/editorial/
public class FindPivot {
    public int pivotInteger(int n) {
        int left = 1, right = n;
        int sumLeft = left, sumRight = right;

        if(n == 1) {
            return n;
        }

        while (left < right) {
            if (sumLeft < sumRight) {
                sumLeft += ++left;
            } else {
                sumRight += --right;
            }

            if(sumRight == sumLeft && left + 1 == right - 1)
                return left + 1;
        }
        return -1;
    }

    // O(logn)
    public int pivotIntegerBinarySearch(int n) {
        // Initialize left and right pointers for binary search
        int left = 1, right = n;

        // Calculate the total sum of the sequence
        int totalSum = n * (n + 1) / 2;

        // Binary search for the pivot point
        while (left < right) {
            // Calculate the mid-point
            int mid = (left + right) >>> 1;

            // Check if the difference between the square of mid and the total sum is negative
            if (mid * mid - totalSum < 0) {
                left = mid + 1; // Adjust the left bound if the sum is smaller
            } else {
                right = mid; // Adjust the right bound if the sum is equal or greater
            }
        }

        // Check if the square of the left pointer minus the total sum is zero
        if (left * left - totalSum == 0) {
            return left;
        } else {
            return -1;
        }
    }
}
