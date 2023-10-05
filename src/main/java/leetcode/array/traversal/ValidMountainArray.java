package leetcode.array.traversal;

// https://leetcode.com/problems/valid-mountain-array/
public class ValidMountainArray {

    public boolean validMountainArray(int[] arr) {
        if (arr == null || arr.length < 3) {
            return false;
        }

        int i = 0;

        // Climb up
        while (i < arr.length - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        // Peak can't be the first or last element
        if (i == 0 || i == arr.length - 1) {
            return false;
        }

        // Climb down
        while (i < arr.length - 1 && arr[i] > arr[i + 1]) {
            i++;
        }

        // If we've reached the end, it's a mountain
        return i == arr.length - 1;
    }

    public static void main(String[] args) {
        ValidMountainArray solution = new ValidMountainArray();
        System.out.println(solution.validMountainArray(new int[] {2, 1}));         // Output: false
        System.out.println(solution.validMountainArray(new int[] {3, 5, 5}));      // Output: false
        System.out.println(solution.validMountainArray(new int[] {0, 3, 2, 1}));   // Output: true
    }
}