package leetcode.array.sorting;

import java.util.Arrays;

// https://leetcode.com/problems/moving-stones-until-consecutive/
public class MovingStonesUntilConsecutive {
    public int[] numMovesStones(int a, int b, int c) {
        // Sort the stones to easily determine their relative positions
        int[] stones = {a, b, c};
        Arrays.sort(stones);

        // Calculate the maximum moves by considering the gaps between the stones
        // This is the total span minus the actual positions of the two other stones
        int maxMoves = stones[2] - stones[0] - 2;

        // Initialize minimum moves
        int minMoves;

        // Check the size of the gaps between the stones
        // If any two stones have a gap of 1 or 2, only one move is required
        if (stones[2] - stones[1] <= 2 || stones[1] - stones[0] <= 2) {
            minMoves = 1;
        } else if (stones[2] - stones[1] == 1 && stones[1] - stones[0] == 1) {
            // If stones are already consecutive, no moves are required
            minMoves = 0;
        } else {
            // Otherwise, at least two moves are required
            minMoves = 2;
        }

        // Return the array of minimum and maximum moves
        return new int[]{minMoves, maxMoves};
    }


    public static void main(String[] args) {
        MovingStonesUntilConsecutive solution = new MovingStonesUntilConsecutive();
        System.out.println(Arrays.toString(solution.numMovesStones(1, 2, 5))); // Output: [1, 2]
        System.out.println(Arrays.toString(solution.numMovesStones(4, 3, 2))); // Output: [0, 0]
        System.out.println(Arrays.toString(solution.numMovesStones(3, 5, 1))); // Output: [1, 2]
    }
}
