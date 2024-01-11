package leetcode.array.counting;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-number-of-coins-to-be-added/
public class MinAddedCoins {
    public int minCoinsToAdd(int[] coins, int target) {
        Arrays.sort(coins);
        int maxObtainable = 0;
        int coinsToAdd = 0;

        for (int coin : coins) {
            while (coin > maxObtainable + 1 && maxObtainable < target) {
                maxObtainable += maxObtainable + 1;
                coinsToAdd++;
            }
            maxObtainable += coin;
            if (maxObtainable >= target) {
                break;
            }
        }

        while (maxObtainable < target) {
            maxObtainable += maxObtainable + 1;
            coinsToAdd++;
        }

        return coinsToAdd;
    }

    public static void main(String[] args) {
        MinAddedCoins solution = new MinAddedCoins();
        System.out.println(solution.minCoinsToAdd(new int[]{1, 4, 10}, 19)); // Output: 2
        System.out.println(solution.minCoinsToAdd(new int[]{1, 4, 10, 5, 7, 19}, 19)); // Output: 1
        System.out.println(solution.minCoinsToAdd(new int[]{1, 1, 1}, 20)); // Output: 3
    }
}
