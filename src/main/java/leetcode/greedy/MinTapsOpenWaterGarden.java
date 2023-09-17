package leetcode.greedy;

// https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/description/
public class MinTapsOpenWaterGarden {
    public int minTaps(int n, int[] ranges) {
        int[] maxRangeAtPosition = new int[n + 1];

        // Convert taps to intervals and find the max coverage at each position.
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            maxRangeAtPosition[left] = Math.max(maxRangeAtPosition[left], right);
        }

        int totalTaps = 0;
        int currentEnd = 0;
        int nextEnd = 0;

        for (int i = 0; i <= n && currentEnd < n; currentEnd = nextEnd) {
            totalTaps++;
            // Find the tap that can water the furthest position to the right.
            while (i <= n && i <= currentEnd) {
                nextEnd = Math.max(nextEnd, maxRangeAtPosition[i]);
                i++;
            }
            // If no progress is made, the garden cannot be fully watered.
            if (currentEnd == nextEnd) {
                return -1;
            }
        }

        return currentEnd >= n ? totalTaps : -1;
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[] ranges1 = {3, 4, 1, 1, 0, 0};
        System.out.println(new MinTapsOpenWaterGarden().minTaps(n1, ranges1));  // Output: 1

        int n2 = 3;
        int[] ranges2 = {0, 0, 0, 0};
        System.out.println(new MinTapsOpenWaterGarden().minTaps(n2, ranges2));  // Output: -1
    }

}
