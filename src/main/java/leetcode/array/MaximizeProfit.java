package leetcode.array;

import java.util.List;

// https://leetcode.com/problems/maximize-the-profit-as-the-salesman/description/
public class MaximizeProfit {
    public int maximizeTheProfit(int n, List<List<Integer>> offersList) {
        int[] res = new int[n + 1];
        int pos = 0;

        // Sort the list based on the second element and then the first
        offersList.sort((a, b) -> {
            if (a.get(1).equals(b.get(1))) {
                return Integer.compare(a.get(0), b.get(0));
            }
            return Integer.compare(a.get(1), b.get(1));
        });

        for (int i = 1; i <= n; i++) {
            int bestVal = res[i - 1];
            while (pos < offersList.size() && offersList.get(pos).get(1) == i - 1) {
                bestVal = Math.max(bestVal, offersList.get(pos).get(2) + res[offersList.get(pos).get(0)]);
                pos++;
            }
            res[i] = bestVal;
        }

        return res[n];
    }
}
