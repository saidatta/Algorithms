package leetcode.design.datastructure.iterator;

import leetcode.tree.util.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/?tab=Description
 *
 * Created by venkatamunnangi on 3/7/17.
 */
public class BSTIterator {
    private final Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        this.leftmostInorder(root);
    }

    private void leftmostInorder(TreeNode node) {
        // Add all the nodes from root to the leftmost leaf
        // This will be the path of nodes to be visited
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        // Pop the next smallest element from the stack
        TreeNode topNode = stack.pop();

        // If there is a right subtree, process its leftmost nodes
        if (topNode.right != null) {
            leftmostInorder(topNode.right);
        }

        return topNode.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

    /**
     * An in-order implementation of a BST Iterator. For example, given a BST with Integer values,
     * expect to retrieve TreeNodes according to the Integer's natural ordering (1, 2, 3...)
     *
     * @param <T> This Iterator has been implemented with generic typing to allow for TreeNodes of
     *            different value types
     */
//    public class BstIterator2<T extends Comparable<T>> implements Iterator<BstIterator2.TreeNode2<T>> {
//
//        private final ArrayDeque<TreeNode2<T>> pathStack;
//
//        public BstIterator2(TreeNode<T> root) {
//            pathStack = new ArrayDeque<>();
//            pushPathToNextSmallest(root);
//        }
//
//        /**
//         * This BstIterator manages to use O(h) extra space, where h is the height of the tree It achieves
//         * this by maintaining a stack of the nodes to handle (pushing all left nodes first), before
//         * handling self or right node.
//         *
//         * @param node TreeNode that acts as root of the subtree we're interested in.
//         */
//        private void pushPathToNextSmallest(TreeNode<T> node) {
//            while (node != null) {
//                pathStack.push(node);
//                node = node.getLeft();
//            }
//        }
//
//        /**
//         * Checks if there exists next element.
//         *
//         * @return true if this iterator has a "next" element
//         */
//        @Override
//        public boolean hasNext() {
//            return !pathStack.isEmpty();
//        }
//
//        /**
//         * Gets the next element.
//         *
//         * @return TreeNode next. The next element according to our in-order traversal of the given BST
//         * @throws NoSuchElementException if this iterator does not have a next element
//         */
//        @Override
//        public TreeNode2<T> next() throws NoSuchElementException {
//            if (pathStack.isEmpty()) {
//                throw new NoSuchElementException();
//            }
//            var next = pathStack.pop();
//            pushPathToNextSmallest(next.getRight());
//            return next;
//        }
//
//
//        public class TreeNode2<T extends Comparable<T>> {
//
//            private final T val;
//            private TreeNode<T> left;
//            private TreeNode<T> right;
//
//            /**
//             * Creates a TreeNode with a given value, and null children.
//             *
//             * @param val The value of the given node
//             */
//            public TreeNode(T val) {
//                this.val = val;
//                this.left = null;
//                this.right = null;
//            }
//
//            public T getVal() {
//                return val;
//            }
//
//            public TreeNode<T> getLeft() {
//                return left;
//            }
//
//            private void setLeft(TreeNode<T> left) {
//                this.left = left;
//            }
//
//            public TreeNode<T> getRight() {
//                return right;
//            }
//
//            private void setRight(TreeNode<T> right) {
//                this.right = right;
//            }
//
//            /**
//             * Inserts new TreeNode based on a given value into the subtree represented by self.
//             *
//             * @param valToInsert The value to insert as a new TreeNode
//             */
//            public void insert(T valToInsert) {
//                var parent = getParentNodeOfValueToBeInserted(valToInsert);
//                parent.insertNewChild(valToInsert);
//            }
//
//            /**
//             * Fetch the Parent TreeNode for a given value to insert into the BST.
//             *
//             * @param valToInsert Value of the new TreeNode to be inserted
//             * @return Parent TreeNode of `valToInsert`
//             */
//            private TreeNode<T> getParentNodeOfValueToBeInserted(T valToInsert) {
//                TreeNode<T> parent = null;
//                var curr = this;
//
//                while (curr != null) {
//                    parent = curr;
//                    curr = curr.traverseOneLevelDown(valToInsert);
//                }
//
//                return parent;
//            }
//
//            /**
//             * Returns left or right child of self based on a value that would be inserted; maintaining the
//             * integrity of the BST.
//             *
//             * @param value The value of the TreeNode that would be inserted beneath self
//             * @return The child TreeNode of self which represents the subtree where `value` would be inserted
//             */
//            private TreeNode<T> traverseOneLevelDown(T value) {
//                if (this.isGreaterThan(value)) {
//                    return this.left;
//                }
//                return this.right;
//            }
//
//            /**
//             * Add a new Child TreeNode of given value to self. WARNING: This method is destructive (will
//             * overwrite existing tree structure, if any), and should be called only by this class's insert()
//             * method.
//             *
//             * @param valToInsert Value of the new TreeNode to be inserted
//             */
//            private void insertNewChild(T valToInsert) {
//                if (this.isLessThanOrEqualTo(valToInsert)) {
//                    this.setRight(new TreeNode<>(valToInsert));
//                } else {
//                    this.setLeft(new TreeNode<>(valToInsert));
//                }
//            }
//
//            private boolean isGreaterThan(T val) {
//                return this.val.compareTo(val) > 0;
//            }
//
//            private boolean isLessThanOrEqualTo(T val) {
//                return this.val.compareTo(val) < 1;
//            }
//
//            @Override
//            public String toString() {
//                return val.toString();
//            }
//
//        }
//
//    }

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */