package leetcode.stack;

import java.util.LinkedList;

public class RemoveKDigits {
//    Time complexity : O(N)\mathcal{O}(N)O(N). Although there are nested loops, the inner loop is bounded to be run at
//    most kkk times globally. Together with the outer loop, we have the exact (N+k)(N + k)(N+k) number of operations.
//    Since 0<k≤N0 < k \le N0<k≤N, the time complexity of the main loop is bounded within 2N2N2N.

    //    For the logic outside the main loop, it is clear to see that their time complexity is O(N)\mathcal{O}(N)O(N). As
//    a result, the overall time complexity of the algorithm is O(N)\mathcal{O}(N)O(N).
//
//   Space complexity : O(N)\mathcal{O}(N)O(N). We have a stack which would hold all the input digits in the worst case.
    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();

        //monotonically increasing stack.
        for(char digit : num.toCharArray()) {
            while(stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k--;
            }
            stack.addLast(digit);
        }

        /* remove the remaining k digits from the tail. */
        for(int i=0; i<k; ++i) {
            stack.removeLast();
        }

        // build the final string, while removing the leading zeros.
        StringBuilder result = new StringBuilder();
        boolean leadingZero = true;
        for(char digit: stack) {
            if(leadingZero && digit == '0') continue;
            leadingZero = false;
            result.append(digit);
        }

        /* return the final string  */
        if (result.length() == 0) return "0";
        return result.toString();
    }
}
