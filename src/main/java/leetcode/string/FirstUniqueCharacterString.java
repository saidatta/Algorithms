package leetcode.string;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/#/description
 *
 * Created by venkatamunnangi on 4/2/17.
 */
public class FirstUniqueCharacterString {
    private static final int ALPHABET_SIZE = 26;
    private static final int NO_UNIQUE_CHAR = -1;

    public int firstUniqChar(String s) {
        int[] frequency = generateFrequencies(s);

        for(int i = 0; i < s.length(); i++) {
            if (isUniqueChar(s, frequency, i)) {
                return i;
            }
        }
        return NO_UNIQUE_CHAR;
    }

    private int[] generateFrequencies(String s) {
        int[] frequency = new int[ALPHABET_SIZE];
        for(int i = 0; i < s.length(); i ++) {
            frequency[getCharIndex(s, i)]++;
        }
        return frequency;
    }

    private int getCharIndex(String s, int i) {
        return s.charAt(i) - 'a';
    }

    private boolean isUniqueChar(String s, int[] frequency, int i) {
        return frequency[getCharIndex(s, i)] == 1;
    }
}
