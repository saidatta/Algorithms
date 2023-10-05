package leetcode.array.sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RankTransformArray {
    public int[] arrayRankTransform(int[] arr) {
        // Create a copy of the original array to rank them
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);

        Map<Integer, Integer> numberToRank = new HashMap<>();

        // Assign ranks to each unique number
        int currentRank = 0;
        for (int num : sortedArr) {
            // If the number is not ranked yet, assign it the next rank
            if (!numberToRank.containsKey(num)) {
                currentRank++;
                numberToRank.put(num, currentRank);
            }
        }

        // Replace numbers in the original array with their ranks
        for (int i = 0; i < arr.length; i++) {
            arr[i] = numberToRank.get(arr[i]);
        }

        return arr;
    }

    public int[] arrayRankTransform2(int[] arr) {
        // Create a sorted copy with unique elements
        int[] sortedUnique = Arrays.stream(arr)
                .boxed()
                .sorted()
                .distinct()
                .mapToInt(Integer::intValue)
                .toArray();

        // Store the rank for each unique element
        Map<Integer, Integer> rank = new HashMap<>();
        for (int i = 0; i < sortedUnique.length; i++) {
            rank.put(sortedUnique[i], i + 1);
        }

        // Replace the elements in arr with their rank
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rank.get(arr[i]);
        }

        return arr;
    }

}

