package Leetcode.DP.string;

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
        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == '(') {
                leftParenthesesCount++;
            } else if (leftParenthesesCount > 0){
                dp[index] = dp[index - 1] + 2;
                //we need to not only consider valid parenthesis from current set of parens
                // but also from previous ones. () -> group 1, (()) -> group 2  for "()(())"
                dp[index] += (index - dp[index]) >= 0 ? dp[index - dp[index]] : 0;
                result = Math.max(result, dp[index]);
                leftParenthesesCount--;
            }
        }
        return result;
    }

    public static void main(String [] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses("()"));
        System.out.println(longestValidParentheses.longestValidParentheses("(())"));
        System.out.println(longestValidParentheses.longestValidParentheses("()(())"));
    }
}
