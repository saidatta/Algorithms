package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
// The code defines a struct NodeWParent that inherits from TreeNode to add a parent pointer. The Solution class converts
// the tree nodes to NodeWParent objects while preserving the parent pointers. It uses a depth-first search (DFS) to
// find nodes at a given distance k from a target node. The inheritance relationship allows the NodeWParent objects to
// inherit the properties of TreeNode and adds the parent member for tracking the parent node.
//
// Approach
//    Complexity
//    Time complexity:
//      O(n+k)O(n + k)O(n+k)
//
//    Space complexity:
//      O(n+k)O(n + k)O(n+k)
class AllNodesKBT {
    private final List<Integer> foundNodes;
    private NodeWParent start;

    public AllNodesKBT() {
        foundNodes = new ArrayList<>();
        start = null;
    }

    private NodeWParent nodeWParentReachingT(TreeNode root, TreeNode target) {
        if (root == null)
            return null;

        NodeWParent nodeWParent = new NodeWParent(root.val);

        if (nodeWParent.val == target.val)
            start = nodeWParent;

        nodeWParent.left = nodeWParentReachingT(root.left, target);
        if (nodeWParent.left != null) {
            ((NodeWParent) nodeWParent.left).parent = nodeWParent;
        }

        nodeWParent.right = nodeWParentReachingT(root.right, target);
        if (nodeWParent.right != null) {
            ((NodeWParent) nodeWParent.right).parent = nodeWParent;
        }

        return nodeWParent;
    }

    private void dfsNodeWParent(NodeWParent start, int k) {
        if (start == null || start.val == Integer.MIN_VALUE)
            return;

        if (k == 0)
            foundNodes.add(start.val);

        start.val = Integer.MIN_VALUE;
        dfsNodeWParent(start.parent, k - 1);
        dfsNodeWParent((NodeWParent) start.left, k - 1);
        dfsNodeWParent((NodeWParent) start.right, k - 1);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        nodeWParentReachingT(root, target);
        dfsNodeWParent(start, k);
        return foundNodes;
    }

    static class NodeWParent extends TreeNode {
        NodeWParent parent;

        public NodeWParent(int x) {
            super(x);
            parent = null;
        }
    }
}
