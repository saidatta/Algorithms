package leetcode.array.simulation;

// https://leetcode.com/problems/find-the-winner-of-the-circular-game/description/
public class FindWinnerCircularGame {
    public int findTheWinner(int n, int k) {
        int winner = 0;
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
