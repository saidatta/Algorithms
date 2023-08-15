package Leetcode.design;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode.com/problems/design-bounded-blocking-queue/
 */
public class BoundedBlockingQueue {
    private final ReentrantLock lock;
    private final Condition full;
    private final Condition notEmpty;
    private final int[] queue;
    private int tail = 0;
    private int head = 0;
    private int size = 0;
    public BoundedBlockingQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        this.queue = new int[capacity];
        lock = new ReentrantLock(false);
        this.full = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while(size == queue.length) {
                full.await();
            }
            queue[tail++] = element;
            tail %= queue.length;
            size++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while(size == 0) {
                notEmpty.await();
            }
            int res = queue[head++];
            head %= queue.length;
            size--;
            full.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public int size() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            return this.size;
        } finally {
            lock.unlock();
        }
    }
}
