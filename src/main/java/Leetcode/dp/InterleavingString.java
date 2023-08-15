package Leetcode.dp;

/**
 * https://leetcode.com/problems/interleaving-string/#/description
 * <p>
 * Created by venkatamunnangi on 4/23/17.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        return isInterleaved(s1.toCharArray(), s2.toCharArray(), s3.toCharArray());
    }

    public boolean isInterleaved(char str1[], char str2[], char str3[]) {
        boolean[][] T = new boolean[str1.length + 1][str2.length + 1];

        // santization
        if (str1.length + str2.length != str3.length) {
            return false;
        }

        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[i].length; j++) {
                int l = i + j - 1;
                if (i == 0 && j == 0) {
                    T[i][j] = true;
                } else if (i == 0) {
                    if (str3[l] == str2[j - 1]) {
                        T[i][j] = T[i][j - 1];
                    }
                } else if (j == 0) {
                    if (str1[i - 1] == str3[l]) {
                        T[i][j] = T[i - 1][j];
                    }
                } else {
                    T[i][j] = ((str1[i - 1] == str3[l]) && T[i - 1][j]) || ((str2[j - 1] == str3[l]) && T[i][j - 1]);
                }
            }
        }
        return T[str1.length][str2.length];
    }

    public static void main(String [] args) {
        InterleavingString interleavingString = new InterleavingString();

        System.out.println(interleavingString.isInterleave("a", "b", "a"));
    }
}
