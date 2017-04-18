package Skiena.Chp8_DynamicProgramming;

/**
 * https://leetcode.com/problems/coin-change/
 *
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 *
 * Created by venkatamunnangi on 2/21/17.
 */
public class CoinChange {
    /**
     * Bottom up way of solving this problem.
     * Keep input sorted. Otherwise temp[j-arr[i]) + 1 can become Integer.Max_value + 1 which
     * can be very low negative number
     * Returns Integer.MAX_VALUE - 1 if solution is not possible.
     */
    public int minimumCoinBottomUp(int total, int coins[]){
        int[] minNumberCoinsUsed = new int[total + 1];
        int[] indexCoinUsed = new int[total + 1];

        minNumberCoinsUsed[0] = 0;

        for(int i = 1;i <= total; i++) {
            minNumberCoinsUsed[i] = Integer.MAX_VALUE - 1;
            indexCoinUsed[i] = -1;
        }

        for(int i = 0; i< coins.length; i++) {
            for(int j = 1; j<= total; j++) {
                if(j >= coins[i]) {
                    int previusMinNumberUsedPlusCurrentCoin = minNumberCoinsUsed[j-coins[i]] + 1;
                    if(previusMinNumberUsedPlusCurrentCoin < minNumberCoinsUsed[j]) { // if not min is stored.
                        minNumberCoinsUsed[j] = previusMinNumberUsedPlusCurrentCoin;
                        indexCoinUsed[j] = i;
                    }
                }
            }
        }
        return minNumberCoinsUsed[total];
    }

    public int coinChange(int[] coins, int amount) {
        if(amount<1) return 0;
        return helper(coins, amount, new int[amount]);
    }

    private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
        if(rem<0) return -1; // not valid
        if(rem==0) return 0; // completed
        if(count[rem-1] != 0) return count[rem-1]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for(int coin : coins) {
            int res = helper(coins, rem-coin, count);
            if(res>=0 && res < min)
                min = 1+res;
        }
        count[rem-1] = (min==Integer.MAX_VALUE) ? -1 : min;
        return count[rem-1];
    }

    private void printCoinCombination(int R[], int coins[]) {
        if (R[R.length - 1] == -1) {
            System.out.print("No solution is possible");
            return;
        }
        int start = R.length - 1;
        System.out.print("Coins used to form total ");
        while ( start != 0 ) {
            int j = R[start];
            System.out.print(coins[j] + " ");
            start = start - coins[j];
        }
        System.out.print("\n");
    }


}
