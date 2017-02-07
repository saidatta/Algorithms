package Leetcode;

/**
 * 418
 * Created by venkatamunnangi on 1/25/17.
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        if(sentence == null ) {
            return 0;
        }

        String spacedSentence = String.join(" ", sentence) + " ";

        int[] dict =new int[spacedSentence.length()];

        for(int i = 0; i<dict.length;i++) {
            dict[i] = spacedSentence.charAt(i) == ' ' ? 1 : dict[i-1]-1; // can be space before or character before.
        }

        int c = 0;
        for(int i = 0; i< rows;i++) {
            c += cols;
            c += dict[c % dict.length];
        }

        return c / dict.length;

    }

}
