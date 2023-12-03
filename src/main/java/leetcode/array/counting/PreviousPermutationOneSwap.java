package leetcode.array.counting;

// https://leetcode.com/problems/previous-permutation-with-one-swap/
public class PreviousPermutationOneSwap {
    public int[] prevPermOpt1(int[] arr) {
        int i = arr.length - 2;

        // Step 1: Find the first element from the right that is smaller than the previous element
        while (i >= 0 && arr[i] <= arr[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = arr.length - 1;

            // Step 2: Find the largest element to the right of arr[i] that is smaller than arr[i]
            while (arr[j] >= arr[i]) {
                j--;
            }

            // To handle duplicate elements, find the leftmost occurrence of arr[j]
            while (arr[j] == arr[j - 1]) {
                j--;
            }

            // Step 3: Swap arr[i] and arr[j]
            swap(arr, i, j);
        }

        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        PreviousPermutationOneSwap solution = new PreviousPermutationOneSwap();
        int[] arr1 = {3, 2, 1};
        int[] arr2 = {1, 1, 5};
        int[] arr3 = {1, 9, 4, 6, 7};

        printArray(solution.prevPermOpt1(arr1)); // Output: [3, 1, 2]
        printArray(solution.prevPermOpt1(arr2)); // Output: [1, 1, 5]
        printArray(solution.prevPermOpt1(arr3)); // Output: [1, 7, 4, 6, 9]
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
//    We start from the second last element and move backward to find the first element that is smaller than its
//    next element.

//    Then, we find the largest element to the right of this element that is smaller than it.
//    We handle duplicates by finding the leftmost occurrence of this element.

//    We swap these two elements and return the modified array.
//    If no such pair is found, we return the original array as is.