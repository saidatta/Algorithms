package Leetcode.Array;


/**
 * https://leetcode.com/problems/shortest-word-distance-iii/
 */
public class ShortestWordDistanceIII {

    public static final int NOT_FILLED = -1;
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int pastWordIndex = NOT_FILLED;
        int min = words.length;
        for (int currentIndex = 0; currentIndex < words.length; currentIndex++) {
            if (words[currentIndex].equals(word1) || words[currentIndex].equals(word2)) {
                if (pastWordIndex != NOT_FILLED &&
                        ((word1.equals(word2) || !words[pastWordIndex].equals(words[currentIndex])))
                ) {
                    min = Math.min(currentIndex - pastWordIndex, min);
                }
                pastWordIndex = currentIndex;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        ShortestWordDistanceIII calculator = new ShortestWordDistanceIII();

//        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
//        String word1 = "makes";
//        String word2 = "coding";

        String[] words = {"a", "a", "c", "b"};
        String word1 = "a";
        String word2 = "b";

        int shortestDistance = calculator.shortestWordDistance(words, word1, word2);

        System.out.println("The shortest distance between " + word1 + " and " + word2 + " is " + shortestDistance);
    }
}