package Skiena.Chp3;

/**
 * Created by venkatamunnangi on 12/28/16.
 */
public class MinHeap<T extends Comparable> extends Heap<T> {

    public static void main(String[] args) throws HeapFullException, HeapEmptyException {
        MinHeap<Integer> minHeap = new MinHeap<>(Integer.class);

        minHeap.insert(9);
        minHeap.insert(4);
        minHeap.insert(17);
        minHeap.printHeapArray();
        minHeap.insert(6);
        minHeap.printHeapArray();

        minHeap.insert(100);
        minHeap.insert(20);
        minHeap.printHeapArray();
        minHeap.insert(2);
        minHeap.insert(1);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.printHeapArray();

        minHeap.removeHighestPriority();
        minHeap.printHeapArray();
        minHeap.removeHighestPriority();
        minHeap.printHeapArray();
    }

    public MinHeap(Class<T> clazz) {
        super(clazz);
    }

    public MinHeap(Class<T> clazz, int size) {
        super(clazz, size);
    }

    @Override
    protected void siftDown(int index) {
        int leftIndex = getLeftChildIndex(index);
        int rightIndex = getRightChildIndex(index);

        // Find the minimum of the left and right child elements.
        int smallerIndex = -1;
        if (leftIndex != -1 && rightIndex != -1) {
            smallerIndex = getElementAtIndex(leftIndex).compareTo(getElementAtIndex(rightIndex)) < 0
                    ? leftIndex : rightIndex;
        } else if (leftIndex != -1) {
            smallerIndex = leftIndex;
        } else if (rightIndex != -1) {
            smallerIndex = rightIndex;
        }

        // If the left and right child do not exist stop sifting down.
        if (smallerIndex == -1) {
            return;
        }

        // Compare the smaller child with the current index to see if a swap
        // and further sift down is needed.
        if (getElementAtIndex(smallerIndex).compareTo(getElementAtIndex(index)) < 0) {
            swap(smallerIndex, index);
            siftDown(smallerIndex);
        }
    }

    @Override
    protected void siftUp(int index)  {
        int parentIndex = getParentIndex(index);

        if (parentIndex != -1 &&
                getElementAtIndex(index).compareTo(getElementAtIndex(parentIndex)) < 0) {
            swap(parentIndex, index);

            siftUp(parentIndex);
        }
    }
}

