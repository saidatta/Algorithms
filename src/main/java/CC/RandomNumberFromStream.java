package CC;


import java.util.Arrays;
import java.util.Random;

/**
 * This problem was asked by Facebook.
 * Given a stream of elements too large to store in memory, pick a random element from the stream with uniform probability.
 */
public class RandomNumberFromStream {
    public static void main(String[] args) {
        selectRandomItem(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    }
    private static void selectRandomItem(int... dataStream) {
        int n = dataStream.length;

        int randomElement = dataStream[0];

        Random r = new Random();

        for (int i = 1; i < n; i++) {// Iterate from the 2nd element to nth element
            int j = r.nextInt(i + 1); // Pick a random index from 0 to i.

            if (j < 1) {
                // If the randomly  picked index is smaller than 1,
                // probabiliy to get 1 item.
                randomElement = dataStream[i];
            }
        }

        System.out.println("Randomly selected item: "+randomElement);
    }
}
