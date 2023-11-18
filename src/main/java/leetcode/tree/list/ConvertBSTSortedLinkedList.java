package leetcode.tree.list;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 *
*Created by venkatamunnangi on 10/7/19.
*/
public class ConvertBSTSortedLinkedList {

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode[] treeNodes = divideAndConquer(root);
        // make it circular.
        TreeNode first = treeNodes[0], last = treeNodes[1];
        first.left = last;
        last.right = first;
        return first;
    }

    // helper function that return a length 2 TreeNode array (when current subtree is nonempty)
    // or null (when current subtree is empty)
    // first TreeNode of array will be the head of DLL formed from current subtree
    // last TreeNode of array will be the tail of DLL formed from current subtree
    private TreeNode[] divideAndConquer(TreeNode curr) {
        if (curr == null) {
            return null;
        }

        // divide
        TreeNode[] left = divideAndConquer(curr.left);
        TreeNode[] right = divideAndConquer(curr.right);

        // in case both left subtree and right subtree are both non-empty
        if (left != null && right != null) {
            // make it sorted 1<>2<><3> <==> left <> parent <> right
            left[1].right = curr;
            curr.left = left[1];
            curr.right = right[0];
            right[0].left = curr;
            return new TreeNode[]{left[0], right[1]};
        }

        // in case only left subtree is non-empty
        if (left != null) {
            left[1].right = curr;
            curr.left = left[1];
            return new TreeNode[]{left[0], curr};
        }

        // in case only right subtree is non-empty
        if (right != null) {
            curr.right = right[0];
            right[0].left = curr;
            return new TreeNode[]{curr, right[1]};
        }

        // in case both subtrees are empty
        return new TreeNode[]{curr, curr};
    }

    public static void main(String [] args) {
        ConvertBSTSortedLinkedList convertBSTSortedLinkedList = new ConvertBSTSortedLinkedList();

        TreeNode TreeNode = new TreeNode(4);
        TreeNode TreeNode2 = new TreeNode(2);
        TreeNode TreeNode3 = new TreeNode(5);
        TreeNode TreeNode4 = new TreeNode(1);
        TreeNode TreeNode5 = new TreeNode(3);

        TreeNode.left = TreeNode2;
        TreeNode.right = TreeNode3;

        TreeNode2.left = TreeNode4;
        TreeNode2.right = TreeNode5;

        System.out.println(convertBSTSortedLinkedList.treeToDoublyList(TreeNode));

    }
}
