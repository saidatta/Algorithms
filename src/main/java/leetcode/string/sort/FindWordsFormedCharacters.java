package leetcode.string.sort;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
public class FindWordsFormedCharacters {
    public int countCharacters(String[] words, String chars) {
        int totalLength = 0;
        Map<Character, Integer> charsCount = new HashMap<>();

        // Count the frequency of each character in chars
        for (char c : chars.toCharArray()) {
            charsCount.put(c, charsCount.getOrDefault(c, 0) + 1);
        }

        for (String word : words) {
            if (canForm(word, new HashMap<>(charsCount))) {
                totalLength += word.length();
            }
        }

        return totalLength;
    }

    private boolean canForm(String word, Map<Character, Integer> charsCount) {
        for (char c : word.toCharArray()) {
            if (!charsCount.containsKey(c) || charsCount.get(c) == 0) {
                return false;
            }
            charsCount.put(c, charsCount.get(c) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        FindWordsFormedCharacters solution = new FindWordsFormedCharacters();
        System.out.println(solution.countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach")); // Output: 6
        System.out.println(solution.countCharacters(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr")); // Output: 10
    }
}
