package Leetcode.Tree;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/#/description
 *
 * Created by venkatamunnangi on 3/13/17.
 */
public class BSTPreorder {
    public boolean verifyPreorder(int[] preorder) {
        int minValue = Integer.MIN_VALUE;
        Stack<Integer> path = new Stack<>();
        for (int node : preorder) {
            if (node < minValue) {
                return false;
            }

            while (!path.empty() && node > path.peek()) {
                minValue = path.pop();
            }
            path.push(node);
        }
        return true;
    }

    public static void main(String [] args) {
        BSTPreorder bstPreorder = new BSTPreorder();

        int[] preorder = {20,10,25,1,15};
        System.out.println(bstPreorder.verifyPreorder(preorder));
    }
}
