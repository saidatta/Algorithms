package Skiena.Chp8_DynamicProgramming;

/**
 *
 * Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
 * to given total. Another variation is given an array is it possible to split it up into 2 equal
 * sum partitions. Partition need not be equal sized. Just equal sum.
 *
 * Created by venkatamunnangi on 2/21/17.
 */
public class SubsetSum {

    //Algo complexity is O(Sum * N) and use O(Sum) memory.
    private int howManySubsetSums(int [] input, int targetTotal) {
        int[] dp = new int[targetTotal+1];
        dp[0] = 1;
        int tempSum = 0;
        for(int i = 0; i<input.length;i++) {
            tempSum += input[i];

            if(tempSum > targetTotal) {
                continue;
            }

            // math.min is to avoid index overflow.
            for(int j = Math.min(tempSum, targetTotal); j >= input[j];j--) {
                dp[j] += dp[j-input[i]];
            }

        }

        return dp[targetTotal];
    }

    public boolean subsetSum(int[] input, int targetTotal) {
        if(input == null) {
            return false;
        }

        boolean[][] result = new boolean[input.length + 1][targetTotal + 1];

        for(int i = 0; i<input.length;i++) {
            result[i][0] = true;
        }

        for(int i = 1; i <= input.length;i++) {
            for(int currentTotal = 1; currentTotal <= targetTotal;currentTotal++) {
                int goingBackJSpaces = currentTotal - input[i-1]; // go back input[i] spaces for currentTotal.
                if(goingBackJSpaces >= 0) {
                    //previous input(top in matrix) is true i.e. currentTotal is not taken into account
                    // or we go back j spaces and determine what the status is for that cell.
                    result[i][currentTotal] = result[i-1][currentTotal]||result[i-1][goingBackJSpaces];
                } else {
                    // if input is less than the current sum
                    // then copy the value from the top(previous input).
                    result[i][currentTotal] = result[i-1][currentTotal];
                }
            }
        }

        return result[input.length][targetTotal];
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;

        for(int i : nums) {
            sum += i;
        }

        if(sum % 2 != 0) {
            return false;
        }


        sum = sum /2;

        boolean [][] sums = new boolean [nums.length+1][sum+1];

        for(int i = 0; i <= nums.length; i++) {
            sums[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for(int j = 1; j<= sum; j++) {
                if(j - nums[i-1] >= 0) {
                    sums[i][j] = sums[i-1][j] || sums[i-1][j-nums[i-1]];
                } else {
                    sums[i][j] = sums[i-1][j];
                }
            }
        }
        return sums[nums.length][sum];
    }

    public static void main(String args[]) {
        SubsetSum ss = new SubsetSum();
        int arr[] = {1, 3, 5, 5, 2, 1, 1, 6};
        System.out.println(ss.canPartition(arr));

        int arr1[] = {2, 3, 7, 8};
        System.out.print(ss.subsetSum(arr1, 11));

    }

}
