package leetcode.dp;

/**
 * https://leetcode.com/problems/burst-balloons/#/description
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on
 * it represented by array nums. You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left
 * and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *Note:
 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 *     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * Created by venkatamunnangi on 4/23/17.
 */
public class BurstBalloons {

    // bottom up
    public int maxCoinsDP(int[] nums) {
        // add 1 before and after nums
        int n = nums.length + 2;
        int[] newNums = new int[n];
        System.arraycopy(nums, 0, newNums, 1, n - 2);
        newNums[0] = 1;
        newNums[n - 1] = 1;
        // dp[i][j] represents
        // maximum if we burst all nums[left]...nums[right], inclusive
        int[][] dp = new int[n][n];
        // do not include the first one and the last one
        // since they are both fake balloons added by ourselves and we can not burst
        // them
        for (int left = n - 2; left >= 1; left--) {
            for (int right = left; right <= n - 2; right++) {
                // find the last burst one in newNums[left]...newNums[right]
                for (int i = left; i <= right; i++) {
                    // newNums[i] is the last burst one
                    int gain = newNums[left - 1] * newNums[i] * newNums[right + 1];
                    // recursively call left side and right side
                    int remaining = dp[left][i - 1] + dp[i + 1][right];
                    // update
                    dp[left][right] = Math.max(remaining + gain, dp[left][right]);
                }
            }
        }
        // burst newNums[1]...newNums[n-2], excluding the first one and the last one
        return dp[1][n - 2];
    }


    // D&C with memorization.
    public int maxCoins(int... iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;

        for(int i : iNums) {
            nums[n++] = i;
        }

        int[][] memorization = new int[nums.length][nums.length];

        // Given the boundaries are immutable (cannot be bursted.)
        // and have value of 1.
        nums[0] = nums[n++] = 1;

        return burstBalloons(memorization, nums, 0, n-1);
    }


    // D & C recursive function.
    private int burstBalloons(int[][] memorization, int[] nums, int leftIndex, int rightIndex) {

        if(leftIndex + 1 == rightIndex) {
            // if all the balloons are bursted, these two(leftIndex and rightIndex) variables are
            // the immutable boundaries of the array.
            return 0;
        }

        int ans = 0;

        if(memorization[leftIndex][rightIndex] > 0) {
            return memorization[leftIndex][rightIndex];
        }

        // These bounds are the potential candidate balloons that are allowed to be bursted.
        for(int i = leftIndex + 1; i < rightIndex; i++) {
            int tempBalloonBurst =
                    (nums[leftIndex] * nums[i] * nums[rightIndex]) +
                    burstBalloons(memorization, nums, leftIndex, i) +
                    burstBalloons(memorization, nums, i, rightIndex);

            ans = Math.max(ans, tempBalloonBurst);
        }

        memorization[leftIndex][rightIndex] = ans;
        return ans;
    }

    public static void main(String [] args) {
        BurstBalloons burstBalloons = new BurstBalloons();

        System.out.println(burstBalloons.maxCoins(3,1, 5));
    }
}
