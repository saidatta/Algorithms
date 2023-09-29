package leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import leetcode.tree.util.TreeNode;

public class BTInorderTraversal {

    /**
     * Returns the inorder traversal of a binary tree.
     *
     * @param root The root of the binary tree.
     * @return List of integers in inorder.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        pushLeftNodes(stack, root);

        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            result.add(currentNode.val);
            pushLeftNodes(stack, currentNode.right);
        }

        return result;
    }

    /**
     * Pushes all the left nodes of the given node into the stack.
     *
     * @param stack The stack to store nodes.
     * @param node The starting node.
     */
    private void pushLeftNodes(Stack<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}