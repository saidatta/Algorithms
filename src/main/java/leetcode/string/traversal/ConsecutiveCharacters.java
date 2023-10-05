package leetcode.string.traversal;

// https://leetcode.com/problems/consecutive-characters/
public class ConsecutiveCharacters {
    public int maxPower(String s) {
        if (s == null || s.length() == 0) return 0;

        int maxPower = 1;  // maximum length of consecutive characters
        int currentPower = 1;  // current length of consecutive characters

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                // If current character is same as previous, increase currentPower
                currentPower++;
            } else {
                // If current character is different, update maxPower and reset currentPower
                maxPower = Math.max(maxPower, currentPower);
                currentPower = 1;
            }
        }

        // Handle the case where the longest substring is at the end
        maxPower = Math.max(maxPower, currentPower);

        return maxPower;
    }
}
