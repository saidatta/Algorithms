package leetcode.string;

/**
 * https://leetcode.com/problems/count-binary-substrings/
 */
public class CountBinarySubstring {
    public int countBinarySubstrings(String s) {
        int currentConsecutiveCount = 1;
        int lastConsecutiveCount = 0;
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currentConsecutiveCount++;
            } else {
                result += Math.min(lastConsecutiveCount, currentConsecutiveCount);
                lastConsecutiveCount = currentConsecutiveCount;
                currentConsecutiveCount = 1;

            }
        }
        return result + Math.min(lastConsecutiveCount, currentConsecutiveCount);
    }
}
