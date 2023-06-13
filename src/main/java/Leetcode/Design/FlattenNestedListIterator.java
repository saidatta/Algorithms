package Leetcode.Design;

import Leetcode.Tree.NestedInteger;

import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/#/description
 *
 * Given the list [[1,1],2,[1,1]] - 1,1,2,1,1
 *
 * Given the list [1,[4,[6]]] - 1,4,6
 *
 * Created by venkatamunnangi on 3/28/17.
 */

//I don't think using stack to store all data is a real implmentation of Iterator which is usally used to handle with
// stream data and you obviously cannot store all stream data in advance.
//        So I use a list iterator to get NestedInteger, and if it's integer, just return; otherwise, use an inner
//        NestedInteger iterator to get the next value in the nested list.

public class FlattenNestedListIterator implements Iterator<Integer> {

    // If there is an inner list, this is its iterator
    FlattenNestedListIterator innerIterator;
    // current Element
    NestedInteger currentElement;
    // This is the iterator of the current list, used to get curElem
    Iterator<NestedInteger> currentIterator;

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        this.innerIterator = null;
        this.currentElement = null;
        this.currentIterator = nestedList.iterator();
    }

    @Override
    public Integer next() {
        // If it is currently a number, then return directly.
        // Any time curElem is used up, remember to reset to null
        if (currentElement.isInteger()) {
            int res = currentElement.getInteger();
            currentElement = null;
            return res;
        } else {
            // Otherwise, it means we have an inner list, use its it to return
            return innerIterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        // If there is an inner layer it, use its hasNext() directly (note that if the inner layer returns false,
        // it does not mean that this layer is also false)
        if (innerIterator != null && innerIterator.hasNext()) {
            return true;
        } else {
            // If the inner layer it returns false, it means that the inner layer has been traversed and needs
            // to be reset to null
            innerIterator = null;
        }
        // If curElem is a number, just return it directly
        if (currentElement != null && currentElement.isInteger()) {
            return true;
        }
        // Traverse the current list until it crosses the bounds or finds the next value
        while (currentIterator.hasNext()) {
            // Get the next element
            currentElement = currentIterator.next();
            if (currentElement.isInteger()) {
                return true;
            } else {
                // If the next element is a list, let innerIt become its iterator
                innerIterator = new FlattenNestedListIterator(currentElement.getList());
                if (innerIterator.hasNext()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String [] args) {
        //FlattenNestedListIterator flattenNestedListIterator = new FlattenNestedListIterator();
    }

}
