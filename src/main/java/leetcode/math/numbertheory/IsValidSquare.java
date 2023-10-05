package leetcode.math.numbertheory;

// https://leetcode.com/problems/valid-perfect-square/description/
public class IsValidSquare {
    public boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }

        long left = 2, right = num / 2, mid, guessSquared;
        while (left <= right) {
            mid = left + (right - left) / 2;
            guessSquared = mid * mid;

            if (guessSquared == num) {
                return true;
            }

            if (guessSquared > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        IsValidSquare solution = new IsValidSquare();

        // Test cases
        int num1 = 16;
        System.out.println(solution.isPerfectSquare(num1)); // Expected output: true

        int num2 = 14;
        System.out.println(solution.isPerfectSquare(num2)); // Expected output: false
    }
}
