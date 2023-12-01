package leetcode.math.string;

// https://leetcode.com/problems/next-greater-element-iii/description/ - S Tier editorial
public class NextGreaterElementIII {

    public int findNextGreaterElement(int n) {
        // Convert the number to a char array for easy manipulation of its digits
        char[] digits = Integer.toString(n).toCharArray();

        // Step 1: Find the pivot, the first digit from the right that is smaller than the digit to its right
        // This is where the sequence starts to be in descending order
        int pivotIndex;
        for (pivotIndex = digits.length - 2; pivotIndex >= 0; pivotIndex--) {
            if (digits[pivotIndex] < digits[pivotIndex + 1]) {
                break;
            }
        }

        // If no pivot is found, the digits are in descending order, and no greater number is possible
        if (pivotIndex == -1) return -1;

        // Step 2: Find the smallest digit greater than the pivot from the right side of the pivot
        // This digit will be swapped with the pivot to form the next greater number
        int swapIndex;
        for (swapIndex = digits.length - 1; swapIndex > pivotIndex; swapIndex--) {
            if (digits[swapIndex] > digits[pivotIndex]) {
                break;
            }
        }

        // Step 3: Swap the pivot with the found digit
        swapDigits(digits, pivotIndex, swapIndex);

        // Step 4: Reverse the digits after the pivot index to get the smallest sequence
        // The subarray to the right of the pivotIndex is in descending order.
        // Reversing it will give us the next smallest lexicographic permutation
        reverseDigits(digits, pivotIndex + 1, digits.length - 1);

        // Convert the char array back to a number, handling any potential overflow
        try {
            return Integer.parseInt(new String(digits));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void swapDigits(char[] digits, int i, int j) {
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }

    private void reverseDigits(char[] digits, int start, int end) {
        while (start < end) {
            swapDigits(digits, start++, end--);
        }
    }

    public static void main(String[] args) {
        NextGreaterElementIII solution = new NextGreaterElementIII();
        System.out.println(solution.findNextGreaterElement(132)); // Output: 21
        System.out.println(solution.findNextGreaterElement(1234)); // Output: 21
        System.out.println(solution.findNextGreaterElement(123)); // Output: -1
    }
}


// Enhanced Explanation with the Provided Intuition:
//  - The process starts by identifying the 'pivot point', which is the point where the digits stop increasing in
//    value as you read them from right to left. This is the first digit from the right that is smaller than the
//    digit immediately to its right.
//  - If no such pivot is found, it implies that the entire number is in descending order, and therefore, no greater
//    permutation of its digits is possible.
//  - After finding the pivot, the next step is to find the smallest digit to the right of the pivot that is greater
//    than the pivot itself. This is because swapping the pivot with this digit will result in the next greater number.
//  - Once the swap is made, the sequence to the right of the pivot is still in descending order. To get the smallest
//    permutation, these digits are reversed, which effectively sorts them in ascending order.
//  - This approach ensures that the rearranged number is the next greater number that can be formed from the
//    original number's digits.