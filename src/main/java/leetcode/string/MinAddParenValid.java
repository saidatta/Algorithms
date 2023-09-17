package leetcode.string;

// https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/
public class MinAddParenValid {
    public int minAddToMakeValid(String s) {
        int open = 0, close = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (open > 0) {
                open--;
            } else {
                close++;
            }
        }
        return open + close;
    }
}
