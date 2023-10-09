package CompetitiveProgramming;

public class PolynomialHash {

    // Use a prime number as base
    private static final int BASE = 31;
    // To avoid large hash values, use modulo a large prime
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        String text = "example";  // Assuming length 7
        int hashCode = computeHashCode(text);
        System.out.println("Hash code for " + text + ": " + hashCode);
    }

    public static int computeHashCode(String text) {
        int hashCode = 0;
        int powerOfBase = 1;  // BASE^0 = 1

        for (char ch : text.toCharArray()) {
            hashCode = (hashCode + (ch * powerOfBase) % MOD) % MOD;
            powerOfBase = (powerOfBase * BASE) % MOD;
        }
        return hashCode;
    }
}

