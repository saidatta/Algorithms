package Leetcode;

/**
 * https://leetcode.com/problems/predict-the-winner/
 *
 *
 */
public class PredictTheWinner {
    public boolean PredictTheWinner(int... nums) {
        if(nums == null || nums.length == 0) {
            return true;
        }

        int totalElements = nums.length;
        int[][] intermediateStates = new int[totalElements][totalElements];
        for (int i = 0; i < totalElements; i++) {
            intermediateStates[i][i] = nums[i];
        }

        for (int turnNumber = 1; turnNumber < totalElements; turnNumber++) {
            for (int i = 0; i < totalElements - turnNumber; i++) {
                int j = i + turnNumber;
                intermediateStates[i][j] = Math.max(nums[i] - intermediateStates[i + 1][j], nums[j] - intermediateStates[i][j - 1]);
            }
        }

        // how much more does Player 1 have over player 2.
        return intermediateStates[0][totalElements - 1] >= 0;
    }

    public static void main(String [] args) {
        PredictTheWinner predictTheWinner = new PredictTheWinner();
//        System.out.println(predictTheWinner.PredictTheWinner(1,5,2));
        System.out.println(predictTheWinner.PredictTheWinner(1,5,233,7));

    }
}
