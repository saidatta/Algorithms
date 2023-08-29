package leetcode.tree;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 366 - https://leetcode.com/problems/find-leaves-of-binary-tree/#/description
 *
 * Given a binary tree, collect a tree's nodes as if you were doing this:
 * Collect and remove all leaves, repeat until the tree is empty.
 *
 * Created by venkatamunnangi on 12/16/16.
 */
/**
 *           1
            / \
            2   3
            / \
          4   5
 */
public class LeavesBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        findLeavesHelper(list, root);
        return list;
    }

    // return the level of root
    private int findLeavesHelper(List<List<Integer>> list, TreeNode root) {
        if(root == null) {
            return -1;
        }

        int leftLevel = findLeavesHelper(list, root.left);
        int rightLevel = findLeavesHelper(list, root.right);

        int level = Math.max(leftLevel, rightLevel) + 1;
        if(list.size() == level) {
            list.add(new ArrayList<>());
        }

        list.get(level).add(root.val);
        root.left = null;
        root.right = null;

        return level;
    }
}
