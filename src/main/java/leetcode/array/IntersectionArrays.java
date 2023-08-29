package leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/?tab=Description
 *
 * Created by venkatamunnangi on 3/12/17.
 */
public class IntersectionArrays {
    //Time = O(nlog(n)). Space = O(n).
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();
        for(int n : nums1) {
            int high = nums2.length-1;
            // looks whether num1 value exists in num2.
            if(binarySearch(nums2, 0, high, n)) {
                set.add(n);
            }
        }

        // post processing data
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


    //Time = O(n). Space = O(n).
    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for(int i: nums1){
            set1.add(i);
        }
        HashSet<Integer> set2 = new HashSet<Integer>();
        for(int i: nums2){
            if(set1.contains(i)){
                set2.add(i);
            } }
        int[] result = new int[set2.size()];
        int i=0;
        for(int n: set2){
            result[i++] = n;
        }
        return result;
    }

    //nlogn
    public int[] intersectionWithoutSet(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            }
            else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                while (i < nums1.length - 1 && nums1[i + 1] == nums1[i]) {
                    // when found duplicates traverse with future element
                    i++;
                }
                while (j < nums2.length - 1 && nums2[j + 1] == nums2[j]) {
                    // when found duplicates traverse with future element
                    j++;
                }

                i++;
                j++;
            }
        }
        int[] ans = new int[list.size()];
        for (int item = 0; item < list.size(); item++) {
            ans[item] = list.get(item);
        }
        return ans;
    }
}

