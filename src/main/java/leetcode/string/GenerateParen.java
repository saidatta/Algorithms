package leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/#/description
 *
 * Write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
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

    private void helper(List<String> list, String currentString, int leftParen, int rightParen, int n) {
        if (currentString.length() == n * 2) {
            list.add(currentString);
            return;
        }

        if(leftParen < n) {
            helper(list, currentString+"(", leftParen+1, rightParen, n);
        }
        if(rightParen < leftParen) {
            helper(list, currentString+")", leftParen, rightParen+1, n);
        }
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<String>();
        recurse(res, 0, 0, "", n);
        return res;
    }

    public void recurse(List<String> res, int left, int right, String s, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }

        if (left < n) {
            recurse(res, left + 1, right, s + "(", n);
        }

        if (right < left) {
            recurse(res, left, right + 1, s + ")", n);
        }
    }
}
