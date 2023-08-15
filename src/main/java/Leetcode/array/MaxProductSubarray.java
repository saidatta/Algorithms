package Leetcode.array;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 * <p>
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number)
 * which has the largest product.
 * <p>
 * Created by venkatamunnangi on 9/11/19.
 */
public class MaxProductSubarray {

    /**
     * this is very similar to the " max cumulative sum subarray" problem. here you keep 2 values: the max cumulative
     * product UP TO current element starting from SOMEWHERE in the past, and the minimum cumuliative product UP TO
     * current element . it would be easier to see the DP structure if we store these 2 values for each index, like
     * maxProduct[i],minProduct[i] at each new element, u could either add the new element to the existing product,
     * or start fresh the product from current index (wipe out previous results), hence the 2 Math.max() lines.
     *
     * if we see a negative number, the "candidate" for max should instead become the previous min product, because a
     * bigger number multiplied by negative becomes smaller, hence the swap()
     *
     * @param nums
     * @return
     */
    public int maxProduct(int... nums) {
        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int num : nums) {
            if (num < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * num, num);
            imin = Math.min(imin * num, num);

            max = Math.max(max, imax);
        }
        return max;
    }

    public static void main(String [] args) {
        MaxProductSubarray maxProductSubarray = new MaxProductSubarray();

        System.out.println(maxProductSubarray.maxProduct(2,3,-2,4));
    }
}
