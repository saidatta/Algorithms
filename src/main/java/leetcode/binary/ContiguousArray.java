package leetcode.binary;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/contiguous-array/
public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxLength = 0, sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += (nums[i] == 0 ? -1 : 1);
            if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        ContiguousArray ca = new ContiguousArray();
        int[] nums1 = {0, 1};
        int[] nums2 = {0, 1, 0};

        System.out.println(ca.findMaxLength(nums1));  // Output: 2
        System.out.println(ca.findMaxLength(nums2));  // Output: 2
    }
}

//    use a HashMap to keep track of the sum of the elements in the array as you iterate through it, treating 0s as -1.
//    The idea is to convert the binary array into an array of 1s and -1s, and then find the longest subarray with a
//    sum of 0.

// Explanation:
//        Convert 0s to -1s in the array.
//
//        Maintain a running sum as you iterate through the array.
//
//        Use a HashMap to store the running sum as the key and its index as the value.
//
//        Whenever you see the same running sum again, it means that the subarray between the two indices has an equal
//        number of 0s and 1s (or -1s and 1s).
//
//        Calculate the length of this subarray and update the maximum length if needed.
//        Return the maximum length after iterating through the array.
//
//        The HashMap helps in quickly finding the index at which the same running sum was seen before, thus enabling
//        constant time lookups and updates.