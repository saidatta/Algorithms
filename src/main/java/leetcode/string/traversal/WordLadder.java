package leetcode.string.traversal;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/#/description
 * <p>
 * Example:
 * beginWord = "hit"
 * endWord = "cog"
 * <p>
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p>
 * Created by venkatamunnangi on 4/2/17.
 */
public class WordLadder {

    //Time Complexity: (n26)^L
    //  Space Complexity: (n26)^L
    //  n = number of words in list
    //  L = length of each word in list
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert list to set for O(1) look-up times
        Set<String> wordSet = new HashSet<>(wordList);
        // Short-circuit if endWord is not in wordList
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        // Start with a length of 1 for beginWord
        int length = 1;

        while (!q.isEmpty()) {
            int numNodesThisLevel = q.size();
            for (int i = 0; i < numNodesThisLevel; i++) {
                String word = q.remove();
                char[] wordChars = word.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue;
                        }
                        wordChars[j] = c;
                        String newWord = new String(wordChars);
                        if (newWord.equals(endWord)) {
                            return length + 1;
                        }
                        if (wordSet.contains(newWord)) {
                            q.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                    // Restore original character
                    wordChars[j] = originalChar;
                }
            }
            // Increase length for each level of BFS
            length++;
        }
        // Return 0 if there is no sequence connecting beginWord to endWord
        return 0;
    }

    public static void main(String[] args) {
        WordLadder sol = new WordLadder();
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(sol.ladderLength("hit", "cog", wordList1)); // Expected output: 5

        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println(sol.ladderLength("hit", "cog", wordList2)); // Expected output: 0
    }
}
