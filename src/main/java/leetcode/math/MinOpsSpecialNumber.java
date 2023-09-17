package leetcode.math;

// https://leetcode.com/problems/minimum-operations-to-make-a-special-number/description/
public class MinOpsSpecialNumber {
    public int minimumOperations(String num) {
        if (num.equals("0")) {
            return 0;
        }

        int n = num.length();
        int min = n;

        for (int i = n - 1; i > 0; i--) {
            char tens = num.charAt(i);
            if (tens == '0') {
                min = Math.min(min, n - 1);
            }

            for (int j = i - 1; j >= 0; j--) {
                int pnum = Integer.parseInt(String.valueOf(num.charAt(j)) + tens);
                if (pnum % 25 == 0) {
                    min = Math.min(min, i - j + (n - i - 1) - 1);
                }
            }
        }

        return min;
    }

    public static void main(String[] args) {
        MinOpsSpecialNumber solution = new MinOpsSpecialNumber();
        System.out.println(solution.minimumOperations("2245047"));  // Expected output: 2
        System.out.println(solution.minimumOperations("2908305"));  // Expected output: 3
        System.out.println(solution.minimumOperations("10"));       // Expected output: 1
    }
}
