package leetcode.dp;

import static java.lang.System.out;

/**
 * Time complexity is O(m*n)
 * Space complexity is O(m*n)
 * https://leetcode.com/problems/edit-distance/description/
 *
 * Created by venkatamunnangi on 3/3/17.
 */
public class EditDistance {
    public int minDistance(String s1, String s2) {
        return recursiveEditDistance(s1.toCharArray(), s2.toCharArray(), 0, 0);
    }

    /**
     * Calculate the minimum edit distance (Levenshtein distance) between two strings using recursion.
     * Determines the number of insertions, deletions, and substitutions needed to transform one string into another.
     *
     * @param s1 First string as character array
     * @param s2 Second string as character array
     * @param index1 Current index in the first string
     * @param index2 Current index in the second string
     * @return Minimum edit distance between the two strings from the current indices
     */
    public int recursiveEditDistance(char[] s1, char[] s2, int index1, int index2) {

        // If we've reached the end of s1, the distance is the number of characters left in s2
        if (index1 == s1.length) {
            return s2.length - index2;
        }

        // If we've reached the end of s2, the distance is the number of characters left in s1
        if (index2 == s2.length) {
            return s1.length - index1;
        }

        // If characters at the current position are the same, no operation is needed.
        // Otherwise, a substitution is needed.
        int substitutionCost = (s1[index1] == s2[index2]) ? 0 : 1;

        int substitute = recursiveEditDistance(s1, s2, index1 + 1, index2 + 1) + substitutionCost;
        int insert = recursiveEditDistance(s1, s2, index1, index2 + 1) + 1;
        int delete = recursiveEditDistance(s1, s2, index1 + 1, index2) + 1;

        // Return the minimum of the three operations
        return Math.min(substitute, Math.min(insert, delete));
    }


    /**
     * Calculate the minimum edit distance (Levenshtein distance) between two strings using dynamic programming.
     * Determines the number of insertions, deletions, and substitutions needed to transform one string into another.
     *
     * @param s1 First string
     * @param s2 Second string
     * @return Minimum edit distance between the two strings
     */
    public int computeEditDistance(String s1, String s2) {

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        // Create a 2D table to store edit distances between substrings.
        // temp[i][j] represents the edit distance between the first i characters of s1 and the first j characters of s2.
        int[][] temp = new int[chars1.length + 1][chars2.length + 1];

        // Initialize the first column - transforming a non-empty s1 into an empty s2 requires deleting all characters
        // in s1
        for (int i = 0; i <= chars1.length; i++) {
            temp[i][0] = i;
        }

        // Initialize the first row - transforming an empty s1 into a non-empty s2 requires inserting all characters of
        // s2
        for (int j = 0; j <= chars2.length; j++) {
            temp[0][j] = j;
        }

        // Fill in the table using a bottom-up approach
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                // If characters at the current position in both strings are the same, no operation is needed
                if (chars1[i - 1] == chars2[j - 1]) {
                    temp[i][j] = temp[i - 1][j - 1];
                } else {
                    // Calculate the minimum of three operations: substitution, insertion, and deletion
                    int substitute = temp[i - 1][j - 1];
                    int insert = temp[i][j - 1];
                    int delete = temp[i - 1][j];
                    temp[i][j] = 1 + Math.min(substitute, Math.min(insert, delete));
                }
            }
        }

        // The final value represents the edit distance for the full strings
        return temp[chars1.length][chars2.length];
    }

    /**
     * Prints the actual edits which needs to be done.
     */
    public void printActualEdits(int[][] T, char[] str1, char[] str2) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        while (i != 0 && j != 0) {
            if (str1[i - 1] == str2[j - 1]) {
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j - 1] + 1) {
                out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j] + 1) {
                out.println("Delete in string1 " + str1[i - 1]);
                i = i - 1;
            } else if (T[i][j] == T[i][j - 1] + 1) {
                out.println("Delete in string2 " + str2[j - 1]);
                j = j - 1;
            } else {
                throw new IllegalArgumentException("Something wrong with given data");
            }

        }
    }

    private int min(int a, int b, int c) {
        int l = Math.min(a, b);
        return Math.min(l, c);
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();

        String str1 = "azced";
        String str2 = "abcdef";
        out.print(editDistance.minDistance(str1, str2));

        str1 = "abcd";
        str2 = "dbfd";
        out.println(editDistance.computeEditDistance(str1, str2));
    }
}
