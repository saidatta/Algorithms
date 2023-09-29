package leetcode.tree;

import java.util.Stack;
import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/#/description
 *
 * Created by venkatamunnangi on 5/15/17.
 */
public class MinAbsoluteDiffBST {
    int min = Integer.MAX_VALUE;
    Integer prev = null;

    /**
     * The most common idea is to first inOrder traverse the tree and compare the delta between each of the adjacent values.
     * It's guaranteed to have the correct answer because it is a BST thus inOrder traversal values are sorted.
     * In-Order traverse, time complexity O(N), space complexity O(1).
     *
      * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return min;
        }

        getMinimumDifference(root.left);

        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;

        getMinimumDifference(root.right);

        return min;
    }
    public int getMinimumDifference2(TreeNode root) {
        return recursiveDiff(root);
    }

    public int recursiveDiff(TreeNode root) {
        int currMin = minDiffWithRoot(root);
        if (root.left != null) {
            currMin = Math.min(currMin, recursiveDiff(root.left));
        }
        if (root.right != null) {
            currMin = Math.min(currMin, recursiveDiff(root.right));
        }
        return currMin;
    }

//    Iterate over each node, find the minimum absolute difference for the subtree rooted on this node. Update the
//    global minimum on the way.

//   Note this is a BST, so the minimum difference between the root and a node from its subtrees is
//   MIN(root - max value of its left subtree, min value of its right subtree - root). Remember, max value of
//   its left subtree is the rightmost node in its left subtree and min value of its right subtree is the
//   leftmost node in its right subtree.
    //{root != null}
    public int minDiffWithRoot(TreeNode root) {
        int minDiff = Integer.MAX_VALUE;
        if (root.left != null) {
            TreeNode temp = root.left;
            while (temp.right != null) {  //max value < root
                temp = temp.right;
            }
            minDiff = Math.min(minDiff, root.val - temp.val);;
        }
        if (root.right != null) {
            TreeNode temp = root.right;
            while (temp.left != null) {  //min value > root
                temp = temp.left;
            }
            minDiff = Math.min(minDiff, temp.val - root.val);
        }
        return minDiff;
    }
    public int getMinimumDifferenceIterative(TreeNode root) {
        int min = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre = null;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null) {
                    min = Math.min(min, cur.val - pre.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return min;
    }
}
