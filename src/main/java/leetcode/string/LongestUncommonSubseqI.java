package leetcode.string;

/**
 * https://leetcode.com/problems/longest-uncommon-subsequence-ii/#/description
 *
 * Created by venkatamunnangi on 5/21/17.
 */
public class LongestUncommonSubseqI {
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}
