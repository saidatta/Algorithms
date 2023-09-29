package leetcode.tree.traversal;

import java.util.HashSet;
import java.util.Set;
import leetcode.tree.util.TreeNodeParent;

public class LowestCommonAncestorBTIII {
    public TreeNodeParent lowestCommonAncestor(TreeNodeParent p, TreeNodeParent q) {
        Set<TreeNodeParent> visited = new HashSet<>();

        // Traverse from p up to the root, adding all visited nodes to the set
        while (p != null) {
            visited.add(p);
            p = p.parent;
        }

        // Traverse from q up to the root. The first node that is found in the set is the LCA.
        while (q != null) {
            if (visited.contains(q)) {
                return q;
            }
            q = q.parent;
        }

        return null;  // Should never be reached since p and q are guaranteed to be in the tree
    }
}
