package Leetcode.Design;

import Leetcode.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/?tab=Description
 *
 * Created by venkatamunnangi on 3/7/17.
 */
public class BSTIterator {

    private Stack<TreeNode> treeNodeStack;

    public BSTIterator(TreeNode root) {
        // root == null
        treeNodeStack = new Stack<>();
        TreeNode curr = root;
        while(curr != null) {
            // the O ( h ) requirement
            treeNodeStack.push(curr);
            if(curr.left != null) {
                curr = curr.left;
            } else {
                break;
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !treeNodeStack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode smallestNode = treeNodeStack.pop();
        TreeNode currentNode = smallestNode;

        if(currentNode.right != null) {
            currentNode = currentNode.right;

            while(currentNode != null) {
                treeNodeStack.push(currentNode);
                if(currentNode.left != null) {
                    currentNode = currentNode.left;
                } else {
                    break;
                }
            }
        }

        return smallestNode.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */