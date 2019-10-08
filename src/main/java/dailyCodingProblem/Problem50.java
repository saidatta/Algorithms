package dailyCodingProblem;

import Leetcode.TreeNode;

/**
 *
 * https://www.dailycodingproblem.com/solution/50?token=a7bf6c85806a3f4bd114e4c7d8b6b763abccc4c10e6a447891b711dc5c5613060e6bf939
 *
 * Created by venkatamunnangi on 9/28/19.
 */
public class Problem50 {

//            PLUS = "+"
//    MINUS = "-"
//    TIMES = "*"
//    DIVIDE = "/"
//    def evaluate(root):
//            if root.val == PLUS:
//            return evaluate(root.left) + evaluate(root.right)
//    elif root.val == MINUS:
//            return evaluate(root.left) - evaluate(root.right)
//    elif root.val == TIMES:
//            return evaluate(root.left) * evaluate(root.right)
//    elif root.val == DIVIDE:
//            return evaluate(root.left) / evaluate(root.right)
//    else:
//            return root.val

//    This runs in O(N) time and space.
    String PLUS = "+";
    String MINUS = "-";
    String TIMES = "*";
    String DIVIDE = "/";

    public String evaluate(TreeNode root) {
        if (PLUS.equals(root.val)) {
            return String.valueOf(Integer.parseInt(evaluate(root.left)) + Integer.parseInt(evaluate(root.right)));
        } else if (MINUS.equals(root.val)) {
            return String.valueOf(Integer.parseInt(evaluate(root.left)) - Integer.parseInt(evaluate(root.right)));
        } else if (TIMES.equals(root.val)) {
            return String.valueOf(Integer.parseInt(evaluate(root.left)) * Integer.parseInt(evaluate(root.right)));
        } else if (DIVIDE.equals(root.val)) {
            return String.valueOf(Integer.parseInt(evaluate(root.left)) / Integer.parseInt(evaluate(root.right)));
        } else {
            return String.valueOf(root.val);
        }
    }

    public static void main(String [] args) {
//        Problem50 problem50 = new Problem50();
//
//        System.out.print
    }
}
