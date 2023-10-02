package leetcode.tree.traversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/#/description
 *
 * Created by venkatamunnangi on 3/14/17.
 */
public class BTPostorder {
    /**
     * Postorder traversal of a binary tree using a single stack.
     * Postorder traversal order: Left, Right, Root.
     *
     * @param root The root of the binary tree.
     * @return A list containing nodes' values in postorder traversal order.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // LinkedList allows efficient insert operations at the beginning.
        LinkedList<Integer> traversalResult = new LinkedList<>();

        // Base case: if the tree is empty, return an empty list
        if (root == null) {
            return traversalResult;
        }

        // Stack to store nodes for processing
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);

        // Process nodes
        while (!nodeStack.isEmpty()) {
            TreeNode currentNode = nodeStack.pop();

            // Instead of adding values to the end, we add them to the front
            // This way, we're effectively reversing Right, Root, Left order to get Left, Right, Root.
            traversalResult.addFirst(currentNode.val);

            // Push left child first, so it's processed after the right child
            if (currentNode.left != null) {
                nodeStack.push(currentNode.left);
            }

            // Push right child
            if (currentNode.right != null) {
                nodeStack.push(currentNode.right);
            }
        }

        return traversalResult;
    }

    public static void main(String[] args) {
        BTPostorder btPostorder = new BTPostorder();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1000);

        System.out.println(btPostorder.postorderTraversal(root));
    }
}
