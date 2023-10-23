package leetcode.array;

// https://leetcode.com/problems/first-missing-positive/
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Place each number in its right position
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        // Step 2: Find the first place where the number is not right
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // Step 3: If no place found
        return n + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositive solution = new FirstMissingPositive();

//        int[] testArray1 = {3, 4, -1, 1};
        int[] testArray2 = {1, 2, 3};
        int[] testArray3 = {7, 8, 9, 11, 12};

//        System.out.println(solution.firstMissingPositive(testArray1));  // Expected output: 2
        System.out.println(solution.firstMissingPositive(testArray2));  // Expected output: 4
        System.out.println(solution.firstMissingPositive(testArray3));  // Expected output: 1
    }
}
