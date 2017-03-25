package Int;

import java.util.PriorityQueue;

/**
 * Deepthi's Question
 * Find k max numbers within an array of size n.
 *
 * Created by venkatamunnangi on 2/15/17.
 */
public class MaxNoOrderSum {

  // Running time should be proportional to n log k, where n is the number of numbers and k is the maximum number of items on the heap
    public void solution(int [] numbers, int k, PriorityQueue<Integer> myHeap) {
        if(k > numbers.length) {
            // Error u cant have k numbers in (k>n) array.
            return;
        }

        int totalFound = 0;
        for (int num : numbers)
        {
            if (totalFound < k) {
                //add this number no matter what. Since, we need to totalFound is less than the target(k)
                myHeap.add(num);
                totalFound++;
            } else if (num > myHeap.peek()) {
                // If this number is larger than the smallest
                // number already on the heap,
                // then remove the smallest number and add this one
                myHeap.remove();
                totalFound--;
                myHeap.add(num);
                totalFound++;
            }
        }

    // At this point, the max numbers are on the heap,
    // but there might be too many of them.
    // Remove the smallest number until we have k numbers.
        while ((totalFound - myHeap.size()) > k) {
            myHeap.remove();
            totalFound--;
        }
    // The heap now contains the maximum numbers within the data structure.
    }

    public static void main(String [] args) {

        MaxNoOrderSum maxNoOrderSum = new MaxNoOrderSum();

        int [] array = {1,4,11,2,9};
        int k = 3;

        PriorityQueue<Integer> myHeap = new PriorityQueue<Integer>(array.length);
        maxNoOrderSum.solution(array, k, myHeap);
        maxNoOrderSum.print(myHeap);

        System.out.println("---------");

        int [] negativeArray = {1,-10, -5, 4, 2,-11};
        myHeap = new PriorityQueue<Integer>(negativeArray.length);
        maxNoOrderSum.solution(negativeArray, k, myHeap);
        maxNoOrderSum.print(myHeap);


        System.out.println("-------");
        int [] numbers = {5,3,17,10,84,19,6,22,9};
        myHeap = new PriorityQueue<Integer>(numbers.length);
        maxNoOrderSum.solution(numbers, k, myHeap);
        maxNoOrderSum.print(myHeap);

        System.out.println("-------Edge cases------");

        int [] twoNumbers = {1,-10};
        k = 4;
        myHeap = new PriorityQueue<Integer>(twoNumbers.length);
        maxNoOrderSum.solution(twoNumbers, k, myHeap);
        maxNoOrderSum.print(myHeap);
    }

    void print(PriorityQueue<Integer> pq) {
        for(int i : pq) {
            System.out.println(i);
        }
    }
}
