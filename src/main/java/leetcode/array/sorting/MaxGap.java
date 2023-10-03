package leetcode.array.sorting;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-gap/description/
public class MaxGap {

    private static class Bucket {
        boolean used = false;
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int mini = Arrays.stream(nums).min().getAsInt();
        int maxi = Arrays.stream(nums).max().getAsInt();

        int bucketSize = Math.max(1, (maxi - mini) / (nums.length - 1)); // bucket size or capacity
        int bucketNum = (maxi - mini) / bucketSize + 1;                  // number of buckets
        Bucket[] buckets = new Bucket[bucketNum];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }

        for (int num : nums) {
            int bucketIdx = (num - mini) / bucketSize;                   // locating correct bucket
            buckets[bucketIdx].used = true;
            buckets[bucketIdx].minVal = Math.min(num, buckets[bucketIdx].minVal);
            buckets[bucketIdx].maxVal = Math.max(num, buckets[bucketIdx].maxVal);
        }

        int prevBucketMax = mini, maxGap = 0;
        for (Bucket bucket : buckets) {
            if (!bucket.used) {
                continue;
            }

            maxGap = Math.max(maxGap, bucket.minVal - prevBucketMax);
            prevBucketMax = bucket.maxVal;
        }

        return maxGap;
    }
}
