package Leetcode.String;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/shortest-string-that-contains-three-strings/
 */
public class ShortestStringThreeStrings {
    public static void main(String[] args) {
        ShortestStringThreeStrings s = new ShortestStringThreeStrings();
        System.out.println(s.minimumString("abc", "bca", "aaa")); // "aaabca"
        System.out.println(s.minimumString("ab", "ba", "aba"));  // "aba"
        System.out.println(s.minimumString("a", "abc", "b"));    // "abc"
    }

    public String minimumString(String a, String b, String c) {
        String[] arr = {a, b, c};
        String result = "";
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++)
                    if (i != j && i != k && j != k)
                        result = minLexicographically(result, concatMaxOverlap(arr[i], arr[j], arr[k]));
        return result;
    }

    private String concatMaxOverlap(String a, String b, String c) {
        return concatTwoMaxOverlap(a, concatTwoMaxOverlap(b, c));
    }

    private String concatTwoMaxOverlap(String a, String b) {
        if (b.contains(a)) return b;
        for (int i = Math.min(a.length(), b.length()); i >= 0; i--)
            if (a.endsWith(b.substring(0, i))) return a + b.substring(i);
        return a + b;
    }

    private String minLexicographically(String a, String b) {
        if (a.isEmpty() || (b.length() < a.length()) || (b.length() == a.length() && b.compareTo(a) < 0)) return b;
        return a;
    }
}
