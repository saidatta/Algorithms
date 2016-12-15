package Leetcode;

import java.util.List;

/**
 * Created by venkatamunnangi on 12/13/16.
 */
public class NestedListWeight {
    public int depthSum(List<NestedInteger> nestedList) {
        int ans = 0;
        for(NestedInteger ni : nestedList) {
            ans += depthSumHelper(ni, 1);
        }
        return ans;
    }

    public int depthSumHelper(NestedInteger ni, int depth) {

        if(ni.isInteger()) {
            return ni.getInteger()*depth;
        } else {
            List<NestedInteger> tempList = ni.getList();
            int ans = 0;
            ++depth;
            for(int i = 0;i< tempList.size();i++) {
                ans += depthSumHelper(tempList.get(i), depth);
            }
            return ans;
        }
    }
}
