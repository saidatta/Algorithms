package dailyCodingProblem;

import leetcode.tree.util.TreeNode;

import static java.lang.System.out;

/**
 * This problem was asked by Dropbox.
 * Given the root to a binary search tree, find the second largest node in the tree.
 *
 * Created by venkatamunnangi on 9/29/19.
 */
public class Problem36 {

//    Time complexity of the above solution is O(h) where h is height of BST.
    public TreeNode secondLargest(TreeNode root) {


        TreeNode solution = null;
        helper(root, 0, solution);

        return solution;
    }

    void helper(TreeNode root, Integer totalNodeCount, TreeNode solution) {
        if(root == null || totalNodeCount == 2) {
            return;
        }


        if(root.right != null) {
            helper(root.right, totalNodeCount, solution);
        }

        totalNodeCount += 1;
        if (totalNodeCount == 2) {
            out.print("2nd largest element is "+
                    root.val);
            solution = root;
        }

        if(root.left != null) {
            helper(root.right, totalNodeCount, solution);
        }
    }

}
