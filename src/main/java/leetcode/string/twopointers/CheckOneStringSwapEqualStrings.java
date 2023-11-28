package leetcode.string.twopointers;

// https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
public class CheckOneStringSwapEqualStrings {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }

        int firstDiff = -1, secondDiff = -1;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (firstDiff == -1) {
                    firstDiff = i;
                } else if (secondDiff == -1) {
                    secondDiff = i;
                } else {
                    return false; // More than two differences
                }
            }
        }

        // Check for a valid swap that can make the strings equal
        return secondDiff != -1 && s1.charAt(firstDiff) == s2.charAt(secondDiff)
                && s1.charAt(secondDiff) == s2.charAt(firstDiff);
    }

    public static void main(String[] args) {
        CheckOneStringSwapEqualStrings solution = new CheckOneStringSwapEqualStrings();
        System.out.println(solution.areAlmostEqual("bank", "kanb"));  // Output: true
        System.out.println(solution.areAlmostEqual("attack", "defend"));  // Output: false
        System.out.println(solution.areAlmostEqual("kelb", "kelb"));  // Output: true
    }
}
