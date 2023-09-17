package leetcode.greedy.string;

import java.util.Stack;

// https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
public class MinSwapsStringBalanced {
    public static int minSwaps(String s) {
        // `openBrackets` counts the number of open brackets that haven't been paired yet.
        int openBrackets = 0;

        // `unbalancedCloseBrackets` counts the number of closing brackets that haven't found a previous opening bracket.
        int unbalancedCloseBrackets = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '[') {
                openBrackets++;
            } else {
                // If there's an unmatched opening bracket, pair it with this closing bracket.
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    // If no unmatched opening bracket, this closing bracket is unbalanced.
                    unbalancedCloseBrackets++;
                }
            }
        }

        // For `n` unbalanced closing brackets, we will need `(n+1)/2` swaps to balance the string.
        // The formula is derived from the observation that each swap will fix two positions at once.
        return (unbalancedCloseBrackets + 1) / 2;
    }



    public static void main(String[] args) {
        System.out.println(minSwaps("][]["));   // Expected output: 1
        System.out.println(minSwaps("]]][[[")); // Expected output: 2
        System.out.println(minSwaps("[]"));     // Expected output: 0
    }

}
