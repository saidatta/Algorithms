package Int.mongodb;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Shard<T> {
    List<T> data;
    int capacity;
    int offset;

    public Shard(int capacity) {
        this.capacity = capacity;
        data = new ArrayList<>(capacity);
        offset = 0;
    }

    public void add(T item) {
        data.add(item);
        offset++;
    }

    public boolean isFull() {
        return offset == capacity;
    }

    public T get(int index) {
        return data.get(index);
    }

    public int size() {
        return offset;
    }

    public void clear() {
        data.clear();
        offset = 0;
    }
}

class FlusherThread<T> extends Thread {
    private List<Shard<T>> shards;
    private Queue<File> diskQueue;
    private Lock lock;

    public FlusherThread(List<Shard<T>> shards, Queue<File> diskQueue, Lock lock) {
        this.shards = shards;
        this.diskQueue = diskQueue;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                Iterator<Shard<T>> it = shards.iterator();
                while (it.hasNext()) {
                    Shard<T> shard = it.next();
                    if (shard.isFull()) {
                        File temp = File.createTempFile("hybridQueue", ".tmp");
                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(temp))) {
                            for (int i = 0; i < shard.size(); i++) {
                                oos.writeObject(shard.get(i));
                            }
                        }
                        diskQueue.offer(temp);
                        shard.clear();
                        it.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            try {
                Thread.sleep(1000);  // Check every second
            } catch (InterruptedException e) {
                break;  // Allow the thread to be interrupted and stop
            }
        }
    }
}

public class HybridQueueMultiThread<T> {
    private List<Shard<T>> shards;
    private Queue<File> diskQueue;
    private int shardCapacity;
    private int offset;
    private FlusherThread<T> flusherThread;
    private Lock lock = new ReentrantLock();

    public HybridQueueMultiThread(int shardCapacity) {
        this.shardCapacity = shardCapacity;
        this.shards = new ArrayList<>();
        this.diskQueue = new LinkedList<>();
        this.offset = 0;

        flusherThread = new FlusherThread<>(shards, diskQueue, lock);
        flusherThread.start();
    }

    public void enqueue(T item) {
        lock.lock();
        try {
            if (shards.isEmpty() || shards.get(shards.size() - 1).isFull()) {
                Shard<T> newShard = new Shard<>(shardCapacity);
                shards.add(newShard);
            }
            shards.get(shards.size() - 1).add(item);
            offset++;
        } finally {
            lock.unlock();
        }
    }

    public T dequeue() throws IOException, ClassNotFoundException {
        lock.lock();
        try {
            if (offset == 0) {
                return null;
            }

            if (shards.get(0).size() == 0) {
                File oldestFile = diskQueue.poll();
                Shard<T> shard = new Shard<>(shardCapacity);
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(oldestFile))) {
                    while (true) {
                        try {
                            @SuppressWarnings("unchecked")
                            T item = (T) ois.readObject();
                            shard.add(item);
                        } catch (EOFException e) {
                            break;
                        }
                    }
                }
                oldestFile.delete();
                shards.add(0, shard);
            }

            offset--;
            return shards.get(0).get(shards.get(0).size() - 1);
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        flusherThread.interrupt();
    }
}

