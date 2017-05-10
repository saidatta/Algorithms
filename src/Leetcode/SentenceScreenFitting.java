package Leetcode;

import java.util.stream.IntStream;

/**
 * 418 - https://leetcode.com/problems/sentence-screen-fitting/
 * rows = 2, cols = 8, sentence = ["hello", "world"]
 * Given a rows x cols screen and a sentence represented by a list of non-empty words,
 * find how many times the given sentence can be fitted on the screen.
 * <p>
 * Created by venkatamunnangi on 1/25/17.
 */
public class SentenceScreenFitting {

    /**
     * String s = String.join(" ", sentence) + " " ;. This line gives us a formatted sentence to be put to our screen.
     *
     * start is the counter for how many valid characters from s have been put to our screen.
     *
     * (s.charAt(start % l) == ' ') is the situation that we don't need an extra space for current row.
     * The current row could be successfully fitted. So that we need to increase our counter by using start++.
     *
     * The else is the situation, which the next word can't fit to current row. So that we need to remove extra characters from next word.
     *
     * start / s.length() is (# of valid characters) / our formatted sentence.
     *
     * @param rows
     * @param cols
     * @param sentence
     * @return
     */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int len = s.length(), count = 0;
        int[] map = new int[len];
        for (int i = 1; i < len; ++i) {
            map[i] = s.charAt(i) == ' ' ? 1 : map[i-1] - 1;
        }
        for (int i = 0; i < rows; ++i) {
            count += cols;
            count += map[count % len];
        }
        return count / len;
    }

    public int wordsTyping(int rows, int cols, String[] sentence) {
        String s = String.join(" ", sentence) + " ";
        int[] offset = new int[s.length()];
        IntStream.range(1, s.length()).forEach(i -> offset[i] = s.charAt(i) == ' ' ? 1 : offset[i - 1] - 1);
        return IntStream.range(0, rows).reduce(0, (a, b) -> a + cols + offset[(a + cols) % s.length()]) / s.length();
    }

    public static void main(String... args) {
        SentenceScreenFitting sentenceScreenFitting = new SentenceScreenFitting();

        String[] words = {"hello", "world"};
        System.out.println(sentenceScreenFitting.wordsTyping(2, 8, words));
    }

}
