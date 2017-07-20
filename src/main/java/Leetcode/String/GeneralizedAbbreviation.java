package Leetcode.String;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkatamunnangi on 6/19/17.
 */
public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> ret = new ArrayList<>();
        backtrack(ret, word, 0, "", 0);

        return ret;
    }

    private void backtrack(List<String> ret, String word, int pos, String current, int count) {
        if (pos == word.length()) {
            if (count > 0) {
                current += count;
            }
            ret.add(current);
        } else {
            backtrack(ret, word, pos + 1, current, count + 1);
            backtrack(ret, word, pos + 1, current + (count > 0 ? count : "") + word.charAt(pos), 0);
        }
    }
}
