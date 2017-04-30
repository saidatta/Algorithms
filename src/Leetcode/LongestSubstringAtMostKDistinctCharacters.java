package Leetcode;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/#/description
 *
 * Created by venkatamunnangi on 1/11/17.
 */
public class LongestSubstringAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];     // there are 256 ASCII characters in the world

        int i = 0;  // i will be behind j
        int num = 0;
        int res = 0;

        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) {    // if count[s.charAt(j)] == 0, we know that it is a distinct character
                num++;
            }
            while (num > k && i < s.length()) {     // sliding window
                count[s.charAt(i)]--;
                if (count[s.charAt(i)] == 0){
                    // If we remove a distinct character, then we have to
                    // decrement the number of current distinct characters in rotation
                    num--;
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    public static void main(String [] args) {
        LongestSubstringAtMostKDistinctCharacters obj = new LongestSubstringAtMostKDistinctCharacters();
        System.out.println(obj.lengthOfLongestSubstringKDistinct("eceba", 2));
    }
}
