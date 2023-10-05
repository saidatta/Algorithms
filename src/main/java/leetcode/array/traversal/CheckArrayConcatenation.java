package leetcode.array.traversal;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/check-array-formation-through-concatenation/
public class CheckArrayConcatenation {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] piece : pieces) {
            map.put(piece[0], piece);
        }

        int i = 0;
        while (i < arr.length) {
            if (!map.containsKey(arr[i])) {
                return false;
            }

            int[] currentPiece = map.get(arr[i]);
            for (int k : currentPiece) {
                if (arr[i] != k) {
                    return false;
                }
                i++;
            }
        }

        return true;
    }
}
