package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-anagram-mappings/
 */
public class FindAnagramMappings {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> dict = new HashMap<>();
        int[] result = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            // store # -> A index location
            dict.putIfAbsent(B[i], i);
        }

        for (int i = 0; i < B.length; i++) {
            result[i] = dict.get(A[i]);
        }

        return result;
    }
}
