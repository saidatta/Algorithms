package Leetcode.DP;

import java.util.Arrays;

public class LongestNonDecreasingTwoArrays {
    public int maxNonDecreasingLength(int[] a, int[] b) {
        int n = a.length;
        // create a 2D array to store the dynamic programming values
        int[][] dp = new int[n][2];
        // initialize the first row to 1
        dp[0][0] = 1;
        dp[0][1] = 1;
        // the answer variable to store the maximum length
        int ans = 1;
        // loop through the arrays from index 1 to n - 1
        for (int i = 1; i < n; i++) {
            // update the dp values based on the previous values and the current elements
            if (a[i] >= a[i - 1]) dp[i][0] = Math.max(dp[i][0], dp[i - 1][0] + 1);
            if (a[i] >= b[i - 1]) dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] + 1);
            if (b[i] >= a[i - 1]) dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + 1);
            if (b[i] >= b[i - 1]) dp[i][1] = Math.max(dp[i][1], dp[i - 1][1] + 1);
            // update the answer with the maximum of the current dp values
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        // return the answer
        return ans;
    }

//    int maxNonDecreasingLength(vector<int>& a, vector<int>& b) {
//        int n = a.size();
//        vector<vector<int>> dp(n, vector<int>(2, 1));
//        int ans = 1;
//        for(int i=1; i<n; ++i){
//            if(a[i] >= a[i-1])  dp[i][0] = max(dp[i][0], dp[i-1][0] + 1);
//            if(a[i] >= b[i-1])  dp[i][0] = max(dp[i][0], dp[i-1][1] + 1);
//            if(b[i] >= a[i-1])  dp[i][1] = max(dp[i][1], dp[i-1][0] + 1);
//            if(b[i] >= b[i-1])  dp[i][1] = max(dp[i][1], dp[i-1][1] + 1);
//            ans = max(ans, max(dp[i][0], dp[i][1]));
//        }
//        return ans;
//    }


    public static void main(String[] args) {
//        int[] nums1 = {2, 3, 1};
//        int[] nums2 = {1, 2, 1};

                int[] nums1 = {4,2 };
        int[] nums2 = {10,4};

//        int[] nums1 = {8,7,4};
//        int[] nums2 = {13,4,4};

        LongestNonDecreasingTwoArrays longestNonDecreasingTwoArrays = new LongestNonDecreasingTwoArrays();
        int maxLength = longestNonDecreasingTwoArrays.maxNonDecreasingLength(nums1, nums2);
        System.out.println("Maximum Length of Non-Decreasing Subarray: " + maxLength);
    }
}
