package Leetcode.DP;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/regular-expression-matching/?tab=Description
 * Dynamic Programming solution
 *
 * Created by venkatamunnangi on 10/2/16.
 */
public class RegularExpressionMatching {
    char ANY_OCCURENCES = '*';
    char ANY_CHAR = '.';
    public boolean isMatch(String str, String regex) {
        boolean[][] dp = new boolean[str.length() + 1][regex.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < regex.length() + 1; i++) {
            if (regex.charAt(i - 1) == ANY_OCCURENCES) {
                /// Sets the first horizontal row of the regex pattern.
                /// Meant to detect the *'s in the pattern.
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (match(str.charAt(i - 1), regex.charAt(j - 1))) {
                    /*
                     * if it matches the char in string and regex, or it char in string is '.'.
                     * Then, the set the value of the previous word. dp[i-1][j-1].
                     */

                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (regex.charAt(j - 1) == ANY_OCCURENCES) {
                        /*
                         * If the char pattern is *, then we have to check for the current char matches with
                         *  regex char or '.'.
                         *
                         *  - if it does, then it will be status of previous char in word, there are
                         *    multiple same chars. (dp[i-1][j]
                         *  - if it doesn't, then it will be status of char before '<current-char>*' -
                          *  so there is 0 chars (dp[i][j-2])
                         */
                        /// this is prep for or statement.
                        dp[i][j] = dp[i][j - 2];
                        // since star is 0 or anything, it searches for the status of word before the *'s
                        // associated character and copies itself.
                        if (match(str.charAt(i - 1), regex.charAt(j - 2))) {
                            // dp[i][j] - true - means only one or more a's
                            // dp[i-1][j] - true - means no a's so we are getting status of previous char.
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }
        }

        return dp[str.length()][regex.length()];
    }

    private boolean match(char c1, char r) {
        return c1 == r || r == ANY_CHAR;
    }


    public static void main(String [] args) {
        RegularExpressionMatching exp = new RegularExpressionMatching();
        out.println(exp.isMatch("aab", "c*a*b"));
        out.println(exp.isMatch("aab", "ca*b"));
        out.println(exp.isMatch("aa", ".*"));
        out.println(exp.isMatch("acca", ".*"));
    }
}
