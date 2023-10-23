package Int.fb;

import java.util.ArrayList;
import java.util.List;

class NaryTreeNode {
    public int val;
    public List<NaryTreeNode> children;

    public NaryTreeNode() {
        this.children = new ArrayList<>();
    }

    public NaryTreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public NaryTreeNode(int val, List<NaryTreeNode> children) {
        this.val = val;
        this.children = children;
    }
}
class Pair {
    NaryTreeNode node;
    int depth;

    Pair(NaryTreeNode node, int depth) {
        this.node = node;
        this.depth = depth;
    }
}

public class NaryTreeLCA {
    public static NaryTreeNode findLCA(NaryTreeNode root) {
        return findLCAHelper(root, 0).node;
    }

    private static Pair findLCAHelper(NaryTreeNode node, int depth) {
        if (node == null) {
            return new Pair(null, depth);
        }

        if (node.children == null || node.children.isEmpty()) {
            return new Pair(node, depth);
        }

        Pair result = new Pair(node, depth);
        for (NaryTreeNode child : node.children) {
            Pair childPair = findLCAHelper(child, depth + 1);
            if (childPair.depth > result.depth) {
                result = childPair;
            } else if (childPair.depth == result.depth && childPair.node != result.node) {
                // if its ancestor of two children, then we mark the current node that has that children to be
                //
                result.node = node;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        NaryTreeNode root = new NaryTreeNode(1);
        NaryTreeNode node3 = new NaryTreeNode(3);
        NaryTreeNode node2 = new NaryTreeNode(2);
        NaryTreeNode node4 = new NaryTreeNode(4);
        NaryTreeNode node5 = new NaryTreeNode(5);
        NaryTreeNode node6 = new NaryTreeNode(6);

        root.children = List.of(node3, node2, node4);
        node3.children = List.of(node5, node6);

        NaryTreeNode lca = findLCA(root);
        // Output should be 3
        System.out.println(lca == null ? "null" : lca.val);
    }
}
