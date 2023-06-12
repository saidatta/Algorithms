package Leetcode.Tree;

import Leetcode.TreeNode;

public class CountUnivalueBinaryTree {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && root.val != root.left.val) return false;
        if (root.right != null && root.val != root.right.val) return false;
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }

//    public boolean isUnivalTree(TreeNode root) {
//        if (root == null) return true;
//
//        int val = root.val;
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//
//        while (!stack.empty()) {
//            TreeNode node = stack.pop();
//            if (node.val != val) return false;
//            if (node.right != null) stack.push(node.right);
//            if (node.left != null) stack.push(node.left);
//        }
//        return true;
//    }
}
