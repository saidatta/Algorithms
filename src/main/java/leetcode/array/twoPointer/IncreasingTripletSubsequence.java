package leetcode.array.twoPointer;

// https://leetcode.com/problems/increasing-triplet-subsequence/
public class IncreasingTripletSubsequence {
    public static boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int secondSmall = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= small) {
                small = num;
            } else if (num <= secondSmall) {
                secondSmall = num;
            } else {
                // num is greater than both small and secondSmall
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5};
        System.out.println(increasingTriplet(nums1)); // Expected output: true

        int[] nums2 = {5,4,3,2,1};
        System.out.println(increasingTriplet(nums2)); // Expected output: false

        int[] nums3 = {2,1,5,0,4,6};
        System.out.println(increasingTriplet(nums3)); // Expected output: true
    }
}

//    To solve the problem in O(n) time complexity and O(1) space complexity, we can make use of a simple observation.
//    Let's iterate through the array and try to maintain two values: the smallest value (small) and the second smallest
//    value (secondSmall) encountered so far.
//
//        We'll initialize both of these values to the maximum possible value (Integer.MAX_VALUE in Java). As we iterate
//        through the array, if we find a value less than small, we update small. If we find a value greater than small
//        but less than secondSmall, we update secondSmall.
//
//        Now, if we find a value greater than secondSmall during the iteration, it means we've found a triplet that
//        satisfies the given conditions, and we can return true. If we finish iterating through the array without
//        finding such a triplet, we return false.
//
//        The reason this approach works is that if we've updated secondSmall, it means that there exists an element in
//        the array before secondSmall that is smaller than it. And when we find a value greater than secondSmall, it means we've found our increasing triplet.