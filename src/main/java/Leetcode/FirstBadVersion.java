package Leetcode;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * https://leetcode.com/problems/first-bad-version/#/description
 *
 * Created by venkatamunnangi on 3/25/17.
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int start = 1, end = n;

        while(start < end) {
            int mid = start+ (end - start) / 2;
            if(!isBadVersion(mid)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    boolean isBadVersion(int version) {
        // API provided by the LEETCODE.
        throw new NotImplementedException();
    }
}
