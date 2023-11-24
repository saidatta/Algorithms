package Int.fb;

import java.util.ArrayList;
import java.util.List;

public class NaryTreeNode {
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
