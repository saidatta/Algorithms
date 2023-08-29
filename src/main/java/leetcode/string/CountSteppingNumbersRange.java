package leetcode.string;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/count-stepping-numbers-in-range/
 */
public class CountSteppingNumbersRange {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        CountSteppingNumbersRange c = new CountSteppingNumbersRange();
        System.out.println(c.countSteppingNumbers("1", "11"));  // 10
        System.out.println(c.countSteppingNumbers("90", "101")); // 2
    }

    public int countSteppingNumbers(String low, String high) {
        BigInteger lowBI = new BigInteger(low);
        BigInteger highBI = new BigInteger(high);

        int count = 0;
        Queue<BigInteger> queue = new LinkedList<>();
        for (BigInteger i = BigInteger.ONE; i.compareTo(BigInteger.TEN) < 0; i = i.add(BigInteger.ONE)) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            BigInteger num = queue.poll();

            if (num.compareTo(lowBI) >= 0 && num.compareTo(highBI) <= 0) {
                count++;
                count %= MOD;
            }

            BigInteger lastDigit = num.mod(BigInteger.TEN);
            BigInteger incLastDigit = lastDigit.add(BigInteger.ONE);
            BigInteger decLastDigit = lastDigit.subtract(BigInteger.ONE);

            if (incLastDigit.compareTo(BigInteger.TEN) < 0) {
                BigInteger nextNum = num.multiply(BigInteger.TEN).add(incLastDigit);
                if (nextNum.compareTo(highBI) <= 0) {
                    queue.offer(nextNum);
                }
            }

            if (decLastDigit.compareTo(BigInteger.ZERO) >= 0) {
                BigInteger nextNum = num.multiply(BigInteger.TEN).add(decLastDigit);
                if (nextNum.compareTo(highBI) <= 0) {
                    queue.offer(nextNum);
                }
            }
        }

        return count;
    }
}
