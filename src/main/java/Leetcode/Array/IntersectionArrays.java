package Leetcode.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/?tab=Description
 *
 * Created by venkatamunnangi on 3/12/17.
 */
public class IntersectionArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();
        for(int n : nums1) {
            int high = nums2.length-1;
            if(binarySearch(nums2, 0, high, n)) {
                set.add(n);
            }
        }

        int result[] = new int[set.size()];
        int i = 0;
        for(int n : set) {
            result[i++] = n;
        }
        return result;
    }

    private boolean binarySearch(int[] nums, int low, int high, int target) {
        int mid = (low+high) / 2;

        if(low >high) {
            return false;
        }
        if(nums[mid] == target) {
            return true;
        } else if(nums[mid] < target) {
            return binarySearch(nums, mid+1, high, target);
        } else {
            return binarySearch(nums, low, mid-1, target);
        }
    }
}

