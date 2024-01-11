package leetcode.greedy.array;

import java.util.Arrays;

// https://leetcode.com/problems/assign-cookies
// https://www.youtube.com/watch?v=JW8fgvoxPTg
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); // Sort the greed factors of children
        Arrays.sort(s); // Sort the sizes of cookies

        int contentChildren = 0;
        int cookieIndex = 0;

        // Iterate over each child
        for (int greed : g) {
            // Find a cookie that can satisfy the child's greed
            while (cookieIndex < s.length && s[cookieIndex] < greed) {
                cookieIndex++;
            }
            // If a cookie is found, assign it and move to the next child
            if (cookieIndex < s.length) {
                contentChildren++;
                cookieIndex++;
            } else {
                // No more cookies available
                break;
            }
        }

        return contentChildren;
    }
}

