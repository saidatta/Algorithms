package Leetcode.String;

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
        if(leftParen == n && rightParen == n) {
            list.add(currentString);
            return;
        }

        if(leftParen < n) {
            helper(list, currentString+"(", ++leftParen, rightParen, n);
        }
        if(rightParen < leftParen) {
            helper(list, currentString+")", leftParen, ++rightParen, n);
        }
    }
}
