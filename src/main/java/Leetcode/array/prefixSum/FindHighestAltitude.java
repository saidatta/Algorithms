package Leetcode.array.prefixSum;

public class FindHighestAltitude {
    public int largestAltitude(int[] gain) {
        int m = 0, p = 0;
        for (int j : gain) {
            p = p + j;
            if (p > m) {
                m = p;
            }
        }
        return m;
    }
}
