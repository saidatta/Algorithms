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
        int minNumberCoinsUsed [] = new int[total +1];
        int indexCoinUsed [] = new int[total +1];

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
