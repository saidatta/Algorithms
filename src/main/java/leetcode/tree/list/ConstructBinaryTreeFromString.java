package leetcode.tree.list;

import leetcode.tree.util.TreeNode;

public class ConstructBinaryTreeFromString {
    int index = 0; // Global index to track the current position in the string

    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty() || index >= s.length()) {
            return null;
        }

        // Parse the root value
        StringBuilder sb = new StringBuilder();
        if (s.charAt(index) == '-') { // Handle negative numbers
            sb.append(s.charAt(index++));
        }
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            sb.append(s.charAt(index++));
        }
        TreeNode root = new TreeNode(Integer.parseInt(sb.toString()));

        // Construct left child
        if (index < s.length() && s.charAt(index) == '(') {
            index++; // Skip the opening parenthesis
            root.left = str2tree(s); // Recursive call
            index++; // Skip the closing parenthesis
        }

        // Construct right child
        if (index < s.length() && s.charAt(index) == '(') {
            index++; // Skip the opening parenthesis
            root.right = str2tree(s); // Recursive call
            index++; // Skip the closing parenthesis
        }

        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromString solution = new ConstructBinaryTreeFromString();
        TreeNode root = solution.str2tree("4(2(3)(1))(6(5))");
        // Output tree: [4, 2, 6, 3, 1, 5]
        // Perform tree traversal like inorder, preorder, or postorder to verify the structure
        System.out.println(root);
    }
}

