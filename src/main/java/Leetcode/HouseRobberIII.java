package Leetcode;

/**
 * https://leetcode.com/problems/house-robber-iii
 *
 * Created by venkatamunnangi on 9/21/19.
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(robInclude(root), robExclude(root));
    }

    public int robInclude(TreeNode node) {
        if(node == null) {
            return 0;
        }
        return robExclude(node.left) + robExclude(node.right) + node.val;
    }

    public int robExclude(TreeNode node) {
        if(node == null) {
            return 0;
        }
        return rob(node.left) + rob(node.right);
    }
}
