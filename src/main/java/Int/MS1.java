package Int;

/**
 * Created by venkatamunnangi on 9/16/19.
 */
public class MS1 {
        //[3, 4, -1, 1] - 2
        // 1, 2, 0 - 3

    public int firstMissingPositive(int[] array) {
        if(array == null || array.length < 1 ) {
            return -1;
        }

        int positveNumsCount = 0;
        for(int x : array) {
            if(x>0) {
                positveNumsCount++;
            }
        }

        for(int i = 0; i < array.length; i++) {

            int currentNum = array[i];

            // if array is index + 1, then dont need to swap.
            int idealNumber =  i+1;

            // Idea is that we use array indicies as buckets for appropriate numbers and discard the negative numbers.
            if(currentNum != idealNumber && (currentNum <= positveNumsCount) && array[currentNum-1] != array[i]  ) {
                // if a number is missing, and currentNumber is less than or equal to Total Positive &&  non-duplicates
                // we swap the numbers with the approiate index.

                swapElements(array, i, currentNum-1);
            }
        }


        for(int i = 0; i < positveNumsCount; i++ ) {
            if(array[i] != (i+1)) {
                return i;
            }
        }

        // if all numbers exist, then the missing number is final number of the array + 1.
        // example [1,2,3], answer is 4.
        return positveNumsCount+1;

    }

    private void swapElements(int [] array, int index, int destinationTarget) {

        int temp = array[index];
        array[index] = array[destinationTarget];
        array[destinationTarget] = temp;
    }
}

