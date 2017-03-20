package Leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/product-of-array-except-self/#/description
 *
 * Created by venkatamunnangi on 3/19/17.
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= res[0];
            res[0] *= nums[i];
        }
        return res;
    }

    public static void main(String [] args) {
        ProductOfArrayExceptSelf productOfArrayExceptSelf = new ProductOfArrayExceptSelf();
        int [] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productOfArrayExceptSelf.productExceptSelf(nums)));
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
