package leetcode.array.binarysearch;

// https://leetcode.com/problems/kth-missing-positive-number/description/
public class KthMissingPositiveNum {
//    One way to approach this problem is to use a counter for the next expected number, starting from 1. Then, you can
//    iterate through the given array and for each missing number, decrement k. When k reaches zero, you have found the
//    kth missing number.
//
//    However, to solve the problem in less than O(n) complexity, you can use a binary search approach to find the
//    position in the array where the missing numbers count equals k.
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = (left + right) >>> 1;
            // arr[mid] - (mid + 1) gives the number of missing integers before arr[mid]
            if (arr[mid] - (mid + 1) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // if k missing numbers are found before arr, then answer is k + left.
        // otherwise answer will be k + the count of missing numbers before arr[left].
        return left + k;
    }

    public static void main(String[] args) {
        KthMissingPositiveNum solver = new KthMissingPositiveNum();
        System.out.println(solver.findKthPositive(new int[] {2,3,4,7,11}, 4)); // outputs 9
        System.out.println(solver.findKthPositive(new int[] {1,2,3,4}, 2));   // outputs 6
    }
}
