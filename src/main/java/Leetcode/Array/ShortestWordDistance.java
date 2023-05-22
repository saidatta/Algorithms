package Leetcode.Array;

/**
 * Given a list of words and two words word1 and word2,
 * return the shortest distance between these two words in the list.
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * https://leetcode.com/problems/shortest-word-distance/
 *
 * Created by venkatamunnangi on 9/7/19.
 */
public class ShortestWordDistance {

    public int shortestDistance(String[] words, String word1, String word2) {
        int leftIndex = -1;
        int rightIndex = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (word1.equals(s)) {
                leftIndex = i;
                if (rightIndex != -1) {
                    min = Math.min(min, leftIndex - rightIndex);
                }
            } else if (word2.equals(s)) {
                rightIndex = i;
                if (leftIndex != -1) {
                    min = Math.min(min, rightIndex - leftIndex);
                }
            }
        }
        return min;
    }
}
