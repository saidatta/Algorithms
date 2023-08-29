package leetcode.string;

/**
 * https://leetcode.com/problems/ransom-note/#/description
 *
 * Created by venkatamunnangi on 4/5/17.
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] ch1 = ransomNote.toCharArray();
        char[] ch2 = magazine.toCharArray();

        int[] count = new int[26];
        for(char c : ch1) {
            count[c - 'a']++;
        }

        for(char c : ch2) {
            if(count[c-'a']>0) {
             count[c-'a']--;
            }
        }

        for(int i : count) {
            if(i != 0) {
                return false;
            }
        }
        return true;
    }
}
