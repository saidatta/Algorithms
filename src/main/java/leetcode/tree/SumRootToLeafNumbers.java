package leetcode.tree;

import leetcode.TreeNode;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/#/description
 *
 * Created by venkatamunnangi on 3/12/17.
 */
public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root, 0);
    }

    public int sumNumbersHelper(TreeNode n, int sum) {
        if (n == null) {
            return 0;
        }

        if (n.right == null && n.left == null) {
            return sum * 10 + n.val;
        }
        return sumNumbersHelper(n.left, sum * 10 + n.val) + sumNumbersHelper(n.right, sum * 10 + n.val);
    }
}
