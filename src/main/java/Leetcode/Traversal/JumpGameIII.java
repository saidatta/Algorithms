package Leetcode.Traversal;

// https://leetcode.com/problems/jump-game-iii/
public class JumpGameIII {
    public static boolean canReach(int[] arr, int start) {
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }

        // Mark the index as visited by setting its value to a negative number
        arr[start] = -arr[start];

        // If the value at the current index is 0, return true
        if (arr[start] == 0) {
            return true;
        }

        // Recursively try to jump forward and backward
        return canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
    }

    public static void main(String[] args) {
        int[] arr1 = {4,2,3,0,3,1,2};
        int start1 = 5;
        System.out.println(canReach(arr1, start1));  // true

        int[] arr2 = {4,2,3,0,3,1,2};
        int start2 = 0;
        System.out.println(canReach(arr2, start2));  // true

        int[] arr3 = {3,0,2,1,2};
        int start3 = 2;
        System.out.println(canReach(arr3, start3));  // false
    }

}
