package leetcode.tree;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/subtree-of-another-tree
 */
public class CheckSubtree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        StringBuilder leftNodePath = new StringBuilder(), rightNodePath = new StringBuilder();
        serialize(s, leftNodePath);
        serialize(t, rightNodePath);

        return leftNodePath.toString().contains(rightNodePath.toString());
    }

    private void serialize(TreeNode node, StringBuilder nodesPath) {
        nodesPath.append('_');
        nodesPath.append(node.val);

        if (node.left != null) {
            serialize(node.left, nodesPath);
        } else {
            nodesPath.append('N');
        }

        if (node.right != null) {
            serialize(node.right, nodesPath);
        } else {
            nodesPath.append('N');
        }
    }

//Considering s and t two trees.
//
//Calculate height of t [hT]
//Store all the nodes at height hT in s, having same val as t's [list]
//iterate list to check if any of those are identical to t


//    Heighest no. of nodes in s at any level (leaves) : n
//Time : O(t) + O(s) + O(nt)
//Space : lg(t) + lg(s) + nlg(t)
//
//    public boolean isSubtree(TreeNode s, TreeNode t) {
//        List<TreeNode> tN = new ArrayList<>();
//        int tH = height(t);
//        allSameNodesAtH(s, tH, t.val, tN);
//
//        if(tN.size() == 0) return false;
//
//        for(TreeNode sNew : tN) {
//            if(isIdentical(sNew, t)) return true;
//        }
//
//        return false;
//    }

//    private boolean isIdentical(TreeNode s, TreeNode t) {
//        if(s == null && t == null) return true;
//        if(s == null || t == null) return false;
//
//        boolean l = isIdentical(s.left, t.left);
//        boolean r = isIdentical(s.right, t.right);
//
//        return s.val == t.val && l && r;
//    }
//
//    //same as height calculation for s
//    private int allSameNodesAtH(TreeNode n, int h, int v, List<TreeNode> list) {
//        if(n == null) return -1;
//
//        int l = allSameNodesAtH(n.left, h, v, list);
//        int r = allSameNodesAtH(n.right, h, v, list);
//
//        int max = (l >= r ? l : r) + 1;
//        if(n.val == v && max == h) list.add(n);
//
//        return max;
//    }
//
//    private int height(TreeNode n) {
//        if(n == null) return -1;
//        int l = height(n.left);
//        int r = height(n.right);
//
//        return (l >= r ? l : r) + 1;
//    }
}
