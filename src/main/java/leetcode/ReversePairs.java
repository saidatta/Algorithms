package leetcode;

/**
 * https://leetcode.com/problems/reverse-pairs/#/description
 * https://discuss.leetcode.com/topic/79227/general-principles-behind-problems-similar-to-reverse-pairs
 *
 * Created by venkatamunnangi on 3/30/17.
 */
public class ReversePairs {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);

        // Count reverse pairs
        for (int i = left, j = mid + 1; i <= mid; i++) {
            while (j <= right && nums[i] > 2 * nums[j]) {
                j++;
            }
            count += j - (mid + 1);
        }

        // Standard merging in merge sort
        merge(nums, left, mid, right);
        return count;
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        System.arraycopy(temp, 0, nums, left, temp.length);
    }

    public static void main(String[] args) {
        ReversePairs solution = new ReversePairs();
        System.out.println(solution.reversePairs(new int[]{1, 3, 2, 3, 1})); // Output: 2
        System.out.println(solution.reversePairs(new int[]{2, 4, 3, 5, 1})); // Output: 3
    }
}

