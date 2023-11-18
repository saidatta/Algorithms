package leetcode.dp.string;

// https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings
public class CheckOriginalTwoEncodedStrings {
// -1000 < diff < 1000 as there can be at most 3 digits in the string meaning largest digits are 999

    /**
     * Determines if there exists an original string that could be encoded as both s1 and s2.
     *
     * @param s1 The first encoded string.
     * @param s2 The second encoded string.
     * @return true if there exists an original string that could be encoded as both s1 and s2, otherwise false.
     */
    public boolean possiblyEquals(String s1, String s2) {
        return checkPossibility(s1.toCharArray(), s2.toCharArray(), 0, 0, 0,
                new Boolean[s1.length() + 1][s2.length() + 1][2001]);
    }

    /**
     * Helper method to recursively check the possibility of encoding.
     *
     * @param s1Chars The character array of the first string.
     * @param s2Chars The character array of the second string.
     * @param i       The current index in the first string.
     * @param j       The current index in the second string.
     * @param diff    The current difference in lengths due to digit expansion.
     * @param dp      The memoization table.
     * @return true if the current state can lead to a valid encoding, otherwise false.
     */
    boolean checkPossibility(char[] s1Chars, char[] s2Chars, int i, int j, int diff, Boolean[][][] dp) {
        // Base case: if both strings are fully traversed and the difference is zero
        if (i == s1Chars.length && j == s2Chars.length) {
            return diff == 0;
        }

        // Check for memoized result
        if (dp[i][j][diff + 1000] != null) {
            return dp[i][j][diff + 1000];
        }

        // Case 1: Matching characters and no difference
        if (i < s1Chars.length && j < s2Chars.length && diff == 0 && s1Chars[i] == s2Chars[j]) {
            if (checkPossibility(s1Chars, s2Chars, i + 1, j + 1, diff, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        // Case 2: Literal character in s1 and diff > 0
        if (i < s1Chars.length && !Character.isDigit(s1Chars[i]) && diff > 0 && checkPossibility(s1Chars, s2Chars,
                i + 1, j, diff - 1, dp)) {
            return dp[i][j][diff + 1000] = true;
        }

        // Case 3: Literal character in s2 and diff < 0
        if (j < s2Chars.length && !Character.isDigit(s2Chars[j]) && diff < 0 && checkPossibility(s1Chars, s2Chars, i,
                j + 1, diff + 1, dp)) {
            return dp[i][j][diff + 1000] = true;
        }

        // Case 4: Wildcard matching in s1
        for (int k = i, val = 0; k < i + 4 && k < s1Chars.length && Character.isDigit(s1Chars[k]); k++) {
            val = val * 10 + s1Chars[k] - '0';
            if (checkPossibility(s1Chars, s2Chars, k + 1, j, diff - val, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        // Case 5: Wildcard matching in s2
        for (int k = j, val = 0; k < j + 4 && k < s2Chars.length && Character.isDigit(s2Chars[k]); k++) {
            val = val * 10 + s2Chars[k] - '0';
            if (checkPossibility(s1Chars, s2Chars, i, k + 1, diff + val, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        // If none of the above cases return true, then the current state does not lead to a valid encoding
        return dp[i][j][diff + 1000] = false;
    }

    public static void main(String[] args) {
        CheckOriginalTwoEncodedStrings solution = new CheckOriginalTwoEncodedStrings();

        // Test example: "l123e" and "44"
        String s1 = "l123e";
        String s2 = "44";
        boolean canBeEncoded = solution.possiblyEquals(s1, s2);

        System.out.println("Can \"" + s1 + "\" and \"" + s2 + "\" represent the same original string? " + canBeEncoded);
    }
}

}

/**
 * s1 = "l123e", s2 = "44", diff = 0
 * |
 * |--- (Case 1: Literal in s1, diff > 0) l -> "123e", 44, diff = -1
 * |    |
 * |    |--- (Case 4: Wildcard in s1) "123e" -> "23e", 44, diff = -2
 * |         |
 * |         |--- (Case 4: Wildcard in s1) "23e" -> "3e", 44, diff = -3
 * |              |
 * |              |--- (Case 4: Wildcard in s1) "3e" -> "e", 44, diff = 0
 * |                   |
 * |                   |--- (Case 1: Literal in s1, diff > 0) "e", 4, diff = -1
 * |                        |
 * |                        `--- (Case 2: End of s1) Return false
 * |
 * |--- (Case 5: Wildcard in s2) "l123e", "4", diff = 4
 * |
 * |--- (Case 1: Literal in s1, diff > 0) "123e", 4, diff = 3
 * |
 * |--- (Case 4: Wildcard in s1) "23e", 4, diff = 2
 * |
 * |--- (Case 4: Wildcard in s1) "3e", 4, diff = 1
 * |
 * |--- (Case 4: Wildcard in s1) "e", 4, diff = -2
 * |
 * `--- (Case 5: Wildcard in s2) "e", "", diff = 2
 * |
 * `--- (Case 2: End of s2) Return false
 *
 *
 * diff > 0 meaning we need to pick more chars in s1
 * diff < 0 meaning we need to pick more chars in s2
 *
 * -1000 < diff < 1000 as there can be at most 3 digits in the string meaning largest digits are 999
 *
 * 1. s1[i] == s2[j] and diff = 0
 *     increment i+1 and j+1
 *
 * 2. if s1[i] is not digit and diff > 0, then increment i i+1, diff
 * 3. if s2[j] is not digit and diff < 0, then increment j j+1, diff
 * 4. if s1[i] is digit then get digit value and decrement diff val as we have covered such chars in the s1 string
 *     and increment i i+1, diff-val
 * 5. if s2[j] is digit then get digit value and increment diff val as we need to cover such chars in the s2 string and
 *     increment j, j+1, diff+val
 */

