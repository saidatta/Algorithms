package CompetitiveProgramming;

public class RollingHash {

    // Prime numbers for base and modulo
    private static final int BASE = 31;
    private static final int MOD = 1_000_000_007;

    private int currentHash;
    private int basePower;

    public RollingHash() {
        this.currentHash = 0;
        this.basePower = 1;  // BASE^0 = 1 initially
    }

    // Computes the initial hash for the given text
    public void computeInitialHash(String text) {
        for (char ch : text.toCharArray()) {
            addCharacter(ch);
        }
    }

    // Adds a character to the hash and updates basePower
    public void addCharacter(char ch) {
        currentHash = (currentHash + (ch * basePower) % MOD) % MOD;
        basePower = (basePower * BASE) % MOD;
    }

    // Removes a character from the hash and adjusts basePower
    public void removeCharacter(char ch, int length) {
        currentHash = (currentHash - (ch * modInverse(basePower, MOD)) % MOD + MOD) % MOD;
    }

    // Returns the current hash value
    public int getHash() {
        return currentHash;
    }

    // Computes modular inverse using Fermat's Little Theorem
    private int modInverse(int a, int m) {
        return power(a, m - 2, m);
    }

    // Computes x raised to y modulo m
    private int power(int x, int y, int m) {
        if (y == 0) return 1;
        int p = power(x, y / 2, m) % m;
        p = (p * p) % m;
        return (y % 2 == 0) ? p : (x * p) % m;
    }

    public static void main(String[] args) {
        String text = "example";
        RollingHash rollingHash = new RollingHash();

        // Compute initial hash
        rollingHash.computeInitialHash(text.substring(0, 4));  // Examining "exam"
        System.out.println("Initial hash for 'exam': " + rollingHash.getHash());

        // Roll to next substring "xamp"
        rollingHash.removeCharacter('e', 4);
        rollingHash.addCharacter('p');
        System.out.println("Rolled hash for 'xamp': " + rollingHash.getHash());
    }
}
