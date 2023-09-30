package leetcode.string;

// https://leetcode.com/problems/length-of-last-word/description/
public class LengthLastWord {
    public int lengthOfLastWord(String s) {
        s = s.trim();  // trim the trailing spaces in the string
        return s.length() - s.lastIndexOf(" ") - 1;
    }
}
