package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/recover-binary-search-tree/
class RecoverBST {
    public void recoverTree(TreeNode root) {
        TreeNode firstSwapped = null, secondSwapped = null;
        TreeNode predecessor, predNode = null;

        while (root != null) {
            if (root.left != null) {
                // Find the rightmost node in the left subtree (Morris predecessor)
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // Establish a temporary link to the current root
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    // Temporary link already exists, time to break it and move to right subtree
                    if (predNode != null && root.val < predNode.val) {
                        secondSwapped = root;
                        if (firstSwapped == null) {
                            firstSwapped = predNode;
                        }
                    }
                    predNode = root;

                    predecessor.right = null;
                    root = root.right;
                }
            } else {
                // No left child, check current node and move to the right child
                if (predNode != null && root.val < predNode.val) {
                    secondSwapped = root;
                    if (firstSwapped == null) {
                        firstSwapped = predNode;
                    }
                }
                predNode = root;

                root = root.right;
            }
        }

        // Swap the values of the two swapped nodes
        swap(firstSwapped, secondSwapped);
    }

    // Function to swap values of two nodes
    public void swap(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    public static void main(String[] args) {
        // Construct the BST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2); // Swapped node

        // Print the BST before recovery
        System.out.println("BST Before Recovery (In-order Traversal):");
        inOrderTraversal(root);
        System.out.println();

        // Recover the BST
        RecoverBST solution = new RecoverBST();
        solution.recoverTree(root);

        // Print the BST after recovery
        System.out.println("BST After Recovery (In-order Traversal):");
        inOrderTraversal(root);
        System.out.println();
    }

    private static void inOrderTraversal(TreeNode node) {
        if (node == null) return;
        inOrderTraversal(node.left);
        System.out.print(node.val + " ");
        inOrderTraversal(node.right);
    }
}


//    The provided Java code implements the Morris Traversal algorithm to recover a binary search tree (BST) in which two nodes have been swapped. This approach does not require extra space for recursion or a stack, making it an O(1) space complexity solution, which is more efficient than the recursive approach in terms of space.
//
//        Morris Traversal is a tree traversal algorithm that does not use recursion or a stack. Instead, it establishes temporary links (or threads) to traverse the tree. Here's a breakdown of the solution:
//
//        1. **Morris Traversal to Find Swapped Nodes**:
//        - The algorithm iterates through the tree using a while loop. For each node, it checks if the node has a left child. If it does, it finds the rightmost child of the left subtree (the Morris predecessor).
//        - If the rightmost child does not point back to the current node (`predecessor.right == null`), a temporary link is created, and the algorithm moves to the left child.
//        - If the rightmost child points to the current node, it means we've already visited the left subtree, so the temporary link is broken, and the algorithm moves to the right child.
//        - While traversing, it keeps track of the current node (`root`) and its predecessor (`pred`). If a pair of nodes is found where the predecessor's value is greater than the current node's value, these nodes are candidates for being swapped.
//
//        2. **Swapping the Swapped Nodes**:
//        - The algorithm identifies two nodes (`x` and `y`) where the BST property is violated. `x` is the first such node, and `y` is the last.
//        - After the traversal, `x` and `y` will point to the two nodes that need to be swapped to recover the BST.
//
//        3. **Swap Function**:
//        - The `swap` function swaps the values of the two nodes identified by the Morris Traversal.
//
//        This solution is efficient in terms of space complexity since it uses the Morris Traversal algorithm, which does not require additional space for recursion or a stack. It is a good approach when space optimization is a critical concern.