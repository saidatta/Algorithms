package leetcode.array.binarysearch;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-k-closest-elements/
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();

        int index = binarySearch(arr, x);
        int left = index - 1;
        int right = index;

        while (k > 0) {
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else {
                if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    left--;
                } else {
                    right++;
                }
            }
            k--;
        }

        for (int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    private int binarySearch(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        FindKClosestElements obj = new FindKClosestElements();
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println(obj.findClosestElements(arr1, 4, 3));  // Expected output: [1, 2, 3, 4]

        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println(obj.findClosestElements(arr2, 4, -1));  // Expected output: [1, 2, 3, 4]
    }
}

//Firstly, you find the possible starting point of the k closest numbers to x using binary search. Once you find a
// potential starting point, you use two pointers to expand outwards to determine the k closest elements.
//
//Here's a step-by-step breakdown:
//
//        Use binary search to find the index of the closest number to x in arr.
//        Use two pointers, left and right, initialized at the found index. Expand these pointers outwards to capture k
//        closest numbers.
//        Compare the absolute differences of arr[left] and arr[right] from x. If arr[left] is closer or equally close
//        but smaller than arr[right], decrement left; otherwise, increment right.
//        Repeat step 3 until you have the k closest numbers.
