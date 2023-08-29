package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/#/description
 *
 * Created by venkatamunnangi on 4/4/17.
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        // Backtracking
        // Edge case
        if(s == null || s.length() == 0) {
            return new ArrayList<>();
        }

        List<List<String>> result = new ArrayList<>();
        helper(s, new ArrayList<>(), result);
        return result;
    }

    public void helper(String s, List<String> step, List<List<String>> result) {
        // Base case
        if(s == null || s.length() == 0) {
            result.add(new ArrayList<>(step));
            return;
        }
        for(int i = 1; i <= s.length(); i++) {
            String leftSideString = s.substring(0, i);
            if(!isPalindrome(leftSideString)) {
                continue; // only do backtracking when current string is palindrome
            }

            step.add(leftSideString);  // choose
            helper(s.substring(i), step, result); // explore
            step.remove(step.size() - 1); // unchoose
        }
    }

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while(left <= right) {

            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }
}
