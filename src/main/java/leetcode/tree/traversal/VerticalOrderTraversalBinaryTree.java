package leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
import leetcode.tree.util.TreeNode;

/**
 * A helper class to store a tree node along with its corresponding row and column.
 */
class IndexedNode {
    int row, col;
    TreeNode treeNode;

    IndexedNode(int row, int col, TreeNode treeNode) {
        this.row = row;
        this.col = col;
        this.treeNode = treeNode;
    }
}

/**
 * Comparator to sort the IndexedNode based on row and then by value if rows are same.
 */
class IndexedNodeComparator implements Comparator<IndexedNode> {
    public int compare(IndexedNode node1, IndexedNode node2) {
        if (node1.row == node2.row) {
            return node1.treeNode.val - node2.treeNode.val;
        }
        return node1.row - node2.row;
    }
}

public class VerticalOrderTraversalBinaryTree {

    /**
     * Returns a vertical traversal of the tree.
     *
     * @param root Root node of the tree.
     * @return List of lists containing the vertical traversal.
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // TreeMap to keep the columns in order.
        Map<Integer, List<Integer>> valuesByColumn = new TreeMap<>();

        // Priority Queue to process nodes in order.
        PriorityQueue<IndexedNode> queue = new PriorityQueue<>(new IndexedNodeComparator());
        queue.offer(new IndexedNode(0, 0, root));

        while (!queue.isEmpty()) {
            IndexedNode currentNode = queue.poll();
            valuesByColumn
                    .computeIfAbsent(currentNode.col, k -> new ArrayList<>())
                    .add(currentNode.treeNode.val);

            if (currentNode.treeNode.left != null) {
                queue.offer(new IndexedNode(currentNode.row + 1, currentNode.col - 1, currentNode.treeNode.left));
            }
            if (currentNode.treeNode.right != null) {
                queue.offer(new IndexedNode(currentNode.row + 1, currentNode.col + 1, currentNode.treeNode.right));
            }
        }

        return new ArrayList<>(valuesByColumn.values());
    }
}

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        // HashMap to store nodes by column index
        Map<Integer, List<Integer>> colTable = new HashMap<>();
        Map<TreeNode, Integer> nodeToCol = new HashMap<>();

        // Using BFS
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        nodeToCol.put(root, 0);

        int minCol = 0, maxCol = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int col = nodeToCol.get(node);

            if (!colTable.containsKey(col)) {
                colTable.put(col, new ArrayList<>());
            }

            colTable.get(col).add(node.val);

            if (node.left != null) {
                queue.add(node.left);
                nodeToCol.put(node.left, col - 1);
                minCol = Math.min(minCol, col - 1);
            }

            if (node.right != null) {
                queue.add(node.right);
                nodeToCol.put(node.right, col + 1);
                maxCol = Math.max(maxCol, col + 1);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = minCol; i <= maxCol; i++) {
            result.add(colTable.get(i));
        }

        return result;
    }
}