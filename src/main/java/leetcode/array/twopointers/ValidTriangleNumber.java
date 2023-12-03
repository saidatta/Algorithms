package leetcode.array.twopointers;

import java.util.Arrays;

// https://leetcode.com/problems/valid-triangle-number/description/
public class ValidTriangleNumber {
//    We can use a similar approach to the 3Sum problem, with a focus on the triangle inequality theorem.
//    The theorem states that the sum of the lengths of any two sides of a triangle must be greater than the length
//    of the third side.
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;

        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    // All elements between left and right can be paired with nums[i] to form a valid triangle
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ValidTriangleNumber solution = new ValidTriangleNumber();
        System.out.println(solution.triangleNumber(new int[]{2, 2, 3, 4})); // Output: 3
        System.out.println(solution.triangleNumber(new int[]{4, 2, 3, 4})); // Output: 4
    }
}
//    We sort the array to make it easier to apply the triangle inequality theorem.
//        We iterate through the array in reverse, for each nums[i], we find pairs (nums[left], nums[right])
//        such that nums[left] + nums[right] > nums[i].
//        When the condition is met, it means that all elements from left to right can form a valid triangle
//        with nums[i], so we add right - left to the count.
//        We continue this process until we have considered all possible triplets in the array.
//        The final count gives us the total number of valid triangles.