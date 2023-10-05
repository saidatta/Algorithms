package leetcode.array.traversal;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/construct-string-from-binary-tree/description/
public class ConstructStringFromBinaryTree {

    public String tree2str(TreeNode t) {
        // Base case: if the node is null, return an empty string
        if (t == null) {
            return "";
        }

        // Initialize a StringBuilder for efficient string construction
        StringBuilder sb = new StringBuilder();

        // Append the current node's value
        sb.append(t.val);

        // Recursively process the left and right subtrees
        String left = tree2str(t.left);
        String right = tree2str(t.right);

        // If either left or right is not empty, append the left tree
        if (!left.isEmpty() || !right.isEmpty()) {
            sb.append("(").append(left).append(")");
        }

        // If right is not empty, append the right tree
        if (!right.isEmpty()) {
            sb.append("(").append(right).append(")");
        }

        return sb.toString();
    }

    public class Solution {

        /**
         * Given a binary tree, converts it to a string representation.
         *
         * @param t The root of the binary tree.
         * @return A string representation of the tree.
         */
        public String tree2str(TreeNode t) {
            StringBuilder result = new StringBuilder();
            convertTreeToString(t, result);
            return result.toString();
        }

        /**
         * Recursive helper function to convert a node and its descendants into a string.
         *
         * @param node Current tree node.
         * @param result StringBuilder to which the tree's string representation is appended.
         */
        private void convertTreeToString(TreeNode node, StringBuilder result) {
            // Base case: if the node is null, no need to process further.
            if (node == null) {
                return;
            }

            // Add the value of the current node to the result.
            result.append(node.val);

            // If both left and right children are null, no need to add more to the string.
            if (node.left == null && node.right == null) {
                return;
            }

            // Process the left child.
            result.append("(");
            convertTreeToString(node.left, result);
            result.append(")");

            // Only process the right child if it's not null.
            if (node.right != null) {
                result.append("(");
                convertTreeToString(node.right, result);
                result.append(")");
            }
        }

        public String tree2str2(TreeNode t) {
            if (t == null)
                return "";

            // Using a stack to help with the DFS traversal
            Stack<TreeNode> stack = new Stack<>();
            stack.push(t);

            // A set to keep track of visited nodes.
            // This helps us know when to close the brackets in our string representation
            Set<TreeNode> visited = new HashSet<>();

            // StringBuilder to efficiently build the result string
            StringBuilder result = new StringBuilder();

            while (!stack.isEmpty()) {
                t = stack.peek();
                if (visited.contains(t)) {
                    stack.pop();
                    result.append(")"); // closing bracket for a visited node
                } else {
                    visited.add(t);
                    result.append("(").append(t.val); // starting bracket and node value

                    // If there's no left child but there's a right child, we need to append empty brackets to indicate the missing left child
                    if (t.left == null && t.right != null)
                        result.append("()");

                    // If there's a right child, add it to the stack so it can be processed next
                    if (t.right != null)
                        stack.push(t.right);

                    // If there's a left child, add it to the stack so it can be processed next
                    if (t.left != null)
                        stack.push(t.left);
                }
            }

            // Since the result has an extra opening and closing bracket, we return the substring excluding the first and last character
            return result.substring(1, result.length() - 1);
        }
    }



    public static void main(String[] args) {
        ConstructStringFromBinaryTree c = new ConstructStringFromBinaryTree();
        TreeNode example1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3));
        System.out.println(c.tree2str(example1));  // Output: "1(2(4))(3)"

        TreeNode example2 = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3));
        System.out.println(c.tree2str(example2));  // Output: "1(2()(4))(3)"
    }
}
