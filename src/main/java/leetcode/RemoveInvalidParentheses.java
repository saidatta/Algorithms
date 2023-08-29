package leetcode;

import java.util.*;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/#/description
 *
 * Created by venkatamunnangi on 3/23/17.
 */
public class RemoveInvalidParentheses {

    /**
     * In BFS we handle the states level by level, in the worst case, we need to handle all the levels,
     * we can analyze the time complexity level by level and add them up to get the final complexity.
     *
     * On the first level, there's only one string which is the input string s, let's say the length of it is n,
     * to check whether it's valid, we need O(n) time. On the second level, we remove one ( or ) from the first level,
     * so there are C(n, n-1) new strings, each of them has n-1 characters, and for each string, we need to check whether
     * it's valid or not, thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level,
     * total time complexity is (n-2) x C(n, n-2), so on and so forth...
     *
     * Finally we have this formula:
     * T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
     * @param inputString
     * @return
     */
    public List<String> removeInvalidParentheses(String inputString) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (inputString == null) {
            return res;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(inputString);
        visited.add(inputString);

        boolean found = false;

        while (!queue.isEmpty()) {
            String currentQueuedString = queue.poll();
            if (isValid(currentQueuedString)) {
                // found an answer, add to the result
                res.add(currentQueuedString);
                found = true;
            }

            if (found) {
                // this ensures once we've found a valid parentheses pattern, we don't do any further bfs using items
                // pending in the queue since any further bfs would only yield strings of smaller length.
                // However the items already in queue need to be processed since there could be other solutions of the
                // same length.
                continue;
            }

            // generate all possible states
            for (int i = 0; i < currentQueuedString.length(); i++) {
                // we only try to remove left or right paren
                if (currentQueuedString.charAt(i) != '(' && currentQueuedString.charAt(i) != ')') {
                    continue;
                }

                String t = currentQueuedString.substring(0, i) + currentQueuedString.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it'inputString not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    private boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            }
            if (c == ')' && count-- == 0) {
                return false;
            }
        }

        return count == 0;
    }

    public List<String> removeInvalidParentheses2(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    /**
     *  Key Points:
     * Generate unique answer once and only once, do not rely on Set.
     * Do not need preprocess.
     *
     * Explanation:
     * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
     * The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative,
     * we have more ‘)’ than ‘(‘ in the prefix.
     * To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one
     * in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()),
     * we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the
     * first ) in a series of concecutive )s.
     *
     * After the removal, the prefix is then valid. We then call the function recursively to solve the
     * rest of the string. However, we need to keep another information: the last removal position.
     * If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only
     * with a different order.
     * For this, we keep tracking the last removal position and only remove ‘)’ after that.
     *
     * Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
     * The answer is: do the same from right to left.
     * However a cleverer idea is: reverse the string and reuse the code!
     *
     * @param s
     * @param ans
     * @param last_i
     * @param last_j
     * @param par
     */

    public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) {
                stack++;
            }
            if (s.charAt(i) == par[1]) {
                stack--;
            }
            if (stack >= 0) {
                continue;
            }
            for (int j = last_j; j <= i; ++j) {
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
                }
            }
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }




    public String ss(String s) {
        if(s == null || s.isEmpty()) {
            return "";
        }
        //())

        Queue<String> q = new LinkedList<>();
        q.offer(s);

        List<String> visited = new ArrayList<>();
        while(!q.isEmpty()) {
            String curr = q.poll();
            if(isValidParen(curr)) {
                return curr;
            }

            for(int i = 0; i < curr.length(); i++) {
                char c = curr.charAt(i);
                if(c != '(' && c != ')') {
                    continue;
                }

                // delete character at i
                String truncated = curr.substring(0, i) + curr.substring(i+1);

                if(!visited.contains(truncated)) {
                    visited.add(truncated);
                    q.offer(truncated);
                }
            }
        }

        return "";
    }

    private boolean isValidParen(String s) {
        if(s == null || s.isEmpty()) {
            return false;
        }

        int count = 0;
        for(int i = 0; i<s.length();i++) {
            if(s.charAt(i) == '(') {
                count++;
            }

            if(s.charAt(i) == ')' && count--==0) {
                count--;
            }
        }

        return count == 0;
    }



    public static void main(String [] args) {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        out.println(removeInvalidParentheses.ss("(()(()"));
        out.println(removeInvalidParentheses.removeInvalidParentheses("()())()"));
        out.println(removeInvalidParentheses.removeInvalidParentheses("(a)())()"));
        out.println(removeInvalidParentheses.removeInvalidParentheses(")("));
    }
}
