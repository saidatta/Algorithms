package leetcode.array.binarysearch;

// https://leetcode.com/problems/guess-number-higher-or-lower/description/
public class GuessGameHigherLower {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;

        while (low <= high) {
            int mid = low + (high - low) / 2; // avoid potential overflow
            int result = guess(mid);

            if (result == 0) {
                return mid;
            } else if (result == 1) {
                // Our guess is too low
                low = mid + 1;
            } else {
                // Our guess is too high
                high = mid - 1;
            }
        }

        return -1; // this line is just for safety, the while-loop should always find and return the answer.
    }

    private int guess(int mid) {
//        You call a pre-defined API int guess(int num), which returns three possible results:
//
//        -1: Your guess is higher than the number I picked (i.e. num > pick).
//        1: Your guess is lower than the number I picked (i.e. num < pick).
//        0: your guess is equal to the number I picked (i.e. num == pick).
        return 0;
    }
}
