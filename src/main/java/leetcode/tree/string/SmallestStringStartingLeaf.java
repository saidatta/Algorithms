package leetcode.tree.string;

import leetcode.TreeNode;

public class SmallestStringStartingLeaf {
    private String smallestString = null;

    public String smallestFromLeaf(TreeNode root) {
        if (root == null) {
            return "";
        }
        traverseTree(root, new StringBuilder());
        return smallestString;
    }

    private void traverseTree(TreeNode node, StringBuilder currentPath) {
        currentPath.insert(0, (char) ('a' + node.val));

        if (node.left == null && node.right == null) {
            String currentString = currentPath.toString();
            if (smallestString == null || smallestString.compareTo(currentString) > 0) {
                smallestString = currentString;
            }
        } else {
            if (node.left != null) {
                traverseTree(node.left, currentPath);
            }
            if (node.right != null) {
                traverseTree(node.right, currentPath);
            }
        }
        currentPath.deleteCharAt(0);
    }
}


