package leetcode.tree.actions.delete;

import leetcode.tree.util.TreeNode;

import java.util.*;

class DeleteNodesReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int i : to_delete) {
            toDeleteSet.add(i);
        }

        List<TreeNode> remaining = new ArrayList<>();
        root = deleteNodes(root, toDeleteSet, remaining);
        if (root != null) {
            remaining.add(root);
        }
        return remaining;
    }

    private TreeNode deleteNodes(TreeNode node, Set<Integer> toDelete, List<TreeNode> remaining) {
        if (node == null) return null;

        node.left = deleteNodes(node.left, toDelete, remaining);
        node.right = deleteNodes(node.right, toDelete, remaining);

        if (toDelete.contains(node.val)) {
            if (node.left != null) remaining.add(node.left);
            if (node.right != null) remaining.add(node.right);
            return null;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        int[] to_delete = {3, 5};
        DeleteNodesReturnForest solution = new DeleteNodesReturnForest();
        List<TreeNode> remaining = solution.delNodes(root, to_delete);

        // Print the remaining trees
        for (TreeNode treeNode : remaining) {
            printTree(treeNode);
            System.out.println();
        }
    }

    private static void printTree(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printTree(node.left);
        printTree(node.right);
    }
}
