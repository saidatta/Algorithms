package leetcode.dp.string;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break/?tab=Description
 *
 * Dynamic programming solution
 *
 * O (string length * dict size)
 *
 * Created by venkatamunnangi on 10/1/16.
 */
public class WordBreak {
    public boolean wordBreak(String targetString, Set<String> wordDict) {

        if (wordDict == null || targetString == null || targetString.isEmpty() || wordDict.isEmpty()) {
            return false;
        }

        boolean[] positions = new boolean[targetString.length() + 1];
        positions[0] = true;

        for (int i = 0; i < targetString.length(); i++) {
            if (!positions[i]) {
                continue;
            }

            for (String word : wordDict) {
                int wordLength = word.length();
                int wordEnd = i + wordLength;

                if (wordEnd > targetString.length()) {
                    continue;
                }

                if (targetString.substring(i, wordEnd).equals(word)) {
                    positions[wordEnd] = true;
                }
            }
        }
        return positions[targetString.length()];
    }


    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();

        Set<String> words = new HashSet<>();
        words.add("leet");
        words.add("code");

        System.out.println(wordBreak.wordBreak("leetcode", words));
    }
}
