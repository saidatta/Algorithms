package Leetcode.dp.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ArrayStrictlyIncreasing {
    private static final double INF = Double.POSITIVE_INFINITY;

    //    Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
//    Output: 2
//    Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);

        Map<Integer, Double> previousMap = new HashMap<>();
        previousMap.put(0, -INF);

        for (int num : arr1) {
            Map<Integer, Double> currentMap = new HashMap<>();

            for (int nSwap : previousMap.keySet()) {
                double prevNum = previousMap.get(nSwap);
                if (num > prevNum) {
                    currentMap.put(nSwap, Math.min(currentMap.getOrDefault(nSwap, INF), num));
                }
                int loc = Arrays.binarySearch(arr2, (int) (prevNum + 1));
                if (loc < 0) {
                    loc = -(loc + 1); // Insertion point
                }
                if (loc < arr2.length) {
                    currentMap.put(nSwap + 1, Math.min(currentMap.getOrDefault(nSwap + 1, INF), arr2[loc]));
                }
            }

            if (currentMap.isEmpty()) {
                return -1;
            }
            previousMap = currentMap;
        }

        return Collections.min(previousMap.keySet());
    }

    public static void main(String[] args) {
        ArrayStrictlyIncreasing arrayIncreaser = new ArrayStrictlyIncreasing();
        int[] arr1 = {1, 5, 3, 6, 7};
        int[] arr2 = {1, 3, 2, 4};
        int minOperations = arrayIncreaser.makeArrayIncreasing(arr1, arr2);
        System.out.println("Minimum operations required: " + minOperations);
    }
}
