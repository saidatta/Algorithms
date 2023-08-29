package leetcode.greedy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/put-marbles-in-bags/description/
 */
// The score for each bag of marbles is calculated as the sum of the weights of the first and last marble in the bag.
//  This means that the minimum score for a bag is the sum of the two smallest weights, and the maximum score for a bag
//  is the sum of the two largest weights.

public class PutMarblesBag {

//    Input: weights = [1,3,5,1], k = 2
//    Output: 4
//    Explanation:
//    The distribution [1],[3,5,1] results in the minimal score of (1+1) + (3+1) = 6.
//    The distribution [1,3],[5,1], results in the maximal score of (1+3) + (5+1) = 10.
//    Thus, we return their difference 10 - 6 = 4.
//
//    Input: weights = [1, 3], k = 2
//    Output: 0
//    Explanation: The only distribution possible is [1],[3].
//    Since both the maximal and minimal score are the same, we return 0.
    public long putMarbles(int[] marbleWeights, int totalBags) {
        // Get the number of marbles
        int n = marbleWeights.length;


        // Create an ArrayList to store the sums of adjacent marble weights
        List<Integer> adjacentMarbleWeights = new ArrayList<>();

        // Calculate the sum of adjacent weights and store them in adjacentMarbleWeights
        // size - n-1;
        for (int i = 0; i < n - 1; i++) {
            adjacentMarbleWeights.add(marbleWeights[i] + marbleWeights[i + 1]);
        }

        // 4 8 6 -> 4 6 8; 4; 6-6; 4-8 =
        // Sort the sums in ascending order
        Collections.sort(adjacentMarbleWeights);

        // Initialize the difference between the maximum and minimum scores
        long scoreDifference = 0;

        // Iterate over the sums. For each iteration, calculate the difference
        // between the current maximum and minimum possible scores and add it to scoreDifference
        for (int i = 0; i < totalBags - 1; i++) {
            scoreDifference += adjacentMarbleWeights.get(adjacentMarbleWeights.size() - 1 - i) - adjacentMarbleWeights.get(i);
        }

        // Return the total difference between the maximum and minimum scores
        return scoreDifference;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        int numProcessors = Runtime.getRuntime().availableProcessors();
//        Unsafe unsafe = Unsafe.getUnsafe();
        Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe"); //Internal reference
        f.setAccessible(true);
        sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
        int[] maxVectorLength = new int[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            maxVectorLength[i] = unsafe.getByte(unsafe.arrayBaseOffset(sun.misc.Unsafe.class) + 12 + (i * 8));
        }

        for (int i = 0; i < numProcessors; i++) {
            if (maxVectorLength[i] >= 128) {
                System.out.println("Core " + i + " supports AVX2");
            }
            if (maxVectorLength[i] >= 256) {
                System.out.println("Core " + i + " supports AVX-512");
            }
        }
    }
}
