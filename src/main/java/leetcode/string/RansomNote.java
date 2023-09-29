package leetcode.string;

/**
 * https://leetcode.com/problems/ransom-note/#/description
 *
 * Created by venkatamunnangi on 4/5/17.
 */
public class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] frequency = new int[26];

        // Populate the frequency array with counts from the magazine
        for (char ch : magazine.toCharArray()) {
            frequency[ch - 'a']++;
        }

        // Decrement counts for each character in ransomNote
        for (char ch : ransomNote.toCharArray()) {
            if (--frequency[ch - 'a'] < 0) {
                // Letter not present or exhausted in magazine
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("a", "b"));  // false
        System.out.println(canConstruct("aa", "ab"));  // false
        System.out.println(canConstruct("aa", "aab"));  // true
    }

}
