package leetcode.tree.util;

public class TreeNodeParent {
    public int val;
    public TreeNodeParent left;
    public TreeNodeParent right;
    public TreeNodeParent parent;
    public TreeNodeParent(int x) { val = x; }

    public TreeNodeParent(int x, TreeNodeParent left, TreeNodeParent right, TreeNodeParent parent) {
        val = x;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
