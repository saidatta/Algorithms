package leetcode.array.sorting;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/sort-array-by-parity/
public class SortArrayByParity {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // Increment 'left' pointer if even
            while (left < right && nums[left] % 2 == 0) {
                left++;
            }

            // Decrement 'right' pointer if odd
            while (left < right && nums[right] % 2 == 1) {
                right--;
            }

            // Swap odd number on the left with even number on the right
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }

    public int[] sortArrayByParityComparator(int[] A) {
        Integer[] B = new Integer[A.length];
        for (int t = 0; t < A.length; ++t) {
            B[t] = A[t];
        }

        Arrays.sort(B, Comparator.comparingInt(a -> a % 2));

        for (int t = 0; t < A.length; ++t)
            A[t] = B[t];
        return A;

        /* Alternative:
        return Arrays.stream(A)
                     .boxed()
                     .sorted((a, b) -> Integer.compare(a%2, b%2))
                     .mapToInt(i -> i)
                     .toArray();
        */
    }

    public static void main(String[] args) {
        SortArrayByParity sorter = new SortArrayByParity();
        int[] nums1 = {3, 1, 2, 4};
        int[] result1 = sorter.sortArrayByParity(nums1);
        for (int num : result1) {
            System.out.print(num + " ");
        }
    }
}
