package Leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/zigzag-iterator/#/description
 *
 * Created by venkatamunnangi on 4/30/17.
 */
public class ZigZagIterator {

    Queue<Integer> result;

    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        result = new LinkedList<>();

        int index = 0;
        while(index < v1.size()|| index < v2.size()) {
            if(index < v1.size()) {
                result.offer(v1.get(index));
            }

            if(index < v2.size()) {
                result.offer(v2.get(index));
            }

            index++;
        }
    }

    public int next() {
        return result.poll();
    }

    public boolean hasNext() {
        return !result.isEmpty();
    }

    ///// O(1) SOLUTION

    private Iterator<Integer> i, j, tmp;


    /**
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v2.iterator();
        j = v1.iterator();
    }

    public int next() {
        if (j.hasNext()) { tmp = j; j = i; i = tmp; }
        return i.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }
     **/
}
