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
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        Set<Integer> intersection = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                intersection.add(num);
            }
        }

        int[] result = new int[intersection.size()];
        int idx = 0;
        for (int num : intersection) {
            result[idx++] = num;
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

