package Skiena.Chp3;

/**
 * Created by venkatamunnangi on 12/28/16.
 */
public class MaxHeap<T extends Comparable> extends Heap<T> {

    public static void main(String[] args) throws HeapFullException, HeapEmptyException {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(Integer.class);

        maxHeap.insert(9);
        maxHeap.insert(4);
        maxHeap.insert(17);
        maxHeap.printHeapArray();
        maxHeap.insert(6);
        maxHeap.printHeapArray();

        maxHeap.insert(100);
        maxHeap.insert(20);
        maxHeap.insert(2);
        maxHeap.insert(1);
        maxHeap.printHeapArray();
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.printHeapArray();

        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();
        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();
    }


    public MaxHeap(Class<T> clazz) {
        super(clazz);
    }

    public MaxHeap(Class<T> clazz, int size) {
        super(clazz, size);
    }

    @Override
    protected void siftDown(int index) {
        int leftIndex = getLeftChildIndex(index);
        int rightIndex = getRightChildIndex(index);

        // Find the maximum of the left and right child elements.
        int largerIndex = -1;
        if (leftIndex != -1 && rightIndex != -1) {
            largerIndex = getElementAtIndex(leftIndex).compareTo(getElementAtIndex(rightIndex)) > 0
                    ? leftIndex : rightIndex;
        } else if (leftIndex != -1) {
            largerIndex = leftIndex;
        } else if (rightIndex != -1) {
            largerIndex = rightIndex;
        }

        // If the left and right child do not exist stop sifting down.
        if (largerIndex == -1) {
            return;
        }

        // Compare the larger child with the current index to see if a swap
        // and further sift down is needed.
        if (getElementAtIndex(largerIndex).compareTo(getElementAtIndex(index)) > 0) {
            swap(largerIndex, index);
            siftDown(largerIndex);
        }
    }

    @Override
    protected void siftUp(int index)  {
        int parentIndex = getParentIndex(index);

        if (parentIndex != -1 &&
                getElementAtIndex(index).compareTo(getElementAtIndex(parentIndex)) > 0) {
            swap(parentIndex, index);

            siftUp(parentIndex);
        }
    }

}
