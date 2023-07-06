package Leetcode.DP;

/**
 * https://leetcode.com/problems/decode-ways-ii/
 */
public class DecodeWaysII {

    // Maximum value to mod against to prevent overflow
    private static final int MOD_VALUE = 1_000_000_007;

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;

        long oneBack = 1;
        long twoBack = s.charAt(0) == '0' ? 0 : (s.charAt(0) == '*' ? 9 : 1);
        long current = 0;

        for (int i=2; i<=s.length(); i++) {
            current = 0;
            char currentChar = s.charAt(i-1);
            char previousChar = s.charAt(i-2);

            if (currentChar == '*') {
                current += twoBack*9;
                current += previousChar == '*' ? oneBack*15 : (previousChar == '1' ? oneBack*9 : (previousChar == '2' ? oneBack*6 : 0));
            } else {
                current += currentChar == '0' ? 0 : twoBack;
                if (previousChar == '*') {
                    current += oneBack * (currentChar <= '6' ? 2 : 1);
                } else {
                    int twoCharValue = (previousChar - '0') * 10 + (currentChar - '0');
                    current += (twoCharValue >= 10 && twoCharValue <= 26) ? oneBack : 0;
                }
            }
            current %= MOD_VALUE;
            oneBack = twoBack;
            twoBack = current;
        }

        return (int) twoBack;
    }
}