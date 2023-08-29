package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/optimal-account-balancing/
 */
public class OptimalAccountBalancing {
    private int[] isZeroSum;
    private int[] dp;
    private List<Integer> amounts;
    private int n;

    public int minTransfers(int[][] transactions) {
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int[] v : transactions) {
            m.put(v[0], m.getOrDefault(v[0], 0) + v[2]);
            m.put(v[1], m.getOrDefault(v[1], 0) - v[2]);
        }
        amounts = new ArrayList<>();
        for (int v : m.values()) {
            if (v != 0) {
                amounts.add(v);
            }
        }
        n = amounts.size();
        if (n == 0) {
            return 0;
        }
        int dpSize = 1 << n;
        isZeroSum = new int[dpSize];
        Arrays.fill(isZeroSum, -1);
        dp = new int[dpSize];
        Arrays.fill(dp, -1);
        return n - getOrCompute((1 << n) - 1);
    }

    private boolean checkZeroSum(int mask) {
        if (isZeroSum[mask] != -1) {
            return isZeroSum[mask] == 1;
        }
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            if ((mask & (1 << i)) != 0) {
                sum += amounts.get(i);
            }
        }
        return (isZeroSum[mask] = (sum == 0 ? 1 : 0)) == 1;
    }

    private int getOrCompute(int mask) {
        if (dp[mask] != -1) {
            return dp[mask];
        }
        int res = 1;
        for (int submask = mask - 1; submask > 0; submask = (submask - 1) & mask) {
            if (checkZeroSum(submask)) {
                res = Math.max(res, 1 + getOrCompute(submask));
            }
        }
        return dp[mask] = res;
    }

    public static void main(String[] args) {
        OptimalAccountBalancing debtSettlement = new OptimalAccountBalancing();
        int[][] transactions = {{0,1,10}, {1,0,1}, {1,2,5}, {2,0,5}};
        System.out.println(debtSettlement.minTransfers(transactions));  // Output: 1
    }
}
