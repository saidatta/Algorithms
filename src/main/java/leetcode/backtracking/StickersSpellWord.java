package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/stickers-to-spell-word/description/
public class StickersSpellWord {
    public int minStickers(String[] stickers, String target) {
        // Convert the target string to a character count
        int[] targetCount = new int[26];
        for (char ch : target.toCharArray()) {
            targetCount[ch - 'a']++;
        }

        // Convert stickers to character counts and filter by usefulness
        List<int[]> stickerCounts = new ArrayList<>();
        for (String sticker : stickers) {
            int[] count = new int[26];
            for (char ch : sticker.toCharArray()) {
                if (target.indexOf(ch) != -1) {
                    count[ch - 'a']++;
                }
            }
            for (int val : count) {
                if (val > 0) {
                    stickerCounts.add(count);
                    break;
                }
            }
        }

        // Sort stickerCounts by their usefulness
        stickerCounts.sort((a, b) -> {
            int scoreA = 0, scoreB = 0;
            for (int i = 0; i < 26; i++) {
                scoreA += a[i] * targetCount[i];
                scoreB += b[i] * targetCount[i];
            }
            return scoreB - scoreA;
        });

        if (stickerCounts.size() == 0) return -1;

        // Memoization table
        Map<String, Integer> memo = new HashMap<>();

        return helper(targetCount, stickerCounts, memo);
    }

    private int helper(int[] targetCount, List<int[]> stickerCounts, Map<String, Integer> memo) {
        String key = Arrays.toString(targetCount);
        if (memo.containsKey(key)) return memo.get(key);
        if (Arrays.stream(targetCount).allMatch(val -> val == 0)) return 0;

        int result = Integer.MAX_VALUE;
        for (int[] sticker : stickerCounts) {
            if (sticker[findFirstPositiveIndex(targetCount)] > 0) {
                int[] newTargetCount = new int[26];
                for (int i = 0; i < 26; i++) {
                    newTargetCount[i] = Math.max(0, targetCount[i] - sticker[i]);
                }
                int tempResult = helper(newTargetCount, stickerCounts, memo);
                if (tempResult != -1) {
                    result = Math.min(result, 1 + tempResult);
                }
            }
        }

        memo.put(key, result == Integer.MAX_VALUE ? -1 : result);
        return memo.get(key);
    }

    private int findFirstPositiveIndex(int[] targetCount) {
        for (int i = 0; i < targetCount.length; i++) {
            if (targetCount[i] > 0) {
                return i;
            }
        }
        return -1;
    }
}
