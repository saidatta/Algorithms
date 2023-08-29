package leetcode.string;

/**
 * https://leetcode.com/problems/buddy-strings/
 */
public class BuddyStrings {
    //abcd cbad
    public static boolean buddyStrings(String s, String goal) {
        if (s.equals(goal)) {
            return hasDuplicateCharacters(s);
        } else if (s.length() != goal.length()) {
            return false;
        } else {
            return canBeMadeEqualBySingleSwap(s, goal);
        }
    }

    // Checks if a string has any duplicate characters
    private static boolean hasDuplicateCharacters(String s) {
        return s.chars().distinct().count() < s.length();
    }

    // Determines if two strings of equal length can be made equal by a single swap
    private static boolean canBeMadeEqualBySingleSwap(String s, String goal) {
        char[] sChars = s.toCharArray();
        char[] goalChars = goal.toCharArray();
        int diffCount = 0;
        char[] diffChars = new char[4];

        for (int i = 0; i < s.length(); i++) {
            if (sChars[i] != goalChars[i]) {
                if (diffCount >= 4) {
                    return false;
                }
                diffChars[diffCount++] = sChars[i];
                diffChars[diffCount++] = goalChars[i];
            }
        }

        return diffCount == 4 && diffChars[0] == diffChars[3] && diffChars[1] == diffChars[2];
    }

//    bool buddyStrings(string s, string goal) {
//        int n = s.length();
//        if(s == goal){
//            set<char> temp(s.begin(), s.end());
//            return temp.size() < goal.size(); // Swapping same characters
//        }
//
//        int i = 0;
//        int j = n - 1;
//
//        while(i < j && s[i] == goal[i]){
//            i++;
//        }
//
//        while(j >= 0 && s[j] == goal[j]){
//            j--;
//        }
//
//        if(i < j){
//            swap(s[i], s[j]);
//        }
//
//        return s == goal;
//    }
}
