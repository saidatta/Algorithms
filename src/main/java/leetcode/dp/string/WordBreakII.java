package leetcode.dp.string;

import java.util.*;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/word-break-ii/#/description
 * <p>
 * Created by venkatamunnangi on 3/24/17.
 */
public class WordBreakII {
    /**
     * Updates the set of characters based on the input string.
     */
    private void updateCharSet(String s, HashSet<Character> charSet) {
        for (char c : s.toCharArray()) {
            charSet.add(c);
        }
    }

    /**
     * Breaks the input string into words based on the word dictionary.
     */
    public List<String> wordBreak(String input, List<String> wordDict) {
        HashSet<Character> stringCharSet = new HashSet<>();
        updateCharSet(input, stringCharSet);

        HashSet<Character> wordCharSet = new HashSet<>();
        Set<String> wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
            updateCharSet(word, wordCharSet);
        }

        // Perform a quick check on the sets of characters.
        if (!wordCharSet.containsAll(stringCharSet)) {
            return new ArrayList<>();
        }

        List<List<List<Integer>>> dp = new ArrayList<>(input.length() + 1);
        for (int i = 0; i < input.length() + 1; i++) {
            dp.add(new ArrayList<>());
        }
        dp.get(0).add(new ArrayList<>(Collections.singletonList(0)));

        for (int endIndex = 1; endIndex < input.length() + 1; endIndex++) {
            List<List<Integer>> stops = new ArrayList<>();

            // Populate the dp array.
            for (int startIndex = 0; startIndex < endIndex; startIndex++) {
                String word = input.substring(startIndex, endIndex);
                if (wordSet.contains(word)) {
                    for (List<Integer> subsentence : dp.get(startIndex)) {
                        List<Integer> copy = new ArrayList<>(subsentence);
                        copy.add(endIndex);
                        stops.add(copy);
                    }
                }
            }
            dp.set(endIndex, stops);
        }

        // Reconstruct the sentences based on the break positions.
        List<String> results = new ArrayList<>();
        for (List<Integer> stops : dp.get(input.length())) {
            StringBuilder sentence = new StringBuilder();
            for (int i = 0; i < stops.size() - 1; i++) {
                sentence.append(input, stops.get(i), stops.get(i + 1)).append(" ");
            }
            results.add(sentence.toString().trim());
        }

        return results;
    }

    public static void main(String[] args) {
        WordBreakII wordBreakII = new WordBreakII();
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        out.println(wordBreakII.wordBreak("catsanddog", dict));
    }
}
