package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/#/description
 *
 * Created by venkatamunnangi on 4/3/17.
 */
public class GenerateParen {
    public List<String> generateParenthesis(int n) {
        if(n <= 0) {
            return new ArrayList<>();
        }

        List<String> al = new ArrayList<>();
        helper(al,"",0,0,n );
        return al;
    }

    private void helper(List<String> list, String curr, int oc, int cc, int n) {
        if(oc == n && cc == n) {
            list.add(curr);
            return;
        }

        if(oc < n) {
            helper(list, curr+"(", ++oc, cc, n);
        }
        if(cc < n) {
            helper(list, curr+")", oc, ++cc, n);
        }
    }
}
