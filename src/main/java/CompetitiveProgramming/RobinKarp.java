package CompetitiveProgramming;

public class RobinKarp {

    private static final int BASE = 31;
    private static final int MOD = 1_000_000_007;

    public static void search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        int patternHash = 0;
        int textHash = 0;
        int basePower = 1;

        // Calculate the initial hashes
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash + pattern.charAt(i) * basePower) % MOD;
            textHash = (textHash + text.charAt(i) * basePower) % MOD;
            if (i < m - 1) basePower = (basePower * BASE) % MOD;
        }

        // Slide over the text
        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash) {
                // Compare character by character
                boolean isMatch = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    System.out.println("Pattern found at index: " + i);
                }
            }

            // Roll the hash for the next window in the text
            if (i < n - m) {
                textHash = (textHash - text.charAt(i) * basePower) % MOD;
                if (textHash < 0) textHash += MOD;

                textHash = (textHash * BASE + text.charAt(i + m)) % MOD;
            }
        }
    }

    public static void main(String[] args) {
        String text = "abcababcabcab";
        String pattern = "abcab";
        search(text, pattern);
    }
}
