package leetcode.tree.traversal;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/find-distance-in-a-binary-tree/
public class FindDistanceBinaryTree {
    public int findDistance(TreeNode root, int p, int q) {
        TreeNode lca = lowestCommonAncestor(root, p, q);
        return distanceFromLCA(lca, p, 0) + distanceFromLCA(lca, q, 0);
    }

    private TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    private int distanceFromLCA(TreeNode root, int val, int depth) {
        if (root == null) {
            return -1;
        }
        if (root.val == val) {
            return depth;
        }
        int left = distanceFromLCA(root.left, val, depth + 1);
        int right = distanceFromLCA(root.right, val, depth + 1);
        return left != -1 ? left : right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        FindDistanceBinaryTree solution = new FindDistanceBinaryTree();

        // Example 1: Find the distance between nodes 5 and 0
        int distance1 = solution.findDistance(root, 5, 0);
        System.out.println("Distance between nodes 5 and 0: " + distance1);

        // Example 2: Find the distance between nodes 5 and 7
        int distance2 = solution.findDistance(root, 5, 7);
        System.out.println("Distance between nodes 5 and 7: " + distance2);

        // Example 3: Find the distance between node 5 and itself
        int distance3 = solution.findDistance(root, 5, 5);
        System.out.println("Distance between node 5 and itself: " + distance3);
    }
}
