package Leetcode;

/**
 * Created by venkatamunnangi on 3/25/17.
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int start = 1, end = n;

        while(start < end) {
            int mid = (start + end) / 2;
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
        return false;
    }
}
