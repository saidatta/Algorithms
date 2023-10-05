package leetcode.array.traversal;

import java.util.Arrays;

// https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/
public class ReplaceElementsFromRightSide {
    public static int[] replaceElements(int[] arr) {
        int n = arr.length;
        // Initialize with -1 as per the problem statement for the last element
        int maxFromRight = -1;

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];  // Store the current value

            // Replace the current value with maxFromRight
            arr[i] = maxFromRight;

            // Update maxFromRight
            maxFromRight = Math.max(maxFromRight, current);
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] arr1 = {17,18,5,4,6,1};
        int[] arr2 = {400};

        replaceElements(arr1);  // Expected: [18,6,6,6,1,-1]
        replaceElements(arr2);  // Expected: [-1]

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

}
