package leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import leetcode.tree.util.TreeNode;

public class DiameterOfBinaryTreeFollowUp {
    private int diameter = 0;
    private List<Integer> diameterPath = new ArrayList<>();
    private static class Result {
        int depth;
        List<Integer> path;

        Result(int depth, List<Integer> path) {
            this.depth = depth;
            this.path = path;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(0, new ArrayList<>());
        }

        Result left = dfs(node.left);
        Result right = dfs(node.right);

        if (left.depth + right.depth > diameter) {
            diameter = left.depth + right.depth;
            List<Integer> path = new ArrayList<>(left.path);
            path.add(node.val);
            for (int i = right.path.size() - 1; i >= 0; i--) {
                path.add(right.path.get(i));
            }
            diameterPath = path;
        }

        if (left.depth > right.depth) {
            List<Integer> path = new ArrayList<>(left.path);
            path.add(node.val);
            return new Result(1 + left.depth, path);
        } else {
            List<Integer> path = new ArrayList<>(right.path);
            path.add(node.val);
            return new Result(1 + right.depth, path);
        }
    }

    public static void main(String[] args) {
        DiameterOfBinaryTreeFollowUp solution = new DiameterOfBinaryTreeFollowUp();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        root.right = new TreeNode(3);

        int result = solution.diameterOfBinaryTree(root);
        System.out.println("Diameter: " + result);
        System.out.println("Path: " + solution.diameterPath);
    }
}
