package CompetitiveProgramming.chp3;

/**
 * Find and display all pairs of 5-digit numbers that collectively
 * use the digits 0 through 9 once each, such that the first number divided by the second is
 * equal to an integer N, where 2  N  79. That is, abcde/fghij = N, where each letter
 * represents a di↵erent digit. The first digit of one of the numbers is allowed to be zero, e.g.,
 * for N = 62, we have 79546/01283 = 62; 94736/01528 = 62.
 */
public class UniqueDigitPairs {
    public static void main(String[] args) {
        for (int N = 2; N <= 79; N++) {
            findUniqueDigitPairs(N);
        }
    }

    private static void findUniqueDigitPairs(int N) {
        for (int fghij = 1234; fghij <= 98765 / N; fghij++) {
            // N times fghij should be a 5-digit number
            int abcde = fghij * N;
            // Ensure both are 5-digit numbers
            if (String.valueOf(abcde).length() == 5 || fghij >= 10000) {
                // Flag if 'f' is leading zero
                int used = (fghij < 10000) ? 1 : 0;

                int tmp = abcde;
                while (tmp > 0) {
                    used |= 1 << (tmp % 10);
                    tmp /= 10;
                }

                tmp = fghij;
                while (tmp > 0) {
                    used |= 1 << (tmp % 10);
                    tmp /= 10;
                }

                // If all digits used once, then used should be 1111111111 (binary), which is (1<<10)-1
                if (used == (1 << 10) - 1) {
                    System.out.printf("%05d / %05d = %d%n", abcde, fghij, N);
                }
            }
        }
    }
}

