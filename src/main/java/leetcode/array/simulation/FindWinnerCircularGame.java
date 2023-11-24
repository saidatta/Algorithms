package leetcode.array.simulation;

// https://leetcode.com/problems/find-the-winner-of-the-circular-game/description/
// https://www.youtube.com/watch?v=XG6txfMFBVk
// https://www.youtube.com/watch?v=2XC_Va55d-w - queue solution
public class FindWinnerCircularGame {
    public int findTheWinner(int n, int k) {
        int winner = 0;
        // Slowly building up the winners each round.
        // Imagine these for loops w.r.t to each round.
        for (int i = 2; i <= n; i++) {
            winner = (winner + k) % i;
        }
        return winner + 1; // Adjusting for 1-based indexing
    }

    public static void main(String[] args) {
        FindWinnerCircularGame solution = new FindWinnerCircularGame();
        System.out.println(solution.findTheWinner(5, 2)); // Output: 3
        System.out.println(solution.findTheWinner(6, 5)); // Output: 1
    }
}
