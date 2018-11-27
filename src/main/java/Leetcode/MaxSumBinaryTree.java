package Leetcode;

public class MaxSumBinaryTree {

    public static void main(String [] args) {

    }

    public void solve(TreeNode treeNode) {
        solve(treeNode.left);
        solve(treeNode.right);


    }
}
