package Leetcode;

import Leetcode.Tree.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkatamunnangi on 12/13/16.
 */
//[[1,1],2,[1,1]]
public class NestedListWeightII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int sumOfCurrentLevel = 0, sumOfTotalLevels = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    sumOfCurrentLevel += ni.getInteger();
                } else {
                    // you are un-nesting the current depth of nested list.
                    nextLevel.addAll(ni.getList());
                }
            }

            // Since you will be re-adding the root value multiple times on how many ever nested lsits you have.
            // you do not have to multiply.
            sumOfTotalLevels += sumOfCurrentLevel;
            nestedList = nextLevel;
        }
        return sumOfTotalLevels;
    }
}
