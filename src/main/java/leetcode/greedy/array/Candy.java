package leetcode.greedy.array;

// https://leetcode.com/problems/candy/description/
public class Candy {

//    We can do a two-pass solution.
//    First pass: left to right, assigning candies based on increasing ratings.
//    Second pass: right to left, adjusting candies for decreasing ratings.
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int n = ratings.length;
        int[] candies = new int[n];

        // Left to right: assign candies based on increasing ratings.
        candies[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }

        // Right to left: adjust candies for decreasing ratings.
        int totalCandies = candies[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            totalCandies += candies[i];
        }

        return totalCandies;
    }

}
