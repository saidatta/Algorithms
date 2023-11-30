package leetcode.tree.traversal.bst;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
public class TwoSumIVInputBST {

    public boolean findTarget(TreeNode root, int k) {
        // HashSet to store visited values
        Set<Integer> set = new HashSet<>();

        // Use in-order traversal to visit the values
        return inorder(root, k, set);
    }

    private boolean inorder(TreeNode node, int k, Set<Integer> set) {
        // Base case
        if (node == null) {
            return false;
        }

        // Traverse the left subtree
        if (inorder(node.left, k, set)) {
            return true;
        }

        // Check if the complement exists in the set
        if (set.contains(k - node.val)) {
            return true;
        }

        // Otherwise, add the current value to the set
        set.add(node.val);

        // Traverse the right subtree
        return inorder(node.right, k, set);
    }

    // faster O(n); O(n)
    public boolean findTarget2(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k) {
                return true;
            }
            if (sum < k) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
