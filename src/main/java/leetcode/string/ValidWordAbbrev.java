package leetcode.string;

/**
 * https://leetcode.com/problems/valid-word-abbreviation/#/description
 *
 * Created by venkatamunnangi on 5/21/17.
 */
public class ValidWordAbbrev {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }

            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                return false;
            }

            int start = j;
            while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                j++;
            }

            int num = Integer.parseInt(abbr.substring(start, j));
            i += num;
        }
        return i == word.length() && j == abbr.length();
    }
}
