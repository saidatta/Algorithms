package leetcode.binary;

// https://leetcode.com/problems/total-hamming-distance/
public class TotalHammingDistance {
    public int totalHammingDistance(int[] nums) {
        int totalDistance = 0;
        int n = nums.length;

        for (int bit = 0; bit < 32; bit++) {
            int countOnes = 0;
            for (int num : nums) {
                countOnes += (num >> bit) & 1;
            }
            // # of ones that are different w.r.t to the numbers that dont have ones.
            totalDistance += countOnes * (n - countOnes);
        }

        return totalDistance;
    }

    public static void main(String[] args) {
        TotalHammingDistance solution = new TotalHammingDistance();
        System.out.println(solution.totalHammingDistance(new int[]{4, 14, 2})); // Output: 6
        System.out.println(solution.totalHammingDistance(new int[]{4, 14, 4})); // Output: 4
    }
}
