package leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import leetcode.tree.util.TreeNode;

/**
 * Created by venkatamunnangi on 3/13/17.
 */
public class BTPreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrderList = new LinkedList<>();
        preHelper(root, preOrderList);
        return preOrderList;
    }

    public void preHelper(TreeNode root, List<Integer> preOrderList) {
        if (root == null) {
            return;
        }

        preOrderList.add(root.val);
        preHelper(root.left, preOrderList);
        preHelper(root.right, preOrderList);
    }

    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> preOrderList = new LinkedList<>();
        if (root == null) {
            return preOrderList;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            preOrderList.add(current.val);

            // Push right child first so that left child is processed first
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return preOrderList;
    }

    public static void main(String[] args) {
        // Test the iterative preorder traversal
        BTPreorderTraversal traversal = new BTPreorderTraversal();
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> result = traversal.preorderTraversal(root);
        System.out.println(result); // Expected: [1, 2, 3]
    }
}
