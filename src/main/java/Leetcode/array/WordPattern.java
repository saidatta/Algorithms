package Leetcode.array;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/word-pattern/
 *
 * Created by venkatamunnangi on 9/10/19.
 */
public class WordPattern {
    public static boolean wordPattern(String pattern, String inputString) {
        String[] words = inputString.split(" ");
        HashMap<Character, String> map = new HashMap<Character, String>();
        if (words.length != pattern.length()) {
            return false;
        }

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(words[i]))
                    return false;
            } else {
                if (map.containsValue(words[i]))
                    return false;
                map.put(c, words[i]);
            }
        }
        return true;
    }

    public static void main(String [] args) {
        System.out.println(WordPattern.wordPattern("abba","cat dog dog cat"));
    }
}
