package leetcode;

/**
 * Created by venkatamunnangi on 3/27/17.
 */
public class Interval {
    int start;
    int end;

    public Interval(int s, int e) {
        start = s;
        end = e;
    }

    public String toString() {
        return "" + start + '-' + end;
    }
}
