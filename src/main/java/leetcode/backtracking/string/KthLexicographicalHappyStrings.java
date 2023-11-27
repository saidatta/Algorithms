package leetcode.backtracking.string;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/
public class KthLexicographicalHappyStrings {
    public String getHappyString(int n, int k) {
        List<String> happyStrings = new ArrayList<>();
        generateHappyStrings(new StringBuilder(), n, happyStrings, ' ', k);
        return k <= happyStrings.size() ? happyStrings.get(k - 1) : "";
    }

    private void generateHappyStrings(StringBuilder current, int n, List<String> result, char lastChar, int k) {
        if (current.length() == n) {
            result.add(current.toString());
            return;
        }

        if (result.size() == k) {
            return;
        }

        // Consider adding 'a', 'b', and 'c', if they are different from the last added character
        for (char c : new char[] {'a', 'b', 'c'}) {
            if (c != lastChar) {
                current.append(c);
                generateHappyStrings(current, n, result, c, k);
                current.deleteCharAt(current.length() - 1); // Backtrack
            }
        }
    }
}
