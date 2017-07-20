package Leetcode.String;

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
            charCode -= (int)s.charAt(i);
            charCode += (int)t.charAt(i);
        }

        // eventually the charcodes are negated and summed up to result the missing char
        return (char)charCode;
    }
}
