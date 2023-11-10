package leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
public class FindLargestValueEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);


        while(!queue.isEmpty()) {
            int currMax = Integer.MIN_VALUE;
            int rowSize = queue.size();

            for(int i = 0; i < rowSize; i++) {
                TreeNode currentNode = queue.remove();
                currMax = Math.max(currMax, currentNode.val);

                if(currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if(currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            result.add(currMax);
        }

        return result;
    }
}
