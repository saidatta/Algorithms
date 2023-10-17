package leetcode.backtracking.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/#/description
 *
 * Created by venkatamunnangi on 4/4/17.
 */
public class PalindromePartitioning {
    /**
     * Returns all possible palindrome partitions of a given string.
     *
     * @param s the input string
     * @return a list of all possible palindrome partitions
     */
    public List<List<String>> partition(String s) {
        // Edge case
        if (s == null || s.isEmpty()) {
            return new ArrayList<>();
        }

        List<List<String>> result = new ArrayList<>();
        findPartitions(s, new ArrayList<>(), result);
        return result;
    }

    /**
     * A recursive helper method that finds palindrome partitions.
     *
     * @param s      the remaining portion of the string to be partitioned
     * @param path   the current palindrome path being explored
     * @param result the final list of all palindrome partitions
     */
    private void findPartitions(String s, List<String> path, List<List<String>> result) {
        if (s.isEmpty()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 1; i <= s.length(); i++) {
            String current = s.substring(0, i);

            if (isPalindrome(current)) {
                path.add(current);  // Choose
                findPartitions(s.substring(i), path, result); // Explore
                path.remove(path.size() - 1); // Unchoose
            }
        }
    }

    /**
     * Checks if a given string is a palindrome.
     *
     * @param s the input string
     * @return true if the string is a palindrome, false otherwise
     */
    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning partitioner = new PalindromePartitioning();

        // Test cases
        String test1 = "aab";
        String test2 = "racecar";
        String test3 = "abc";

        // Display the results
        System.out.println("Partitions for " + test1 + " : " + partitioner.partition(test1));
        System.out.println("Partitions for " + test2 + " : " + partitioner.partition(test2));
        System.out.println("Partitions for " + test3 + " : " + partitioner.partition(test3));
    }

}
