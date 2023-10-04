package leetcode.array.sorting;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-gap/description/
//
//Approach 3: Buckets and The Pigeonhole Principle
//
//        Intuition:
//        Instead of sorting an array which takes significant time, divide the array's numbers into "buckets".
//        If we can organize numbers into these buckets properly, we only need to compare the boundaries of adjacent
//        buckets rather than comparing each number against every other number.
//        Pigeonhole Principle:
//        If you have more items (n) than containers (m) to put them into, and n > m, then at least one container has
//        more than one item.
//        In context: If there are fewer buckets than numbers, then some buckets will contain more than one number.
//        Gaps Between Elements:
//        The widest gap in a sorted list would be the difference between the maximum and minimum values divided by the
//        number of gaps (n-1).
//        Formula:
//        t
//        =
//        (
//        m
//        a
//        x
//        −
//        m
//        i
//        n
//        )
//        (
//        n
//        −
//        1
//        )
//        t=
//        (n−1)
//        (max−min)
//        ​
//
//        The calculated 't' gives the potential maximum gap between numbers.
//        Bucket Strategy:
//        Assign numbers to buckets based on their values.
//        Each bucket's size should be smaller than the potential max gap 't' to ensure that the maximum gap is between
//        buckets, not within them.
//        Use formula:
//        1
//<
//        b
//≤
//        (
//        m
//        a
//        x
//        −
//        m
//        i
//        n
//        )
//        (
//        n
//        −
//        1
//        )
//        1<b≤
//        (n−1)
//        (max−min)
//        for bucket size.
//        Clarifications:
//        Buckets have uniform size.
//        The true "gap" represented by a bucket isn't determined by its size, but by its actual contents.
//        A bucket that can hold numbers between 6-10, if only holding numbers 7 and 9, has an "extent" (gap) of 3.
//        To find the maximum gap, compare the minimum element of one bucket to the maximum element of its previous
//        bucket.
//        The overall comparisons required are just twice the number of buckets (the maximum and minimum of each
//        bucket), which is less than the total number of elements, making this method efficient.
//        In essence, instead of comparing all numbers against each other, this approach breaks the array into
//        manageable buckets and only compares numbers at the boundaries of these buckets. This significantly reduces
//        the number of comparisons needed to determine the maximum gap.
public class MaxGap {
    // Inner Bucket class to store bucket properties
    private static class Bucket {
        // Indicates if any number falls into this bucket
        boolean isUsed = false;
        // Minimum and maximum values within this bucket
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // Find minimum and maximum values in the input array
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();

        // Calculate bucket size and number of buckets needed
        // Bucket size ensures numbers are evenly distributed
        int bucketSize = Math.max(1, (maxVal - minVal) / (nums.length - 1));
        int bucketCount = (maxVal - minVal) / bucketSize + 1;

        // Initialize buckets
        Bucket[] buckets = new Bucket[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new Bucket();
        }

        // Distribute numbers into buckets
        for (int num : nums) {
            int bucketIndex = (num - minVal) / bucketSize;
            buckets[bucketIndex].isUsed = true;
            buckets[bucketIndex].minValue = Math.min(num, buckets[bucketIndex].minValue);
            buckets[bucketIndex].maxValue = Math.max(num, buckets[bucketIndex].maxValue);
        }

        // Compare adjacent buckets to find maximum gap
        int previousBucketMax = minVal;
        int maxGap = 0;
        for (Bucket bucket : buckets) {
            if (!bucket.isUsed) {
                continue;
            }

            maxGap = Math.max(maxGap, bucket.minValue - previousBucketMax);
            previousBucketMax = bucket.maxValue;
        }

        return maxGap;
    }

    public static void main(String[] args) {
        MaxGap solution = new MaxGap();

        // Test cases
        int[] nums1 = {3, 6, 9, 2};
        int[] nums2 = {10, 20, 30, 40, 50};
        int[] nums3 = {1, 1, 1, 1};

        // Execute and print results
        System.out.println("Maximum gap in [3, 6, 9, 2]: " + solution.maximumGap(nums1));  // Expected output: 3
        System.out.println("Maximum gap in [10, 20, 30, 40, 50]: " + solution.maximumGap(nums2));  // Expected output: 10
        System.out.println("Maximum gap in [1, 1, 1, 1]: " + solution.maximumGap(nums3));  // Expected output: 0
    }


}
