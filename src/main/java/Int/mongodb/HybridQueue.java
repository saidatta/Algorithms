package Int.mongodb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Queue;

// Implement a hybrid queue class, with one part in memory and the other part on the hard disk.
// The idea behind a hybrid queue is to have a queue that can handle a larger amount of data than what can be typically
// stored in the RAM. When the in-memory part of the queue gets full, the data should overflow to the hard disk
public class HybridQueue<T> {
    private final Queue<T> inMemoryQueue;
    private final Queue<File> diskQueue;
    private final int memoryCapacity;
    private int diskCount;

    public HybridQueue(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
        this.inMemoryQueue = new LinkedList<>();
        this.diskQueue = new LinkedList<>();
        this.diskCount = 0;
    }

    public void enqueue(T item) throws IOException {
        if (inMemoryQueue.size() == memoryCapacity) {
            File temp = File.createTempFile("hybridQueue", ".tmp");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(temp))) {
                oos.writeObject(inMemoryQueue.poll());
            }
            diskQueue.offer(temp);
            diskCount++;
        }
        inMemoryQueue.offer(item);
    }

    public T dequeue() throws IOException, ClassNotFoundException {
        if (inMemoryQueue.isEmpty() && diskCount == 0) {
            return null;
        }

        if (inMemoryQueue.isEmpty() && diskCount > 0) {
            File oldestFile = diskQueue.poll();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(oldestFile))) {
                @SuppressWarnings("unchecked")
                T item = (T) ois.readObject();
                inMemoryQueue.offer(item);
                oldestFile.delete();
                diskCount--;
            }
        }

        return inMemoryQueue.poll();
    }

    public static void main(String[] args) throws Exception {
        HybridQueue<String> queue = new HybridQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D"); // Pushes "A" to disk

        System.out.println(queue.dequeue()); // Should print B
        System.out.println(queue.dequeue()); // Should print C
        System.out.println(queue.dequeue()); // Should pull "A" from disk and print A
        System.out.println(queue.dequeue()); // Should print D
    }
}
