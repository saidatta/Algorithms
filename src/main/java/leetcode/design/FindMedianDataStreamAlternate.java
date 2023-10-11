package leetcode.design;

import java.util.*;

public class FindMedianDataStreamAlternate {

    private TreeSet<NumberWithId> data;
    private NumberWithId mid;
    private int size;
    private long idCounter;

    public FindMedianDataStreamAlternate() {
        data = new TreeSet<>();
        size = 0;
        idCounter = 0;
    }

    public void addNum(int num) {
        NumberWithId newNum = new NumberWithId(num, idCounter++);
        data.add(newNum);

        if (size == 0) {
            mid = newNum;
        } else if (num < mid.number) {
            mid = size % 2 == 0 ? data.lower(mid) : mid;
        } else {
            mid = size % 2 == 0 ? mid : data.higher(mid);
        }

        size++;
    }

    public double findMedian() {
        if (size % 2 == 1) {
            return (double) mid.number;
        } else {
            return (mid.number + (double) data.higher(mid).number) / 2.0;
        }
    }

    private static class NumberWithId implements Comparable<NumberWithId> {
        int number;
        long id;

        NumberWithId(int num, long id) {
            this.number = num;
            this.id = id;
        }

        @Override
        public int compareTo(NumberWithId other) {
            if (this.number != other.number) {
                return Integer.compare(this.number, other.number);
            }
            return Long.compare(this.id, other.id);
        }
    }

    public static void main(String[] args) {
        FindMedianDataStreamAlternate finder = new FindMedianDataStreamAlternate();
        finder.addNum(1);
        finder.addNum(2);
        System.out.println(finder.findMedian()); // Should print 1.5
        finder.addNum(3);
        System.out.println(finder.findMedian()); // Should print 2.0
    }
}
