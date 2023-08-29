package leetcode;

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
                //if both words are found.
                minValue = Math.min(minValue, Math.abs(pos1-pos2));
            }
        }

        if(pos1 == -1 || pos2 == -1) {
            // word not found.
            return -1;
        }

        return minValue;
    }

    public static void main(String [] args) {
        String [] words = new String[]{"practice", "makes", "perfect", "coding", "makes"};

        String w1 = "coding",w2="practice";

        ShortestWordDistance shortestWordDistance = new ShortestWordDistance();

        System.out.println(shortestWordDistance.shortestDistance(words, w1, w2));

        String wx1 = "coding",wx2="makes";

        System.out.println(shortestWordDistance.shortestDistance(words, wx1, wx2));

    }
}
