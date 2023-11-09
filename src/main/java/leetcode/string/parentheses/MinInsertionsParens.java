package leetcode.string.parentheses;

//https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/solutions/780199/java-c-python-straight-forward-one-pass/
public class MinInsertionsParens {
    /**
     * Finds the minimum number of insertions to balance a parentheses string.
     *
     * @param s the input parentheses string
     * @return the minimum number of insertions needed to balance the string
     */
    public int minInsertions(String s) {
        // Counter for the insertions needed
        int insertionsNeeded = 0;
        int pendingRights = 0; // Counter for the pending right parentheses needed

        // Loop through each character in the string
        for (int i = 0; i < s.length(); ++i) {
            // If the current character is an opening parenthesis
            if (s.charAt(i) == '(') {
                // Check if there is a pending right parenthesis which is odd,
                // this means we need to insert one right parenthesis to balance the previous one
                if (pendingRights % 2 != 0) {
                    // One right parenthesis can now be considered balanced
                    pendingRights--;
                    // Increment insertions as we need to add one
                    insertionsNeeded++;
                }
                pendingRights += 2; // Two right parentheses needed for every opening parenthesis
            } else {
                // If the current character is a closing parenthesis
                // One right parenthesis is now considered balanced
                pendingRights--;
                // If pendingRights is negative, we have an extra right parenthesis that needs to be balanced
                // by an opening one
                if (pendingRights < 0) {
                    pendingRights += 2; // Two rights are now pending
                    insertionsNeeded++; // Increment insertions as we need to add an opening parenthesis
                }
            }
        }

        // Total insertions are the sum of insertions needed plus any pending right parentheses
        return insertionsNeeded + pendingRights;
    }

    public static void main(String[] args) {
        MinInsertionsParens balancer = new MinInsertionsParens();
//        String test1 = "(()))";
//        String test2 = "())";
        String test3 = "))())(";

//        System.out.println(test1 + ": " + balancer.minInsertions(test1)); // Output should be 1
//        System.out.println(test2 + ": " + balancer.minInsertions(test2)); // Output should be 0
        System.out.println(test3 + ": " + balancer.minInsertions(test3)); // Output should be 3
    }
}
