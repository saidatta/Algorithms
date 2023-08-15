package Leetcode.dp;

import java.util.Arrays;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/#/description
 *
 * [10, 9, 2, 5, 3, 7, 101, 18] - [2, 3, 7, 101], therefore the length is 4.
 *
 * Created by venkatamunnangi on 4/23/17.
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int... nums) {
        int[] dp = new int[nums.length];
        int lengthOfIncreasingSequence = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, lengthOfIncreasingSequence, x);

            // i returns the potential insertion point within the array dp
            // for eg. if number potentially inserted at index 0, then i = -1 and so on.
            // -<insertion point> - 1
            if(i < 0) {
                // converting positive index point.
                i = -(i + 1);
            }
            dp[i] = x;
            if(i == lengthOfIncreasingSequence) {
                lengthOfIncreasingSequence++;
            }
        }

        return lengthOfIncreasingSequence;
    }

    public static void main(String [] args) {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        out.println(longestIncreasingSubsequence.lengthOfLIS(10, 9, 2, 5, 3, 7, 101, 18));
    }

    /**
     * DP way of solving LIS
     */
    public int longestSubsequenceWithActualSolution(int[] arr){
        int[] T = new int[arr.length];
        int[] actualSolution = new int[arr.length];
        for(int i=0; i < arr.length; i++){
            T[i] = 1;
            actualSolution[i] = i;
        }

        for(int i=1; i < arr.length; i++){
            for(int j=0; j < i; j++){
                if(arr[i] > arr[j]){
                    if(T[j] + 1 > T[i]){
                        T[i] = T[j] + 1;
                        //set the actualSolution to point to guy before me
                        actualSolution[i] = j;
                    }
                }
            }
        }

        //find the index of max number in T
        int maxIndex = 0;
        for(int i=0; i < T.length; i++){
            if(T[i] > T[maxIndex]){
                maxIndex = i;
            }
        }

        //lets print the actual solution
        int t = maxIndex;
        int newT = maxIndex;
        do{
            t = newT;
            out.print(arr[t] + " ");
            newT = actualSolution[t];
        }while(t != newT);
        out.println();

        return T[maxIndex];
    }
}
