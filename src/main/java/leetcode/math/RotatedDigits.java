package leetcode.math;

public class RotatedDigits {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isGood(int num) {
        boolean isValid = false;
        while (num > 0) {
            int digit = num % 10;
            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                isValid = true;
            }
            num /= 10;
        }
        return isValid;
    }

    public static void main(String[] args) {
        RotatedDigits solution = new RotatedDigits();
        System.out.println(solution.rotatedDigits(10));  // Output: 4
    }
}
