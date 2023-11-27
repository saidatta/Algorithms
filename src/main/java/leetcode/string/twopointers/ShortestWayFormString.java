package leetcode.string.twopointers;

// https://leetcode.com/problems/shortest-way-to-form-string/
public class ShortestWayFormString {
    public int shortestWay(String source, String target) {
        int numSubsequences = 0;
        String remaining = target;

        while (remaining.length() > 0) {
            StringBuilder subsequence = new StringBuilder();
            int i = 0, j = 0;
            while (i < source.length() && j < remaining.length()) {
                if (source.charAt(i++) == remaining.charAt(j)) {
                    subsequence.append(remaining.charAt(j++));
                }
            }
            if (subsequence.length() == 0) {
                return -1; // No progress made, hence impossible
            }
            numSubsequences++;
            remaining = remaining.substring(subsequence.length());
        }

        return numSubsequences;
    }

    // Main method for testing
    public static void main(String[] args) {
        ShortestWayFormString solution = new ShortestWayFormString();
        System.out.println(solution.shortestWay("abc", "abcbc")); // Output: 2
        System.out.println(solution.shortestWay("abc", "acdbc")); // Output: -1
        System.out.println(solution.shortestWay("xyz", "xzyxz")); // Output: 3
    }
}
