package leetcode.string;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
     * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/#/description
 * <p>
 * Created by venkatamunnangi on 1/11/17.
 */
public class LongestSubstringAtMostKDistinctCharacters {
    //abcba
    //a-2
    //b-2
    //c-1
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];     // there are 256 ASCII characters in the world

        int startWindowIndex = 0;
        int currentDistinctLetters = 0;
        int maxLengthSubstring = 0;

        for (int endWindowIndex = 0; endWindowIndex < s.length(); endWindowIndex++) {
            if (count[s.charAt(endWindowIndex)]++ == 0) {
                // if count[s.charAt(j)] == 0, we know that it is a distinct character
                currentDistinctLetters++;
            }
            while (currentDistinctLetters > k && startWindowIndex < s.length()) {
                // sliding window when we encounter currentDistinctLetters > given k.
                count[s.charAt(startWindowIndex)]--;
                if (count[s.charAt(startWindowIndex)] == 0) {
                    // If we remove a distinct character, then we have to
                    // decrement the number of current distinct characters in rotation
                    currentDistinctLetters--;
                }
                startWindowIndex++;
            }
            maxLengthSubstring = Math.max(maxLengthSubstring, endWindowIndex - startWindowIndex + 1);
        }
        return maxLengthSubstring;
    }

    //A more generic solution as follows, can be solution for Unicode string:
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }
            best = Math.max(best, i - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        LongestSubstringAtMostKDistinctCharacters obj = new LongestSubstringAtMostKDistinctCharacters();
        out.println(obj.lengthOfLongestSubstringKDistinct("eceba", 2));
//        out.println(obj.lengthOfLongestSubstringKDistinct2("abcbab", 2));
    }
}
