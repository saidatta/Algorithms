package Leetcode.Design;

import Leetcode.Tree.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/#/description
 *
 * Given the list [[1,1],2,[1,1]] - 1,1,2,1,1
 *
 * Given the list [1,[4,[6]]] - 1,4,6
 *
 * Created by venkatamunnangi on 3/28/17.
 */
public class FlattenNestedListIterator implements Iterator<Integer> {

    private NestedInteger nextInt;
    private Stack<Iterator<NestedInteger>> stack;

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt != null ? nextInt.getInteger() : null;
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else {
                nextInt = stack.peek().next();
                if (nextInt.isInteger()) {
                    return true;
                } else {
                    stack.push(nextInt.getList().iterator());
                }
            }
        }

        return false;
    }

    public static void main(String [] args) {
        //FlattenNestedListIterator flattenNestedListIterator = new FlattenNestedListIterator();
    }

}
