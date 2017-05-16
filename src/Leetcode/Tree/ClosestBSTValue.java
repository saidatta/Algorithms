package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * https://leetcode.com/problems/closest-binary-search-tree-value/#/description
 * <p>
 * Created by venkatamunnangi on 5/15/17.
 */
public class ClosestBSTValue {

    // Since it is a BST, keep binary searching through the array based on root value comparision.
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - closest)) {
                closest = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return closest;
    }
}
