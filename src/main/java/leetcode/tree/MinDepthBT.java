package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/#/description
 *
 * Created by venkatamunnangi on 3/12/17.
 */
public class MinDepthBT {
    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        if(root.left == null) {
            return minDepth(root.right) +1;
        }

        if(root.right == null) {
            return minDepth(root.left) + 1;
        }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

//    BFS is a superior solution here.
//
//    BFS will stop the moment we reach the level of min-depth. DFS will continue searching all the way down. On
//    average, for randomly generated trees, the min-depth will be much smaller than the max-depth, so BFS will access
//    fewer nodes.
//
//    BFS's queue takes less memory than a call stack which has to remember the functions' params local variables,
//    return address etc. This can lead to a stack overflow error as memory consumption increases beyond its limits.
//
//    DFS is calling the function recursively, and popular languages like Python, JavaScript have a max recursion depth
//    of 1000. Hence, it cannot solve a tree with 1001 right nodes.

    /**
     * This method calculates the minimum depth of a binary tree using Breadth First Search (BFS).
     *
     * @param root Root node of the binary tree.
     * @return The minimum depth of the tree. If the tree is empty, it returns 0.
     */
    public int minDepthBFS(TreeNode root) {
        // If the tree is empty, return 0.
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();

            // Process all nodes of the current level.
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode currentNode = queue.poll();

                // A leaf node is a node without left or right children.
                // Since we're doing BFS, the first leaf we encounter will be at the minimum depth.
                if (currentNode.left == null && currentNode.right == null) {
                    return depth;
                }

                // Add non-null children to the queue.
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            // Move on to the next level.
            depth++;
        }

        // This return statement is technically unreachable because there's always at least one leaf in a non-empty tree.
        // However, it's good to keep it to satisfy the method's contract.
        return -1;
    }
}
