package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * Created by venkatamunnangi on 3/1/17.
 */
public class KthSmallestBST {
    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if(k <= count) { // if k is in the left subtree.
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            // if k is in the right subtree.
            // count + 1 because of left subtree and root element.
            return kthSmallest(root.right, k - count - 1);
        }

        // if k == count + 1
        return root.val;
    }

    private int countNodes(TreeNode n) {
        if (n == null) {
            return 0;
        }

        return 1 + countNodes(n.left) + countNodes(n.right);
    }


    public static void main(String [] args) {
        KthSmallestBST kthSmallestBST = new KthSmallestBST();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        System.out.println(kthSmallestBST.kthSmallest(root, 2));

    }
}
