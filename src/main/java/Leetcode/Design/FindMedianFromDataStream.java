package Leetcode.Design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/#/description
 * <p>
 *
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 *
 *     Google
 * Created by venkatamunnangi on 5/3/17.
 */
public class FindMedianFromDataStream {
    // leftSideNumbers queue is always larger or equal to rightSideNumbers queue
    static PriorityQueue<BusinessInfo> rightSideNumbers, leftSideNumbers;

    /**
     * initialize your data structure here.
     */
    public FindMedianFromDataStream() {
        rightSideNumbers = new PriorityQueue<>();
        leftSideNumbers = new PriorityQueue<>(Collections.reverseOrder());
    }

    // Adds a number into the data structure.
    public static void addBusinessInfo(BusinessInfo businessInfo) {
        leftSideNumbers.offer(businessInfo);
        rightSideNumbers.offer(leftSideNumbers.poll());
        if (leftSideNumbers.size() < rightSideNumbers.size()) {
            leftSideNumbers.offer(rightSideNumbers.poll());
        }
    }

    // Returns the median of current data stream
    public static double findMedian() {
        if (leftSideNumbers.size() == rightSideNumbers.size()) {
            return (leftSideNumbers.peek().rating + rightSideNumbers.peek().rating) / 2.0;
        } else {
            return leftSideNumbers.peek().rating;
        }
    }


    public static double calculateMedianRating(List<BusinessInfo> businessInfoList) {

        for(BusinessInfo businessInfo : businessInfoList) {
            addBusinessInfo(businessInfo);
        }

        return findMedian();
    }

   static class BusinessInfo implements Comparable<BusinessInfo> {
        int id;
        double rating;

        public BusinessInfo(int id, double rating) {
            this.id = id;
            this.rating = rating;
        }

        public int compareTo(BusinessInfo b1) {
            if (this.rating < b1.rating) {
                return -1;
            }
            if (this.rating > b1.rating) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String... args) {
        FindMedianFromDataStream stream = new FindMedianFromDataStream();
        List<BusinessInfo> al = new ArrayList<>();

        al.add(new BusinessInfo(1, 3.0));
        al.add(new BusinessInfo(2, 1.0));
        al.add(new BusinessInfo(3, 2.0));
        al.add(new BusinessInfo(4, 10.0));

        System.out.println(calculateMedianRating(al));
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addBusinessInfo(num);
 * double param_2 = obj.findMedian();
 */