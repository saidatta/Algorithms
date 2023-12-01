package leetcode.array.slidingwindow;

import java.util.Arrays;

public class MovingStonesUntilConsecutiveII {
    public int[] numMovesStonesII(int[] stones) {
        // Sort the stones array to process them in order.
        Arrays.sort(stones);
        int totalStones = stones.length;

        // Calculate the maximum moves
        // This is done by finding the gap at either end of the sorted array (excluding one stone)
        // and subtracting the number of stones already in place
        int maxMoves = Math.max(
                stones[totalStones - 1] - stones[1],
                stones[totalStones - 2] - stones[0]
        ) - (totalStones - 2);
        int minMoves = maxMoves;

        // Sliding window approach to find the minimum moves
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < totalStones; ++windowEnd) {
            // Expand the window until it can no longer fit within totalStones length
            while (stones[windowEnd] - stones[windowStart] >= totalStones) {
                windowStart++;
            }

            // Special case: window of size n-1 that needs one move
            if (windowEnd - windowStart + 1 == totalStones - 1
                    && stones[windowEnd] - stones[windowStart] == totalStones - 2) {
                minMoves = Math.min(minMoves, 2);
            } else {
                // General case: Calculate minimum moves for the current window
                minMoves = Math.min(minMoves, totalStones - (windowEnd - windowStart + 1));
            }
        }

        return new int[]{minMoves, maxMoves};
    }

    public static void main(String[] args) {
        MovingStonesUntilConsecutiveII solution = new MovingStonesUntilConsecutiveII();
        System.out.println(Arrays.toString(solution.numMovesStonesII(new int[]{7, 4, 9}))); // Output: [1, 2]
        System.out.println(Arrays.toString(solution.numMovesStonesII(new int[]{6, 5, 4, 3, 10}))); // Output: [2, 3]
    }
}

//    Sort the Stones: First, sort the stones array.
//        Find Minimum Moves:
//        Use a sliding window approach to find the smallest window that can fit all stones. The size of this window
//        is n where n is the total number of stones.
//        Within this window, count the number of stones already present. The minimum moves will be the number of
//        stones needed to fill the window (n - count).
//
//        Special Case: If there is a window of size n - 1 with one stone missing either at the start or the end, and
//        the next stone is two positions away, only one move is needed.
//
//        Find Maximum Moves:
//        Generally, the maximum number of moves is max(stones[n-1] - stones[1], stones[n-2] - stones[0]) - (n-2),
//        which represents moving stones one by one to create a consecutive sequence.
//        Special Case: When the two endpoint stones have two or fewer stones between them, it's quicker to move these
//        endpoint stones.