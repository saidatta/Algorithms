package Leetcode;

import static java.lang.System.*;

/**
 * Given an array of length n having integers 0 to n-1 in unsorted order.
 * Please modify this array such that the value at a[i] becomes a[a[i]].
 * For example, if a[0] = 5, a[0] will have value a[5] and so on.
 * e.g. {2, 4, 3, 1, 0} => {3, 0, 1, 4, 2}
 * This should take O(n) time complexity.
 *
 * https://discuss.leetcode.com/topic/98/change-array-in-place
 *
 * Created by venkatamunnangi on 12/28/16.
 */
public class ChangeArrayInPlace {
    void rearrange(int[] arr, int n)
    {
        // First step: Increase all values by (arr[arr[i]]%n)*n
        for (int i = 0; i < n; i++) {
            arr[i] += (arr[arr[i]] % n) * n;
        }

        // Second Step: Divide all values by n
        for (int i = 0; i < n; i++)
            arr[i] /= n;
    }

    // A utility function to print an array of size n
    void printArr(int arr[], int n)
    {
        for (int i = 0; i < n; i++) {
            out.print(arr[i] + " ");
        }
        out.println("");
    }

    /* Driver program to test above functions */
    public static void main(String[] args)
    {
        ChangeArrayInPlace rearrange = new ChangeArrayInPlace();
        int arr[] = {3, 2, 0, 1};
        int n = arr.length;

        out.println("Given Array is :");
        rearrange.printArr(arr, n);

        rearrange.rearrange(arr, n);

        out.println("Modified Array is :");
        rearrange.printArr(arr, n);
    }
}
