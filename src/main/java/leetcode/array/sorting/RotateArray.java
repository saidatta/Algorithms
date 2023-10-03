package leetcode.array.sorting;

public class RotateArray {

    // brute force
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k %= n; // Ensure k is not greater than length
        for (int i = 0; i < k; i++) {
            int previous = nums[n - 1];
            for (int j = 0; j < n; j++) {
                int temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    // extra array
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }
        System.arraycopy(temp, 0, nums, 0, n);
    }


//    Input: nums = [1,2,3,4,5,6,7], k = 3
    // 7 6 5 4 .... 1
    // 5 6 7 4 3 2 1
    // 5 6 7 1 2 3 4
//    Output: [5,6,7,1,2,3,4]
    // o(1) space
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        // Ensure k is not greater than length
        k %= n;

        // Reverse the entire array
        reverse(nums, 0, n - 1);

        // Reverse the first k elements
        reverse(nums, 0, k - 1);

        // Reverse the remaining elements
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
