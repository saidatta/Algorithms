package Leetcode;

public class LongestValidParentheses {
//    The idea is go through the string and use DP to store the longest valid parentheses at that point.
//    take example "()(())"
//    i : [0,1,2,3,4,5]
//    s : [( ,) ,( ,( ,) ,) ]
//    DP:[0,2,0,0,2,6]
//
//            1, We count all the ‘(‘.
//            2, If we find a ‘)’ and ‘(‘ counter is not 0, we have at least a valid result size of 2. “()"
//            3, Check the the one before (i - 1). If DP[i - 1] is not 0 means we have something like this “(())” . This will have DP “0024"
//            4, We might have something before "(())”. Take "()(())” example, Check the i = 1 because this might be a consecutive valid string.

    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int result = 0;
        int leftParenthesesCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftParenthesesCount++;
            } else if (leftParenthesesCount > 0){
                dp[i] = dp[i - 1] + 2;
                dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                result = Math.max(result, dp[i]);
                leftParenthesesCount--;
            }
        }
        return result;
    }

    public static void main(String [] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses("()"));
        System.out.println(longestValidParentheses.longestValidParentheses("()(())"));
    }
}
