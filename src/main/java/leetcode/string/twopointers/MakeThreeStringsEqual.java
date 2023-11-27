package leetcode.string.twopointers;

// https://leetcode.com/contest/problems/make-three-strings-equal/
public class MakeThreeStringsEqual {
    public int findMinimumOperations(String s1, String s2, String s3) {
        String lcp = longestCommonPrefix(s1, s2, s3);
        if (lcp.length() == 0 && (s1.length() > 0 || s2.length() > 0 || s3.length() > 0)) {
            return -1;
        }
        return (s1.length() - lcp.length()) + (s2.length() - lcp.length()) + (s3.length() - lcp.length());
    }

    private String longestCommonPrefix(String s1, String s2, String s3) {
        int minLength = Math.min(s1.length(), Math.min(s2.length(), s3.length()));
        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(i) != s2.charAt(i) || s2.charAt(i) != s3.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        return s1.substring(0, minLength);
    }

    public static void main(String[] args) {
        MakeThreeStringsEqual solution = new MakeThreeStringsEqual();
        System.out.println(solution.findMinimumOperations("abc", "abb", "ab")); // Output: 2
        System.out.println(solution.findMinimumOperations("dac", "bac", "cac")); // Output: -1
    }
}
