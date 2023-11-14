package leetcode.array.binarysearch;

// https://leetcode.com/problems/cutting-ribbons/
// https://www.youtube.com/watch?v=ha8RA6ZpRyY
public class CuttingRibbons {
    public static int maxLength(int[] ribbons, int k) {
        int low = 1, high = 0;
        for (int ribbon : ribbons) {
            high = Math.max(high, ribbon);
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canCut(ribbons, mid, k)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return high;
    }

    private static boolean canCut(int[] ribbons, int mid, int k) {
        int count = 0;
        for (int ribbon : ribbons) {
            count += ribbon / mid;
        }

        return count >= k;
    }

    public static void main(String[] args) {
        int[] ribbons1 = {9,7,5};
        int k1 = 3;
        System.out.println(maxLength(ribbons1, k1)); // Expected output: 5

        int[] ribbons2 = {7,5,9};
        int k2 = 4;
        System.out.println(maxLength(ribbons2, k2)); // Expected output: 4

        int[] ribbons3 = {5,7,9};
        int k3 = 22;
        System.out.println(maxLength(ribbons3, k3)); // Expected output: 0
    }

}
//    To solve this problem, we can use a binary search on the answer, as the maximum possible ribbon length lies
//    between 1 and the maximum length of ribbon in the given array.
//
// The idea is to determine, for a given ribbon length m, if we can obtain k ribbons of length m from the ribbons array.
//
//        Algorithm:
//
//        Determine the low and high bounds of the binary search. low will be 1, and high will be the maximum ribbon
//        length in the array.
//        While low is less than or equal to high, calculate mid as the average of low and high.
//        Count how many ribbons of length mid we can obtain from the ribbons array.
//        If the count is greater than or equal to k, then set low to mid + 1. Otherwise, set high to mid - 1.
//        Return high as the result.