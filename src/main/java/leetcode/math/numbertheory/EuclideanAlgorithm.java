package leetcode.math.numbertheory;

public class EuclideanAlgorithm {

    /**
     * Computes the greatest common divisor (GCD) of two numbers using the Euclidean algorithm.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The greatest common divisor of a and b.
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        int num1 = 90;
        int num2 = 9;
        System.out.println("The GCD of " + num1 + " and " + num2 + " is " + gcd(num1, num2));
    }
}

