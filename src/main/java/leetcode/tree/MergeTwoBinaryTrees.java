package leetcode.tree;

import leetcode.TreeNode;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/description/
 *
 * Created by venkatamunnangi on 8/16/17.
 */
public class MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null&&t2==null) {
            return null;
        }

        if(t2 == null) {
            return t1;
        } else if(t1 == null){
            return t2;
        }



        TreeNode node = new TreeNode(t1.val+t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);

        return node;
    }
}
