package leetcode.array.search;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.tree.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkatamunnangi on 12/13/16.
 */
//[[1,1],2,[1,1]]
public class NestedListWeightII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int sumOfCurrentLevel = 0, sumOfTotalLevels = 0;
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int tempSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.poll();
                if (current.isInteger()) {
                    tempSum += current.getInteger();
                } else {
                    queue.addAll(current.getList());
                }
            }
            sumOfCurrentLevel += tempSum;
            sumOfTotalLevels += sumOfCurrentLevel;
        }
        return sumOfTotalLevels;
    }
}
