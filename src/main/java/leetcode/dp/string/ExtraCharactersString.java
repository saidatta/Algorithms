package leetcode.dp.string;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/extra-characters-in-a-string/description/?envType=daily-question&envId=2023-09-02
public class ExtraCharactersString {
    public static int minExtraCharacters(String s, String[] dictionary) {
        Set<String> wordSet = new HashSet<>();
        Collections.addAll(wordSet, dictionary);

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;  // consider current character as extra by default
            for (int j = 0; j < i; j++) {
                if (wordSet.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        String s1 = "leetscode";
        String[] dictionary1 = {"leet", "code", "leetcode"};
        System.out.println(minExtraCharacters(s1, dictionary1));  // Output: 1

        String s2 = "sayhelloworld";
        String[] dictionary2 = {"hello", "world"};
        System.out.println(minExtraCharacters(s2, dictionary2));  // Output: 3
    }
}

