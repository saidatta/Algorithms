package Leetcode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/split-strings-by-separator/
 */
public class SplitStringsSeparator {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> result = new ArrayList<>();
        String separatorString = String.valueOf(separator);

        // List of special characters in regex
        List<Character> specialCharacters = Arrays.asList('.', '|', '*', '?', '+', '(', ')', '[', ']', '{', '}', '^', '$', '\\');

        if (specialCharacters.contains(separator)) {
            separatorString = "\\" + separator;
        }

        for (String word : words) {
            // Using a regex to split the strings and a filter to exclude empty strings
            result.addAll(Arrays.stream(word.split(separatorString))
                    .filter(s -> !s.isEmpty())
                    .toList());
        }

        return result;
    }

    public static void main(String[] args) {
        SplitStringsSeparator solution = new SplitStringsSeparator();

        System.out.println(solution.splitWordsBySeparator(List.of(new String[]{"one.two.three", "four.five", "six"}), '.'));
        System.out.println(solution.splitWordsBySeparator(List.of(new String[]{"$easy$", "$problem$"}), '$'));
        System.out.println(solution.splitWordsBySeparator(List.of(new String[]{"|||"}), '|'));
    }
}
