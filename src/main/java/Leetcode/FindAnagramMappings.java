package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class FindAnagramMappings {
    public int[] anagramMappings(int[] A, int[] B) {

        Map<Integer, Integer> dict = new HashMap<>();
        int[] result = new int[A.length];

        for (int i =0 ; i< A.length; i++) {
            dict.putIfAbsent(B[i], i);
        }

        for(int i =0; i<B.length;i++) {
            result[i] = dict.get(A[i]);
        }

        return result;
    }
}
