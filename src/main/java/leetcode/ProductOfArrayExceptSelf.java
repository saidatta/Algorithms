package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/product-of-array-except-self/#/description
 *
 * Created by venkatamunnangi on 3/19/17.
 */
public class ProductOfArrayExceptSelf {
    /**
     * Returns an array where each element at index i is the product of all
     * numbers in the original array except nums[i].
     * This solution runs in O(n) time without extra space.
     *
     * @param nums The input array
     * @return The resultant array
     */
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Calculate the product of all elements to the left of each element.
        // Start with left as 1 (for the leftmost element).
        int leftProduct = 1;
        for (int i = 0; i < n; i++) {
            // For elements other than the leftmost, multiply the leftProduct with
            // the previous element of nums.
            if (i > 0) {
                leftProduct *= nums[i - 1];
            }
            result[i] = leftProduct;
        }

        // Calculate the product of all elements to the right of each element
        // and multiply with previously stored left product.
        // Start with right as 1 (for the rightmost element).
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            // For elements other than the rightmost, multiply the rightProduct with
            // the next element of nums.
            if (i < n - 1) {
                rightProduct *= nums[i + 1];
            }
            result[i] *= rightProduct;
        }

        return result;
    }


    /**
     * Calculates the product of all elements in the array except for the current element.
     *
     * @param nums input array of integers
     * @return an array where each entry is the product of all other elements except the current one
     */
    public int[] productExceptSelf(int[] nums) {
        int arrayLength = nums.length;
        int[] products = new int[arrayLength];

        // Initialize the first element of the products array to 1
        products[0] = 1;

        // Compute the product of all elements to the left of each index
        // For example, products[i] will store the product of all elements to the left of i
        for (int i = 1; i < arrayLength; i++) {
            products[i] = products[i - 1] * nums[i - 1];
        }

        // Calculate the product of all elements to the right of each index
        // and multiply it with the existing value (which is the product of all elements to the left)

        // to store the product of all elements to the right of the current index
        int rightProduct = 1;
        for (int i = arrayLength - 1; i >= 0; i--) {
            products[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return products;
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
