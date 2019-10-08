package Leetcode;

import java.util.Objects;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        return Objects.equals(normalizedString(S), normalizedString(T));
    }

// O(n)
    private String normalizedString(String targetString) {
        StringBuilder sb = new StringBuilder();
        int currentIndex = targetString.length() -1;
        int backspaceSkip = 0;

        while(currentIndex >= 0 ) {
            char currentChar = targetString.charAt(currentIndex);

            if(currentChar == '#') {
                backspaceSkip++;
                currentIndex--;
            } else if(backspaceSkip > 0) {
                currentIndex--;
                backspaceSkip--;
            } else {
                sb.append(targetString.charAt(currentIndex));
                currentIndex--;
            }
        }

        sb = sb.reverse();
        return sb.toString();
    }


    public boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        while (true) {
            for (int back = 0; i >= 0 && (back > 0 || S.charAt(i) == '#'); --i)
                back += S.charAt(i) == '#' ? 1 : -1;
            for (int back = 0; j >= 0 && (back > 0 || T.charAt(j) == '#'); --j)
                back += T.charAt(j) == '#' ? 1 : -1;
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--; j--;
            } else {
                return i == -1 && j == -1;
            }
        }
    }


    public static void main(String [] args) {
        BackspaceStringCompare backspaceStringCompare = new BackspaceStringCompare();
        System.out.println(backspaceStringCompare.backspaceCompare("a##c","b"));
        System.out.println(backspaceStringCompare.backspaceCompare("a##c","#a#c"));
        System.out.println(backspaceStringCompare.backspaceCompare("bxj##tw", "bxo#j##tw"));
    }
}
