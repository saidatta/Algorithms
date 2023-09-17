package leetcode.array.search;

import leetcode.tree.NestedInteger;

import java.util.List;

/**
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 * Example 2:
 * Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
 *
 * Created by venkatamunnangi on 12/13/16.
 */
public class NestedListWeight {
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) {
            return 0;
        }
        int ans = 0;
        for (NestedInteger nestedInteger : nestedList) {
            ans += depthSumHelper(nestedInteger, 1);
        }

        return ans;
    }

    public int depthSumHelper(NestedInteger nestedInteger, int depth) {
        if (nestedInteger.isInteger()) {
            return nestedInteger.getInteger() * depth;
        }

        List<NestedInteger> currentList = nestedInteger.getList();

        int ans = 0;
        int currentDepth = depth + 1;
        for (NestedInteger curr : currentList) {
            ans += depthSumHelper(curr, currentDepth);
        }
        return ans;
    }
}

