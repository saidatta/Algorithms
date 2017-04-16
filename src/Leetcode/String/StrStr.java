package Leetcode.String;

/**
 * https://leetcode.com/problems/implement-strstr/#/description
 *
 * Created by venkatamunnangi on 4/5/17.
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        int l1 = haystack.length();
        int l2 = needle.length();

        if(l2 > l1) {
            return -1;
        } else if(l2 == 0) {
            return 0;
        }

        int diff = l1 - l2;
        for(int i = 0;i <= diff;i++) {
            if(haystack.substring(i, i+l2).equals(needle)) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        StrStr str = new StrStr();
        System.out.println(str.strStr("axxagg", "agg"));
    }
}
