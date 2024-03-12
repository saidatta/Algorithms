package leetcode.array.sorting;

// https://leetcode.com/problems/concatenation-of-array/description/
public class ConcatenationArray {
    public static int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];
            ans[i + n] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] numsExample1 = {1, 2, 1};
        int[] resultExample1 = getConcatenation(numsExample1);
        printArray(resultExample1); // Output: [1, 2, 1, 1, 2, 1]

        int[] numsExample2 = {1, 3, 2, 1};
        int[] resultExample2 = getConcatenation(numsExample2);
        printArray(resultExample2); // Output: [1, 3, 2, 1, 1, 3, 2, 1]
    }

    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}

