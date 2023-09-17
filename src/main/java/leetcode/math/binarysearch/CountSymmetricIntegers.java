package leetcode.math.binarysearch;

// https://leetcode.com/problems/count-symmetric-integers/description/
public class CountSymmetricIntegers {
    public  int countSymmetricIntegers(int low, int high) {
        int count = 0;

        for (int i = low; i <= high; i++) {
            if (isSymmetric(i)) {
                count++;
            }
        }

        return count;
    }

    private  boolean isSymmetric(int num) {
        String str = Integer.toString(num);
        int n = str.length();

        if (n % 2 == 1) return false;  // If odd length, return false

        int leftSum = 0, rightSum = 0;

        for (int i = 0; i < n / 2; i++) {
            leftSum += str.charAt(i) - '0';
            rightSum += str.charAt(n - 1 - i) - '0';
        }

        return leftSum == rightSum;
    }
}
