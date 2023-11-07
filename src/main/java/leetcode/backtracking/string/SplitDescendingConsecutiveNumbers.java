package leetcode.backtracking.string;

public class SplitDescendingConsecutiveNumbers {
    public boolean splitString(String s) {
        return canSplit(s, 0, -1);
    }

    private boolean canSplit(String s, int start, long previous) {
        if (start == s.length()) {
            return false;
        }

        long num = 0;
        for (int i = start; i < s.length(); i++) {
            num = num * 10 + (s.charAt(i) - '0');

            if (previous == -1) { // This is the first number
                if (canSplit(s, i + 1, num)) {
                    return true;
                }
            } else if (previous - num == 1) { // Found a descending number
                if (i == s.length() - 1 || canSplit(s, i + 1, num)) {
                    return true;
                }
            }
        }

        return false;
    }
}
