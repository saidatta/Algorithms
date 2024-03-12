package leetcode.array.sorting;

public class SortedSquares {
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1, pos = n - 1;

        while (left <= right) {
            int leftVal = Math.abs(nums[left]);
            int rightVal = Math.abs(nums[right]);
            if (leftVal > rightVal) {
                result[pos] = leftVal * leftVal;
                left++;
            } else {
                result[pos] = rightVal * rightVal;
                right--;
            }
            pos--;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {-4,-1,0,3,10};
        int[] result1 = sortedSquares(nums1);
        printArray(result1);  // Expected output: [0,1,9,16,100]

        int[] nums2 = {-7,-3,2,3,11};
        int[] result2 = sortedSquares(nums2);
        printArray(result2);  // Expected output: [4,9,9,49,121]
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
