package Leetcode.Tree;

import Leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LC 366
 * Created by venkatamunnangi on 12/16/16.
 */

/**
 * 1
 * 1,2
 * given example
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
