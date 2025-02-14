package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class AmazonSale {
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        // Sort the prices in ascending order
        Collections.sort(prices);

        int itemsRemoved = 0;

        // If the number of items is less than k, no need to remove any items
        if (prices.size() <= k) {
            return itemsRemoved;
        }

        // Iterate through the array from the end
        for (int i = prices.size() - 1; i >= k; i--) {
            // Calculate the sum of the last k items
            int sumLastK = 0;
            for (int j = i; j > i - k; j--) {
                sumLastK += prices.get(j);
            }

            // If the sum exceeds the threshold, remove the highest price item
            if (sumLastK > threshold) {
                itemsRemoved++;
            } else {
                // If the sum is within the threshold, no need to remove more items
                break;
            }
        }

        return itemsRemoved;
    }


    public static void main(String[] args) {
        List<Integer> prices1 = new ArrayList<>(List.of(3, 2, 1, 4, 6, 5));
        int k1 = 3;
        int threshold1 = 14;
//        System.out.println("Minimum removals needed: " + reduceGifts(convertListToIntArray(prices1), k1, threshold1));

        List<Integer> prices2 = new ArrayList<>(List.of(9, 6, 3, 2, 9, 10, 10, 11));
        int k2 = 4;
        int threshold2 = 1;
//        System.out.println("Minimum removals needed: " + reduceGifts(convertListToIntArray(prices2), k2, threshold2));

        List<Integer> prices3 = new ArrayList<>(List.of(9, 6, 7, 2, 7, 2));
        int k3 = 2;
        int threshold3 = 13;
//        System.out.println("Minimum removals needed: " + reduceGifts(convertListToIntArray(prices3), k3, threshold3));
    }

    public static int[] convertListToIntArray(List<Integer> integerList) {
        // Convert List<Integer> to int[] using streams

        return integerList.stream()
                .mapToInt(Integer::intValue) // Convert Integer to int
                .toArray();
    }
}
