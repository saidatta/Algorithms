package leetcode.array;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/#/description
 *
 * Created by venkatamunnangi on 4/2/17.
 */
public class KthLargestElementInArray {
    public static int findKthLargest(int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>(k+1);
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public static void main(String [] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }
    public int findKthLargest2(int[] a, int k) {
        int n = a.length;
        // n-k+1 smallest because a typical qs will be ascending.
        int p = quickSelect(a, 0, n - 1, n - k + 1);
        return a[p];
    }

    // return the index of the kth smallest number
    int quickSelect(int[] a, int lo, int hi, int k) {
        // use quick sort's idea
        // put nums that are <= pivot to the left
        // put nums that are  > pivot to the right
        int i = lo, j = hi, pivot = a[hi];
        while (i < j) {
            if (a[i++] > pivot) {
                swap(a, --i, --j);
            }
        }
        swap(a, i, hi);

        // count the nums that are <= pivot from lo
        int m = i - lo + 1;

        // pivot is the one!
        if (m == k) {
            return i;
        } else if (m > k) {
            // pivot is too big, so it must be on the left
            return quickSelect(a, lo, i - 1, k);
        } else {
            // pivot is too small, so it must be on the right
            return quickSelect(a, i + 1, hi, k - m);
        }
    }

    //// O(1) solution - https://leetcode.com/problems/kth-largest-element-in-an-array/editorial/ comment
    private int[] array;
    private final Random randomGenerator = new Random();

    public int findKthLargestQuickSelect(int[] nums, int k) {
        this.array = nums;
        int length = nums.length;

        int left = 0;
        int right = length - 1;

        while (true) {
            int pivotPosition = partition(left, right);

            if (length - k == pivotPosition) {
                return array[pivotPosition];
            } else if (length - k > pivotPosition) {
                left = pivotPosition + 1;
            } else {
                right = pivotPosition - 1;
            }
        }
    }

    // This function chooses a random pivot, partitions the array and returns
    // the position where the pivot is finally placed
    private int partition(int left, int right) {
        int pivotIndex = randomGenerator.nextInt(right - left + 1) + left;
        int pivotValue = array[pivotIndex];
        swap(pivotIndex, right);

        int storePosition = left;
        for (int i = left; i < right; i++) {
            if (array[i] < pivotValue) {
                swap(i, storePosition);
                storePosition++;
            }
        }

        swap(storePosition, right);
        return storePosition;
    }

    // This function swaps two elements in the array
    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
