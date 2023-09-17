package leetcode.array.binarysearch;

// https://leetcode.com/problems/split-linked-list-in-parts/description/
public class HouseRobberIV {

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 5, 9};
        int k1 = 2;
        System.out.println(minCapability(nums1, k1)); // Expected output: 5

        int[] nums2 = {2, 7, 9, 3, 1};
        int k2 = 2;
        System.out.println(minCapability(nums2, k2)); // Expected output: 2
    }

    public static int minCapability(int[] nums, int k) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (canRob(nums, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean canRob(int[] nums, int capability, int k) {
        int count = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] <= capability) {
                count++;
                i += 2;
            } else {
                i++;
            }
        }
        return count >= k;
    }
}

