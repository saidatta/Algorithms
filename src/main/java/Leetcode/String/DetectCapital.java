package Leetcode.String;

/**
 * https://leetcode.com/problems/detect-capital/#/description
 *
 * Created by venkatamunnangi on 5/21/17.
 */
public class DetectCapital {
    public boolean detectCapitalUseRegex(String word) {
        if(word == null || word.length() == 0) {
            return false;
        }

        return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
    }

    public boolean detectCapitalUse(String word) {
        int numUpper = 0;
        for (int i=0;i<word.length();i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                numUpper++;
            }
        }
        if (numUpper == 1) {
            return Character.isUpperCase(word.charAt(0));
        }
        return numUpper == 0 || numUpper == word.length();
    }
}
