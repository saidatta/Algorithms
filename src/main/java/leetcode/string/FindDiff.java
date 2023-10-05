package leetcode.string;

/**
 * https://leetcode.com/problems/find-the-difference/#/description
 *
 * Created by venkatamunnangi on 5/15/17.
 */
public class FindDiff {
    public char findTheDifference(String s, String t) {
        int charCode = t.charAt(s.length());
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i) {
            charCode -= s.charAt(i);
            charCode += t.charAt(i);
        }

        // eventually the charcodes are negated and summed up to result the missing char
        return (char)charCode;
    }

    // alternate
    public char findTheDifference2(String s, String t) {
        char result = 0;
        for (int i = 0; i < s.length(); i++) {
            result ^= s.charAt(i);
            result ^= t.charAt(i);
        }
        result ^= t.charAt(t.length() - 1); // The extra character in t
        return result;
    }
}
