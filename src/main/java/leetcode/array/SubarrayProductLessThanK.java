package leetcode.array;

/**
 * Created by venkatamunnangi on 9/12/19.
 */
public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }

        int totalCount = 0;
        int currentProduct = 1;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            currentProduct *= nums[right];
            while (left <= right && currentProduct >= k) {
                currentProduct /= nums[left++];
            }
            totalCount += right - left + 1;
        }
        return totalCount;
    }

    public static void main(String [] args) {
        SubarrayProductLessThanK subarrayProductLessThanK = new SubarrayProductLessThanK();

        System.out.println(subarrayProductLessThanK.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6},100));
    }
}
