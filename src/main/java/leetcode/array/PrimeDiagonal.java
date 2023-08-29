package leetcode.array;

import java.math.BigInteger;

public class PrimeDiagonal {
    public  static int diagonalPrime(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0].length == 0) {
            return 0;
        }

        int m =  nums.length, n =  nums[0].length;
        boolean down = false;
        int k = 0;
        if(m > n) {
            down = true;
            k = n;
        } else {
            k = m;
        }
        int max = 0;
        for (int i = 0; i < k; i++) {
            BigInteger bigInt = BigInteger.valueOf(nums[i][i]);
            if (bigInt.isProbablePrime(100)) {
                max = Math.max(max, nums[i][i]);
            }

            if (down) {
                bigInt = BigInteger.valueOf(nums[i][k - i-1]);
            } else {
                bigInt = BigInteger.valueOf(nums[k-i-1][i]);
            }

            if (bigInt.isProbablePrime(100)) {
                max = Math.max(max, nums[i][i]);
            }
        }


        return max;
    }

    public int diagonalPrime2(int[][] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (isPrime(nums[i][i])) {
                max = Math.max(max, nums[i][i]);
            }
            if (isPrime(nums[nums.length - i - 1][i])) {
                max = Math.max(max, nums[nums.length - i - 1][i]);
            }
        }
        return max;
    }

    boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String [] args) {
//        PrimeDiagonal.diagonalPrime(new int[][]{[1,2,3],[5,6,7],[9,10,11]);
    }
}
