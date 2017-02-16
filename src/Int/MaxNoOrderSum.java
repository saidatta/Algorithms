package Int;

/**
 * Created by venkatamunnangi on 2/15/17.
 */
public class MaxNoOrderSum {

    public void solution(int [] numbers, int k, MinHeap myHeap) {
        int totalFound = 0;
        // this creates an empty heap!
        for (int num : numbers)
        {
            if (totalFound < k) {
                // unconditionally add this passenger
                myHeap.insert(num);
                totalFound++;
            } else if (num > myHeap.peek()) {
                // If this passenger is heavier than the lightest
                // passenger already on the heap,
                // then remove the lightest passenger and add this one
                int oldNum = myHeap.remove();
                totalFound--;
                //totalFound -= oldNum;
                myHeap.insert(num);
                totalFound++;
                //totalFound += num;
            }
        }

    // At this point, the heaviest people are on the heap,
    // but there might be too many of them.
    // Remove the lighter people until we have the minimum necessary
        while ((totalFound - myHeap.peek()) > k) {
            myHeap.remove();
            totalFound--;
        }
    // The heap now contains the passengers who will be thrown overboard.
    }

    public static void main(String [] args) {

        MaxNoOrderSum maxNoOrderSum = new MaxNoOrderSum();

        int [] array = {1,4,11,2,9};
        int k = 4;

        MinHeap myHeap = new MinHeap(array.length); /* need comparer here to order by weight */

        maxNoOrderSum.solution(array, k, myHeap);
        myHeap.print();
    }
}
