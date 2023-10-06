package leetcode.tree.search;

import java.util.ArrayList;
import java.util.List;
import leetcode.tree.util.TreeNode;

public class FindLeavesBinaryTree {
    private final List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
        postOrder(root);
        return resultList;
    }

    private int postOrder(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int leftLevel = postOrder(node.left);
        int rightLevel = postOrder(node.right);

        int currentLevel = 1 + Math.max(leftLevel, rightLevel);

        if (resultList.size() == currentLevel) {
            resultList.add(new ArrayList<>());
        }

        resultList.get(currentLevel).add(node.val);

        return currentLevel;
    }

    public static void main(String[] args) {
        // Test the findLeaves method here
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        FindLeavesBinaryTree solution = new FindLeavesBinaryTree();
        List<List<Integer>> leaves = solution.findLeaves(root);
        for (List<Integer> list : leaves) {
            System.out.println(list);
        }
    }
}

//    To solve this problem, we need to traverse the tree in a bottom-up manner (post-order traversal) and at each node,
//    calculate the level at which the node should be removed. All the nodes at the same level will be removed together.
//
//        The idea is to:
//
//        For leaf nodes, assign a level of 0.
//        For non-leaf nodes, the level would be 1 + max(level of left child, level of right child).
//        Once we have the level calculated for a node, we can add it to the corresponding list in the result.
//
//        Here's the code to do this: