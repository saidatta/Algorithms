package leetcode.array.random;

// https://leetcode.com/problems/random-pick-with-weight/description/
public class RandomPickWeight {
    private int[] prefixSums;
    private int totalSum;

    public RandomPickWeight(int[] w) {
        this.prefixSums = new int[w.length];

        int prefixSum = 0;
        for (int i = 0; i < w.length; ++i) {
            prefixSum += w[i];
            this.prefixSums[i] = prefixSum;
        }
        this.totalSum = prefixSum;
    }

    public int pickIndex() {
        double target = this.totalSum * Math.random();

        // run a binary search to find the target zone
        int low = 0, high = this.prefixSums.length;
        while (low < high) {
            // better to avoid the overflow
            int mid = low + (high - low) / 2;
            if (target > this.prefixSums[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    public static void main(String[] args) {
        RandomPickWeight solution = new RandomPickWeight(new int[] {1, 3});

        // Print the output of pickIndex() a few times to observe the distribution
        for (int i = 0; i < 10; i++) {
            System.out.println(solution.pickIndex());
        }
    }
}
