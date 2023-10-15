package leetcode.string.two_pointers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/
public class BeautifulShortestSubstrings {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int count = 0; // to count number of 1s in current window
        int left = 0;  // left pointer for sliding window
        int shortestLen = Integer.MAX_VALUE; // track shortest length of beautiful substring

        List<String> candidates = new ArrayList<>();

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                count++;
            }

            while (count > k) { // Adjust the window size
                if (s.charAt(left) == '1') {
                    count--;
                }
                left++;
            }

            if (count == k) {
                while(s.charAt(left) == '0') {
                    left++;
                }
                int len = right - left + 1;
                String candidate = s.substring(left, right + 1);

                if (len < shortestLen) {
                    candidates.clear();
                    shortestLen = len;
                    candidates.add(candidate);
                } else if (len == shortestLen && candidate.compareTo(candidates.get(0)) < 0) {
                    candidates.clear();
                    candidates.add(candidate);
                }
            }
        }

        if (candidates.isEmpty()) {
            return "";
        }
        Collections.sort(candidates);
        return candidates.get(0);
    }

    public static void main(String[] args) {
        BeautifulShortestSubstrings solution = new BeautifulShortestSubstrings();

        // Test cases
        System.out.println(solution.shortestBeautifulSubstring("100011001", 3)); // Expected: "11001"
        System.out.println(solution.shortestBeautifulSubstring("1011", 2));      // Expected: "11"
        System.out.println(solution.shortestBeautifulSubstring("000", 1));      // Expected: ""
    }
}
