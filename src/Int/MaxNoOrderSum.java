package Int;

/**
 * Deepthi's Question
 * Find k max numbers within an array of size n.
 *
 * Created by venkatamunnangi on 2/15/17.
 */
public class MaxNoOrderSum {

    public void solution(int [] numbers, int k, MinHeap myHeap) {
        if(k > numbers.length) {
            // Error u cant have k numbers in (k>n) array.
            return;
        }

        int totalFound = 0;
        for (int num : numbers)
        {
            if (totalFound < k) {
                //n add this number without any pre-conditions
                myHeap.insert(num);
                totalFound++;
            } else if (num > myHeap.peek()) {
                // If this number is larger than the smallest
                // number already on the heap,
                // then remove the smallest number and add this one
                myHeap.remove();
                totalFound--;
                myHeap.insert(num);
                totalFound++;
            }
        }

    // At this point, the max numbers are on the heap,
    // but there might be too many of them.
    // Remove the smallest number until we have k numbers.
        while ((totalFound - myHeap.peek()) > k) {
            myHeap.remove();
            totalFound--;
        }
    // The heap now contains the maximum numbers within the data structure.
    }

    public static void main(String [] args) {

        MaxNoOrderSum maxNoOrderSum = new MaxNoOrderSum();

        int [] array = {1,4,11,2,9};
        int k = 3;

        MinHeap myHeap = new MinHeap(array.length);
        maxNoOrderSum.solution(array, k, myHeap);
        myHeap.print();

        System.out.println("---------");

        int [] negativeArray = {1,-10, -5, 4, 2,-11};
        myHeap = new MinHeap(negativeArray.length);
        maxNoOrderSum.solution(negativeArray, k, myHeap);
        myHeap.print();

        System.out.println("-------Edge cases------");

        int [] twoNumbers = {1,-10};
        k = 4;
        myHeap = new MinHeap(twoNumbers.length);
        maxNoOrderSum.solution(twoNumbers, k, myHeap);
        myHeap.print();
    }
}
