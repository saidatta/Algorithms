package leetcode.string.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
public class SubstringConcatAllWords {

//    To solve this problem, we can use a sliding window approach along with hashing.
//
//    Here's the breakdown of the approach:
//
//    Create a hash map for the words to store their count. Let's call it wordCount.
//    Calculate wordLength which is the length of any word in words (all are of the same length) and allWordsLength
//    which is the total length of all words combined.
//    Start iterating over s. For each index i (from 0 to wordLength - 1), try to form the concatenated substring:
//    Create a currentWordCount hash map to store the count of words seen in the current window.
//    Keep track of the number of words seen so far in the current window. Let's call it wordsSeen.
//    Slide a window of size allWordsLength and check for valid concatenation.
//    Extract a word from s of size wordLength and check its count in currentWordCount. If it exceeds the count in
//    wordCount, break and move the window.
//    If the word is not in wordCount, break and move the window.
//    Otherwise, increment the currentWordCount for the word and also increment the wordsSeen.
//    If wordsSeen is equal to the number of words, then it's a valid concatenation. Add the start index of the window
//    to the result.
//    Return the result.
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words == null || words.length == 0 || s == null) return result;

        int wordLength = words[0].length();
        int allWordsLength = wordLength * words.length;
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wordLength; i++) {
            int start = i;
            while (start + allWordsLength <= s.length()) {
                Map<String, Integer> currentWordCount = new HashMap<>();
                int wordsSeen = 0;
                for (int j = start; j < start + allWordsLength; j += wordLength) {
                    String word = s.substring(j, j + wordLength);
                    if (!wordCount.containsKey(word)) {
                        break;
                    }
                    currentWordCount.put(word, currentWordCount.getOrDefault(word, 0) + 1);
                    if (currentWordCount.get(word) > wordCount.get(word)) {
                        break;
                    }
                    wordsSeen++;
                }
                if (wordsSeen == words.length) {
                    result.add(start);
                }
                start += wordLength;
            }
        }
        return result;
    }
}
