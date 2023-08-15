package Leetcode.dp.math;

/**
 * https://leetcode.com/problems/divisor-game/
 */
public class DivisorGame {
    public boolean divisorGame(int N) {
        return (N & 1) == 0;
    }
}
