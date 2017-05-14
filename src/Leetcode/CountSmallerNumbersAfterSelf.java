package Leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/#/description
 * <p>
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Given nums = [5, 2, 6, 1]
 * <p>
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * <p>
 * <p>
 * Created by venkatamunnangi on 5/14/17.
 */
public class CountSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int... nums) {
        int[] res = new int[nums.length];
        int[] index = new int[res.length];
        for (int i = 0; i < res.length; i++) {
            index[i] = i;
        }
        mergeSort(nums, index, 0, nums.length - 1, res);
        List<Integer> list = new LinkedList<>();
        for (int i : res) {
            list.add(i);
        }
        return list;
    }

    private void mergeSort(int[] nums, int[] index, int l, int r, int[] res) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(nums, index, l, mid, res);
        mergeSort(nums, index, mid + 1, r, res);
        merge(nums, index, l, mid, mid + 1, r, res);
    }

    private void merge(int[] nums, int[] index, int l1, int r1, int l2, int r2, int[] res) {
        int start = l1;
        int[] tmp = new int[r2 - l1 + 1];
        int count = 0;
        int p = 0;
        while (l1 <= r1 || l2 <= r2) {
            if (l1 > r1) {
                tmp[p++] = index[l2++];
            } else if (l2 > r2) {
                res[index[l1]] += count;
                tmp[p++] = index[l1++];
            } else if (nums[index[l1]] > nums[index[l2]]) {
                tmp[p++] = index[l2++];
                count++;
            } else {
                res[index[l1]] += count;
                tmp[p++] = index[l1++];
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            index[start + i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        CountSmallerNumbersAfterSelf countSmallerNumbersAfterSelf = new CountSmallerNumbersAfterSelf();
        System.out.println(countSmallerNumbersAfterSelf.countSmaller(5, 2, 6, 1));
    }
}
