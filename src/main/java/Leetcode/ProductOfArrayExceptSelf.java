package Leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/product-of-array-except-self/#/description
 *
 * Created by venkatamunnangi on 3/19/17.
 */
public class ProductOfArrayExceptSelf {
    // O(n) without extra space
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // Calculate lefts and store in res.
        int left = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                left = left * nums[i - 1];
            }
            res[i] = left;
        }

        // Calculate rights and the product from the end of the array.
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1) {
                right = right * nums[i + 1];
            }
            res[i] *= right;
        }
        return res;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int right = 0;
        for (int i = n - 1; i > 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    public static void main(String [] args) {
        ProductOfArrayExceptSelf productOfArrayExceptSelf = new ProductOfArrayExceptSelf();
        int [] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productOfArrayExceptSelf.productExceptSelf2(nums)));
    }

    public int[] productExceptSelfSlow(int[] nums) {
        int [] ans = new int[nums.length];
        for(int i = 0; i< nums.length; i++) {
            int curr = i;
            int product =1;
            while(--curr>=0) {
                product *= nums[curr];
            }
            curr = i;

            while(++curr < nums.length) {
                product *= nums[curr];
            }
            ans[i] = product;
        }

        return ans;
    }
}
