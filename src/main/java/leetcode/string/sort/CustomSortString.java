package leetcode.string.sort;

// https://leetcode.com/problems/custom-sort-string/description/
public class CustomSortString {
    public String customSortString(String order, String s) {
        // count[char] = the number of occurrences of 'char' in s.
        // This is offset so that count[0] = occurrences of 'a', etc.
        // 'count' represents the current state of characters
        // (with multiplicity) we need to write to our answer.
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // ans will be our final answer.  We use StringBuilder to join
        // the answer so that we more efficiently calculate a
        // concatenation of strings.
        StringBuilder ans = new StringBuilder();

        // Write all characters that occur in order, in the order of order.
        for (char c : order.toCharArray()) {
            for (int i = 0; i < count[c - 'a']; ++i) {
                ans.append(c);
            }
            // Setting count[char] to zero to denote that we do
            // not need to write 'char' into our answer anymore.
            count[c - 'a'] = 0;
        }

        // Write all remaining characters that don't occur in order.
        // That information is specified by 'count'.
        for (char c = 'a'; c <= 'z'; ++c) {
            for (int i = 0; i < count[c - 'a']; ++i) {
                ans.append(c);
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        CustomSortString customSorter = new CustomSortString();

        String S = "cba";
        String T = "abcd";

        String result = customSorter.customSortString(S, T);

        System.out.println("Original String: " + T);
        System.out.println("Custom Sorted String: " + result);
    }
}

