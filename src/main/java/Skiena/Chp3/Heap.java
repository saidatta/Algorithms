package Skiena.Chp3;

import java.lang.reflect.Array;

import static java.lang.System.*;

/**
 * Generic Heap implementation.
 *
 * @param <T> the type of elements in the heap, which must be Comparable
 */
public abstract class Heap<T extends Comparable> {
    private static final int DEFAULT_SIZE = 40;
    private final T[] array;
    private int count = 0;

    /**
     * Constructs a heap with default maximum size.
     *
     * @param clazz the class of the heap elements
     */
    public Heap(Class<T> clazz) {
        this(clazz, DEFAULT_SIZE);
    }

    /**
     * Constructs a heap with the specified maximum size.
     *
     * @param clazz the class of the heap elements
     * @param size the maximum size of the heap
     */
    public Heap(Class<T> clazz, int size) {
        array = (T[]) Array.newInstance(clazz, size);
    }

    /**
     * Prints the elements of the heap array and the highest priority element.
     */
    public void printHeapArray() {
        for (int i = 0; i < count; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();

        try {
            System.out.println("Highest priority element: " + getHighestPriority());
        } catch (HeapEmptyException ignored) {
        }
    }

    /**
     * Returns the index of the parent of the element at the specified index.
     *
     * @param index the index of the element
     * @return the index of the parent or -1 if index is out of bounds
     */
    public int getParentIndex(int index) {
        if (index < 0 || index > count) {
            return -1;
        }

        return (index - 1) / 2;
    }

    /**
     * Returns the index of the left child of the element at the specified index.
     *
     * @param index the index of the element
     * @return the index of the left child or -1 if no child exists
     */
    public int getLeftChildIndex(int index) {
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex >= count) {
            return -1;
        }

        return leftChildIndex;
    }

    /**
     * Returns the index of the right child of the element at the specified index.
     *
     * @param index the index of the element
     * @return the index of the right child or -1 if no child exists
     */
    public int getRightChildIndex(int index) {
        int rightChildIndex = 2 * index + 2;
        if (rightChildIndex >= count) {
            return -1;
        }

        return rightChildIndex;
    }

    /**
     * Retrieves the highest priority element from the heap.
     *
     * @return the highest priority element
     * @throws HeapEmptyException if the heap is empty
     */
    public T getHighestPriority() throws HeapEmptyException {
        if (count == 0) {
            throw new HeapEmptyException();
        }

        return array[0];
    }

    /**
     * Inserts a new value into the heap.
     *
     * @param value the value to insert
     * @throws HeapFullException if the heap is full
     */
    public void insert(T value) throws HeapFullException {
        if (count >= array.length) {
            throw new HeapFullException();
        }

        array[count] = value;
        siftUp(count);
        count++;
    }

    /**
     * Removes and returns the highest priority element from the heap.
     *
     * @return the removed element
     * @throws HeapEmptyException if the heap is empty
     */
    public T removeHighestPriority() throws HeapEmptyException {
        T min = getHighestPriority();

        array[0] = array[count - 1];
        count--;
        siftDown(0);

        return min;
    }

    /**
     * Moves the element at the specified index down the heap until the heap property is restored.
     *
     * @param index the index of the element to sift down
     */
    protected abstract void siftDown(int index);

    /**
     * Moves the element at the specified index up the heap until the heap property is restored.
     *
     * @param index the index of the element to sift up
     */
    protected abstract void siftUp(int index);

    /**
     * Swaps two elements in the heap array.
     *
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     */
    protected void swap(int index1, int index2) {
        T tempValue = array[index1];
        array[index1] = array[index2];
        array[index2] = tempValue;
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return the count of elements in the heap
     */
    public int getCount() {
        return count;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks if the heap is full.
     *
     * @return true if the heap is full, false otherwise
     */
    public boolean isFull() {
        return count == array.length;
    }

    /**
     * Retrieves the element at the specified index in the heap array.
     *
     * @param index the index of the element
     * @return the element at the specified index
     */
    T getElementAtIndex(int index) {
        return array[index];
    }

    /**
     * Exception thrown when an operation is attempted on a full heap.
     */
    static class HeapFullException extends Exception {
    }

    /**
     * Exception thrown when an operation is attempted on an empty heap.
     */
    static class HeapEmptyException extends Exception {
    }
}
