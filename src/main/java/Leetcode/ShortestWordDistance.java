package Leetcode;

/**
 * Created by venkatamunnangi on 12/13/16.
 */
public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        if(words == null || word1 == null || word2 == null) {
            return 0;
        }

        int pos1 = -1, pos2 = -1, minValue = Integer.MAX_VALUE;

        for(int i = 0; i< words.length; i++) {
            if(words[i].equals(word1)) {
                pos1 = i;
            }

            if(words[i].equals(word2)) {
                pos2 = i;
            }

            if(pos1 != -1 && pos2 != -1) {
                minValue = Math.min(minValue, Math.abs(pos1-pos2));
            }
        }

        if(pos1 == -1 || pos2 == -1) {
            return -1;
        }

        return minValue;
    }
}
