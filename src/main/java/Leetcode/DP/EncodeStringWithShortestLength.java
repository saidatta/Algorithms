package Leetcode.DP;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/encode-string-with-shortest-length/#/description
 *
 * Input: "abbbabbbcabbbabbbc"
 * Output: "2[2[abbb]c]"
 *
 * * Created by venkatamunnangi on 5/10/17.
 */
public class EncodeStringWithShortestLength {
    public String encode(String s) {
        int stringLength = s.length();
        String[][] dp = new String[stringLength][stringLength];
        for (int j = 0; j < stringLength; ++j) {
            int i = j;
            dp[i][j] = s.substring(j, j + 1);
            for (int p = 0; p < i; ++p) {
                dp[p][j] = dp[p][j - 1] + dp[i][j];
            }
            for (i = j - 1; i + 1 >= j - i; --i) {
                String sub = s.substring(i + 1, j + 1); // s[i+1..j]
                for (int k = i - (j - i) + 1; k >= 0 && sub.equals(s.substring(k, k + j - i)); k -= j - i) {
                    String str = Integer.toString((j + 1 - k) / (j - i)) + "[" + dp[i + 1][j] + "]";
                    if (str.length() < dp[k][j].length()) {
                        dp[k][j] = str;
                        for (int p = 0; p < k; ++p) {
                            if (dp[p][k - 1].length() + str.length() < dp[p][j].length()) {
                                dp[p][j] = dp[p][k - 1] + str;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][stringLength - 1];
    }

    public static void main(String... args) {
        EncodeStringWithShortestLength encodeStringWithShortestLength = new EncodeStringWithShortestLength();
        out.println(encodeStringWithShortestLength.encode("aaaaaa"));
    }
}
