package leetcode.string;

/**
 * https://leetcode.com/problems/reverse-string-ii/#/description
 *
 * Given a string and an integer k, you need to reverse the first k characters for
 * every 2k characters counting from the start of the string. If there are less than
 * k characters left, reverse all of them. If there are less than 2k but greater than
 * or equal to k characters, then reverse the first k characters and left the other as original.
 *
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 *
 * Created by venkatamunnangi on 5/15/17.
 */
public class ReverseStringII {
    public String reverseStr(String s, int k) {
        if(s == null || s.isEmpty()) {
            return "";
        }

        char[] cArray = s.toCharArray();
        int n = s.length();
        int i = 0;

        while(i < n) {
            int j = Math.min(i + k - 1, n - 1);
            swap(i, j, cArray);
            i += 2*k;
        }

        return String.valueOf(cArray);
    }

    public void swap(int l, int r, char[] cArray) {
        while(l < r) {
            char temp = cArray[l];
            cArray[l++] = cArray[r];
            cArray[r--] = temp;
        }
    }
}
