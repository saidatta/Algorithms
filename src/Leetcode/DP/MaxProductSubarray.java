package Leetcode.DP;

/**
 * https://leetcode.com/problems/maximum-product-subarray/?tab=Description
 *
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 *
 * Created by venkatamunnangi on 3/2/17.
 */
public class MaxProductSubarray {

    int maxProduct(int[] A) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        int maxProduct = A[0];
        int minProduct = A[0];
        int maxRes = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] >= 0) { // checking for positive
                maxProduct = Math.max(maxProduct * A[i], A[i]);
                minProduct = Math.min(minProduct * A[i], A[i]);
            } else {
                int temp = maxProduct;
                maxProduct = Math.max(minProduct * A[i], A[i]);
                minProduct = Math.min(temp * A[i], A[i]);
            }
            maxRes = Math.max(maxRes, maxProduct);
        }
        return maxRes;
    }

    public static void main(String [] args) {
        MaxProductSubarray maxProductSubarray = new MaxProductSubarray();
        int [] arr = {2,3,-2,4};
        maxProductSubarray.maxProduct(arr);
    }
}
