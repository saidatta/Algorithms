package leetcode.design.datastructure;

// https://www.lowleveldesign.io/LLD/DataStructures/CircularDeque
public class CircularDeque {
    private final int[] arr;
    private final int capacity;
    private int head;
    private int tail;
    private int count;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public CircularDeque(int k) {
        arr = new int[k];
        head = 0;
        tail = 0;
        count = 0;
        capacity = k;
    }

    private int decrementHeadIndex(int index) {
        // when an index is decremented it moves towards 0 except when
        // the index is already at 0 at which time it needs to move to index (k - 1)
        // where k = capacity of the deque
        if (index == 0) {
            return (capacity - 1);
        }
        return (index - 1) % capacity;
    }

    private int incrementHeadIndex(int index) {
        // head index is getting decrement means it is getting pushed pushed towards
        // the tail which means the item at head index is getting deleted.
        // if the circular deque has only one item before the deletion
        // then reset the head and tail index since after the deletion
        // the circular deque will be empty
        if (count == 1) {
            head = 0;
            tail = 0;
        }
        // when an index is incremented it moves towards index (capacity - 1) except when
        // the index is already at index (capacity - 1) at which time it needs to move to index 0
        if (index == capacity - 1) {
            return 0;
        }
        return (index + 1) % capacity;
    }

    private int decrementTailIndex(int index) {
        // tail index decrements when the index at tail index is deleted.
        // If the circular deque has only one item then the deletion would result
        // in an empty deque
        if (count == 1) {
            head = 0;
            tail = 0;
        }
        if (index == 0) {
            return (capacity - 1);
        }
        return (index - 1) % capacity;
    }

    private int incrementTailIndex(int index) {
        if (index == capacity - 1) {
            return 0;
        }
        return (index + 1) % capacity;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (count == capacity) {
            return false;
        }
        count++;
        if (count == 1) { // if this is the first item to be inserted in the deque
            head = 0;
            tail = 0;
            arr[0] = value;
            return true;
        }
        head = decrementHeadIndex(head);
        arr[head] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (count == capacity) {
            return false;
        }
        count++;
        if (count == 1) { // if this is the first item to be inserted in the deque
            head = 0;
            tail = 0;
            arr[0] = value;
            return true;
        }
        tail = incrementTailIndex(tail);
        arr[tail] = value;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (count == 0) {
            return false;
        }
        head = incrementHeadIndex(head);
        count--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (count == 0) {
            return false;
        }
        tail = decrementTailIndex(tail);
        count--;
        return true;

    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (count == 0) {
            return -1;
        }
        return arr[head];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (count == 0) {
            return -1;
        }
        return arr[tail];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return count == capacity;
    }
}