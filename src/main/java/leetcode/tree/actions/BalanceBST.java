package leetcode.tree.actions;

import java.util.ArrayList;
import java.util.List;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/balance-a-binary-search-tree/

//To balance a Binary Search Tree (BST), one common approach is to:
//
// Convert the given BST into a sorted list (in-order traversal).
// Convert the sorted list into a height-balanced BST.
// Step 1: In-order traversal ensures the elements are in sorted order.
// Step 2: Recursively pick the middle element to be the root and then repeat the process for the left and right sublists
public class BalanceBST {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortedList = new ArrayList<>();
        inorderTraversal(root, sortedList);
        return sortedListToBST(sortedList, 0, sortedList.size() - 1);
    }

    private void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    private TreeNode sortedListToBST(List<Integer> list, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(list.get(mid));

        node.left = sortedListToBST(list, start, mid - 1);
        node.right = sortedListToBST(list, mid + 1, end);

        return node;
    }

    public static void main(String[] args) {
        // Test the code
        BalanceBST solution = new BalanceBST();
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));
        TreeNode balancedRoot = solution.balanceBST(root);
        printInOrder(balancedRoot);
    }

    public static void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.val + " ");
            printInOrder(root.right);
        }
    }
}
