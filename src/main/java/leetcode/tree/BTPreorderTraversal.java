package leetcode.tree;

import leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by venkatamunnangi on 3/13/17.
 */
public class BTPreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrderList = new LinkedList<>();
        preHelper(root,preOrderList);
        return preOrderList;
    }
    public void preHelper(TreeNode root, List<Integer> preOrderList) {
        if(root==null) {
            return;
        }

        preOrderList.add(root.val);
        preHelper(root.left, preOrderList);
        preHelper(root.right, preOrderList);
    }
}
