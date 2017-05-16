package Leetcode.Tree;

import Leetcode.TreeNode;

import java.util.TreeSet;

/**
 * Created by venkatamunnangi on 5/15/17.
 */
public class MinAbsoluteDiffBT {
    TreeSet<Integer> treeSet = new TreeSet<>();
    int min = Integer.MAX_VALUE;

    // pre-order traversal.
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return min;
        }

        if (!treeSet.isEmpty()) {
            if (treeSet.floor(root.val) != null) {
                min = Math.min(min, root.val - treeSet.floor(root.val));
            }
            if (treeSet.ceiling(root.val) != null) {
                min = Math.min(min, treeSet.ceiling(root.val) - root.val);
            }
        }

        treeSet.add(root.val);

        getMinimumDifference(root.left);
        getMinimumDifference(root.right);

        return min;
    }
}
