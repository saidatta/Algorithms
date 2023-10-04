package leetcode.tree.sorting;

import java.util.ArrayList;
import java.util.List;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/all-elements-in-two-binary-search-trees/description/
public class AllElementsInTwoBST {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // In-order traversal for both trees
        inOrder(root1, list1);
        inOrder(root2, list2);

        // Merge the two sorted lists
        return merge(list1, list2);
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                result.add(list1.get(i++));
            } else {
                result.add(list2.get(j++));
            }
        }

        // Append any remaining elements
        while (i < list1.size()) {
            result.add(list1.get(i++));
        }
        while (j < list2.size()) {
            result.add(list2.get(j++));
        }

        return result;
    }

    public static void main(String[] args) {
        // Example tree nodes
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);

        AllElementsInTwoBST solution = new AllElementsInTwoBST();
        List<Integer> result = solution.getAllElements(root1, root2);

        System.out.println(result);  // Expected: [0,1,1,2,3,4]
    }
}
