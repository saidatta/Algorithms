package Skiena.Chp4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by venkatamunnangi on 12/29/16.
 */
public class Mergesort {
    public void sort(int [] numbers) {
        mergeSort(numbers, 0, numbers.length -1);
    }

    public void mergeSort(int [] nums, int low, int high) {
        if(low < high) {
            int middle = (low + high) / 2;
            mergeSort(nums, low, middle);
            mergeSort(nums, middle +1, high);

            merge(nums, low, middle, high);
        }
    }

    public void merge(int[] nums, int low, int mid, int high) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for(int x = low;x <= mid; x++ ) {
            q1.offer(nums[x]);
        }

        for(int y = mid+1;y <= high; y++ ) {
            q2.offer(nums[y]);
        }

        int i = low;
        while(!q1.isEmpty() && !q2.isEmpty()) {
            if(q1.peek() <= q2.peek()) {
                nums[i++] = q1.poll();
            } else {
                nums[i++] = q2.poll();
            }
        }

        while(!q1.isEmpty()) {
            nums[i++] = q1.poll();
        }

        while(!q2.isEmpty()) {
            nums[i++] = q2.poll();
        }
    }

    public static void main(String [] args) {
        int [] xxx = new int[]{2,10,1,9,4,21};

        Mergesort ms = new Mergesort();

        ms.sort(xxx);

        for(int i : xxx) {
            System.out.println(i);
        }
    }
}
