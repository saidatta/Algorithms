package leetcode.design.ds;

import java.util.Deque;
import java.util.LinkedList;

// https://www.lowleveldesign.io/LLD/DataStructures/CircularQueue
class CircularQueue {
    private final int[] data;
    private int head;
    private int tail;
    private int size;  // current number of elements
    private final int capacity; // maximum capacity

    // Constructor
    public CircularQueue(int k) {
        capacity = k;
        data = new int[k];
        head = -1;
        tail = -1;
        size = 0;
    }

    // Get the front item from the queue
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    // Get the last item from the queue
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return data[tail];
    }

    // Insert an element into the circular queue
    public boolean enQueue(int value) {
        if (isFull()) {
            // enQueue operation fails if the circular queue has reached its capacity.
            // no more element can be inserted until at least one element is deQueued.
            return false;
        }
        if (isEmpty()) {
            head = 0;
        }
        // enqueueing is always done at the tail (rear) inserting an element increments the tail index.
        // if the tail index is (capacity - 1), then it becomes 0. since this is a circular queue.
        tail = (tail + 1) % capacity;
        data[tail] = value;
        size++;
        return true;
    }

    // Delete an element from the circular queue
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
        } else {
            head = (head + 1) % capacity;
        }
        size--;
        return true;
    }

    // Check whether the queue is empty or not
    public boolean isEmpty() {
        return size == 0;
    }

    // Check whether the queue is full or not
    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(3);
        System.out.println(circularQueue.enQueue(1)); // true
        System.out.println(circularQueue.enQueue(2)); // true
        System.out.println(circularQueue.enQueue(3)); // true
        System.out.println(circularQueue.enQueue(4)); // false
        System.out.println(circularQueue.Rear());    // 3
        System.out.println(circularQueue.isFull());  // true
        System.out.println(circularQueue.deQueue()); // true
        System.out.println(circularQueue.enQueue(4)); // true
        System.out.println(circularQueue.Rear());    // 4
    }
}
