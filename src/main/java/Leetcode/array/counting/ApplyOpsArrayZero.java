package Leetcode.array.counting;

public class ApplyOpsArrayZero {
        public boolean checkArray(int[] nums, int k) {
            int[] diff = new int[nums.length];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i >= k) sum -= diff[i - k];
                diff[i] = nums[i] - sum;
                if (diff[i] < 0) return false;
                sum += diff[i];
            }
            for (int i = nums.length; i < nums.length + k - 1; i++) {
                if (i >= k) sum -= diff[i - k];
                if (sum > 0) return false;
            }
            return true;
        }


    public boolean canConvert2(int[] nums, int k) {
        int n = nums.length;
        int[] delta = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i - k >= 0) {
                nums[i - k] -= sum;
            }
            sum += delta[i] = i < k ? nums[i] : (nums[i] - nums[i - k]);
            if (nums[i - k] < 0) {
                return false;
            }
        }
        return sum == 0;
    }

    public static void main(String[] args) {
        ApplyOpsArrayZero solution = new ApplyOpsArrayZero();

        int[] nums1 = {2,2,3,1,1,0};
        int k1 = 3;
        System.out.println(solution.checkArray(nums1, k1));  // Output: true

        int[] nums2 = {1,3,1,1};
        int k2 = 2;
        System.out.println(solution.checkArray(nums2, k2));  // Output: false

        int[] nums3 = {27,99,7,1,94,63,84,46,76,35,97,77,19,72,3};
        int k3 = 2;
        System.out.println(solution.checkArray(nums3, k3));  // Output: false
    }
}
