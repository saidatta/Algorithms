package leetcode.string.two_pointers;

// https://leetcode.com/problems/maximum-xor-product/solutions/4304472/c-java-python-javascript-beats-100-explained/
public class MaximumXorProduct {

    private static final int MOD = 1_000_000_007;

    public int maximumXorProduct(long a, long b, int n) {
        long ans = 0;
        int big = 0;
        boolean found = false;

        // Iterate through the bits from MSB to LSB (from 50 to 0)
        for (int i = 50; i >= 0; i--) {
            long curr = 1L << i;

            // Check if both bits at 'i' in 'a' and 'b' are 0
            if (((a & curr) == 0) && ((b & curr) == 0)) {
                if (i < n) {
                    ans += curr; // If bit index 'i' is less than 'n', add 'curr' to 'ans'
                }
            }
            // Check if bit at 'i' is set in 'a' and not in 'b'
            else if (((a & curr)) != 0 && ((b & curr) == 0)) {
                if (big == 0) {
                    big = -1;
                } else if (big == -1 && i < n) {
                    ans += curr; // XOR 'curr' if conditions met and 'i' < 'n'
                }
            }
            // Check if bit at 'i' is set in 'b' and not in 'a'
            else if (((a & curr) == 0) && ((b & curr) != 0)) {
                if (big == 0) {
                    big = 1;
                } else if (big == 1 && i < n) {
                    ans += curr; // XOR 'curr' if conditions met and 'i' < 'n'
                }
            }
        }

        // Perform XOR between 'a' and 'b' with 'ans' and take modulo
        a ^= ans;
        b ^= ans;
        a %= MOD;
        b %= MOD;
        ans = (a * b) % MOD; // Calculate product modulo 10^9 + 7

        return (int) ans; // Return the resultant maximum XOR product as integer
    }

    public static void main(String[] args) {
        MaximumXorProduct solution = new MaximumXorProduct();
        System.out.println(solution.maximumXorProduct(12, 5, 4)); // Output: 98
        System.out.println(solution.maximumXorProduct(6, 7, 5)); // Output: 930
        System.out.println(solution.maximumXorProduct(1, 6, 3)); // Output: 12
    }
}
