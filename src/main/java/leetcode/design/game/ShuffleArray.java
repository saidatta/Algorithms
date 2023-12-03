package leetcode.design.game;

import java.util.Random;

// https://leetcode.com/problems/shuffle-an-array/
public class ShuffleArray {
    private final int[] originalArray;
    private int[] array;
    private final Random rand = new Random();

    public ShuffleArray(int[] nums) {
        originalArray = nums.clone();
        array = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = originalArray.clone();
        return array;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swap(array, i, i + rand.nextInt(array.length - i));
        }
        return array;
    }

    /** Swap helper method */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

//    The constructor initializes the object with the integer array nums.
//    We store a copy of the original array to use in the reset method.

//        The reset method resets the array to its original configuration and returns it.

//        The shuffle method implements the Fisher-Yates shuffle algorithm:
//        It iterates over the array, and for each index i, it swaps the element at i with an element at a random
//        index greater than or equal to i.

//        This ensures that each permutation of the array is equally likely.
//        The swap helper method is used to swap two elements in the array.