package leetcode.array.twoPointer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/
public class RunLengthEncodingProduct {

    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < encoded1.length && j < encoded2.length) {
            int val1 = encoded1[i][0], freq1 = encoded1[i][1];
            int val2 = encoded2[j][0], freq2 = encoded2[j][1];

            int product = val1 * val2;
            int minFreq = Math.min(freq1, freq2);

            if (!result.isEmpty() && result.get(result.size() - 1).get(0) == product) {
                // Combine with the last product if they are the same
                result.get(result.size() - 1).set(1, result.get(result.size() - 1).get(1) + minFreq);
            } else {
                // Add a new product
                result.add(new ArrayList<>(Arrays.asList(product, minFreq)));
            }

            // Update frequencies and advance pointers
            encoded1[i][1] -= minFreq;
            encoded2[j][1] -= minFreq;
            if (encoded1[i][1] == 0) {
                i++;
            }
            if (encoded2[j][1] == 0) {
                j++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        RunLengthEncodingProduct solution = new RunLengthEncodingProduct();
        int[][] encoded1 = {{1, 3}, {2, 1}, {3, 2}};
        int[][] encoded2 = {{2, 3}, {3, 3}};
        List<List<Integer>> result = solution.findRLEArray(encoded1, encoded2);

        // Print the result
        for (List<Integer> pair : result) {
            System.out.println(pair);
        }
    }
}
