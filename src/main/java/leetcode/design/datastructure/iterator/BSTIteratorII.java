package leetcode.design.datastructure.iterator;

import java.util.*;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/binary-search-tree-iterator-ii/description/
class BSTIteratorII {
    private final Stack<TreeNode> stack;
    private final List<Integer> history;
    private int pointer;

    public BSTIteratorII(TreeNode root) {
        this.stack = new Stack<>();
        this.history = new ArrayList<>();
        this.pointer = -1;  // Initialize pointer to non-existent element
        leftmostInorder(root);
    }

    private void leftmostInorder(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public boolean hasNext() {
        return pointer < history.size() - 1 || !stack.isEmpty();
    }

    public int next() {
        pointer++;
        if (pointer == history.size()) {
            // Traverse the tree if needed
            TreeNode node = stack.pop();
            history.add(node.val);
            leftmostInorder(node.right);
        }
        return history.get(pointer);
    }

    public boolean hasPrev() {
        return pointer > 0;
    }

    public int prev() {
        pointer--;
        return history.get(pointer);
    }
}


//    Time complexity.
//        O(1) for the constructor.
//        O(1) for hasPrev.
//        O(1) for prev.
//        O(1) for hasNext.
//        O(N) for next.

//        In the worst-case of the skewed tree one has to parse the entire tree, all N nodes.
//
//        However, the important thing to note here is that it's the worst-case time complexity. We only make such a call
//        for the nodes which we've not yet parsed. We save all parsed nodes in a list and then re-use them if we need to
//        return next from the already parsed area of the tree.
//
//        Thus, the amortized (average) time complexity for the next call would still be O(1).
//
//        Space complexity: O(N). The space is taken by stack and arr. stack contains up to H elements, where HHH is
//        the tree height, and arr up to NNN elements.