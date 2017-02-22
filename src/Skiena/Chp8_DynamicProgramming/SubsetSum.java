package Skiena.Chp8_DynamicProgramming;

/**
 * Created by venkatamunnangi on 2/21/17.
 */
public class SubsetSum {

    public boolean subsetSum(int input[], int total) {
        if(input == null) {
            return false;
        }

        boolean result [] [] = new boolean[input.length+1][total+1];

        for(int i = 0; i<input.length;i++) {
            result[i][0] = true;
        }

        for(int i = 1; i <= input.length;i++) {
            for(int j = 1; j <= total;j++) {

                int goingBackJSpaces = j- input[i-1]; // go back input[i] spaces for j.
                if(goingBackJSpaces >= 0) {
                    result[i][j] = result[i-1][j]||result[i-1][goingBackJSpaces];
                } else {
                    result[i][j] = result[i-1][j];
                }
            }
        }

        return result[input.length][total];
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
