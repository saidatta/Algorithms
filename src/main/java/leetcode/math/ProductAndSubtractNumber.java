package leetcode.math;

// https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
public class ProductAndSubtractNumber {
    public int subtractProductAndSum(int n) {
        int sum = 0, product = 1;

        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            product *= digit;
            n /= 10;
        }

        return product - sum;
    }

    public static void main(String[] args) {
        ProductAndSubtractNumber sol = new ProductAndSubtractNumber();
        System.out.println(sol.subtractProductAndSum(234));  // Expected output: 15
        System.out.println(sol.subtractProductAndSum(4421));  // Expected output: 21
    }
}
