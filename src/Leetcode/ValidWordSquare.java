package Leetcode;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/valid-word-square/#/description
 * <p>
 * Created by venkatamunnangi on 4/30/17.
 */
public class ValidWordSquare {
    public boolean validWordSquare(List<String> words) {
        if (words.isEmpty()) {
            return true;
        }

        for (int i = 0; i < words.size(); i++) {
            String horizontalString = words.get(i);
            if (!horizontalString.equals(getVerticalString(i, words))) {
                return false;
            }
        }

        return true;
    }

    String getVerticalString(int column, List<String> words) {
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (column < word.length()) {
                sb.append(word.charAt(column));
            }

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ValidWordSquare validWordSquare = new ValidWordSquare();

        out.println(validWordSquare.validWordSquare(Arrays.asList("abcd", "bnrt", "crm", "dt")));
    }
}
