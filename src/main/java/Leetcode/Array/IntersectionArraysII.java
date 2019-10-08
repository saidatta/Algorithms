package Leetcode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Given two arrays, write a function to compute their intersection. (includes duplicates)
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * <p>
 * Created by venkatamunnangi on 9/7/19.
 */
public class IntersectionArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        //Frequency Map for all numbers in array 1.
        for (int i : nums1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        ArrayList<Integer> intersectionValues = new ArrayList<Integer>();
        for (int i : nums2) {
            if (map.containsKey(i)) {
                // update the cache once it is used for intersection detection.
                if (map.get(i) > 1) {
                    map.put(i, map.get(i) - 1);
                } else {
                    map.remove(i);
                }

                intersectionValues.add(i);
            }
        }


        int[] intersectionValuesArray = new int[intersectionValues.size()];
        int i = 0;
        while (i < intersectionValues.size()) {
            intersectionValuesArray[i] = intersectionValues.get(i);
            i++;
        }
        return intersectionValuesArray;
    }


    // O(n), O(n)
    // if arrays are sorted, then 2 pointer solution works.
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> list = new ArrayList<Integer>();
        int p1 = 0, p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                list.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        //post processing values.
        int[] result = new int[list.size()];
        int i = 0;
        while (i < list.size()) {
            result[i] = list.get(i);
            i++;
        }
        return result;
    }

}
