package Leetcode.design;

/**
 * https://leetcode.com/problems/design-hit-counter/#/description
 *
 * Created by venkatamunnangi on 7/22/17.
 */
public class DesignHitCounter {
    private int[] hits;
    private int[] timeStamp;

    /** Initialize your data structure here. */
    public DesignHitCounter() {
        hits = new int[300];
        timeStamp = new int[300];
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;

        if(timeStamp[index] != timestamp) {
            timeStamp[index] = timestamp;
            hits[index] = 1;
        } else {
            hits[index]++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int total = 0;
        for(int i = 0; i < 300; i++) {
            if(timestamp - timeStamp[i] < 300) {
                total += hits[i];
            }
        }

        return total;
    }

//    public class Hit {
//        int timestamp;
//        int count;
//
//        public Hit(int timestamp, int count) {
//            this.timestamp = timestamp;
//            this.count = count;
//        }
//    }
//
//    public interface IHitCounter {
//        void hit(int timestamp);
//        int getHits(int timestamp);
//    }
//
//    public class DesignHitCounter implements IHitCounter {
//        private final Deque<Hit> hitQueue;
//
//        public DesignHitCounter() {
//            hitQueue = new ArrayDeque<>();
//        }
//
//        @Override
//        public synchronized void hit(int timestamp) {
//            if (!hitQueue.isEmpty() && hitQueue.getLast().timestamp == timestamp) {
//                hitQueue.getLast().count++;
//            } else {
//                hitQueue.addLast(new Hit(timestamp, 1));
//            }
//        }
//
//        @Override
//        public synchronized int getHits(int timestamp) {
//            while (!hitQueue.isEmpty() && timestamp - hitQueue.getFirst().timestamp >= 300) {
//                hitQueue.removeFirst();
//            }
//
//            int result = 0;
//            for (Hit hit : hitQueue) {
//                result += hit.count;
//            }
//
//            return result;
//        }
//    }
//    -----

//    import java.util.*;
//import java.util.concurrent.*;
//import java.util.concurrent.locks.*;
//
//    public class Hit implements Comparable<Hit> {
//        int timestamp;
//        int count;
//
//        public Hit(int timestamp, int count) {
//            this.timestamp = timestamp;
//            this.count = count;
//        }
//
//        @Override
//        public int compareTo(Hit other) {
//            return this.timestamp - other.timestamp;
//        }
//    }
//
//    public interface IHitCounter {
//        void hit(int timestamp);
//        int getHits(int timestamp);
//    }
//
//    public class DesignHitCounter implements IHitCounter {
//        private final PriorityQueue<Hit> hitQueue;
//        private final Map<Integer, Hit> hitMap;
//        private final ReadWriteLock lock;
//        private final ScheduledExecutorService executor;
//        private volatile int total;
//
//        public DesignHitCounter() {
//            hitQueue = new PriorityQueue<>();
//            hitMap = new HashMap<>();
//            lock = new ReentrantReadWriteLock();
//            total = 0;
//            executor = Executors.newSingleThreadScheduledExecutor();
//            executor.scheduleAtFixedRate(this::cleanup, 0, 1, TimeUnit.SECONDS);
//        }
//
//        @Override
//        public void hit(int timestamp) {
//            lock.writeLock().lock();
//            try {
//                Hit hit = hitMap.get(timestamp);
//                if (hit == null) {
//                    hit = new Hit(timestamp, 0);
//                    hitMap.put(timestamp, hit);
//                    hitQueue.offer(hit);
//                }
//                hit.count++;
//                total++;
//            } finally {
//                lock.writeLock().unlock();
//            }
//        }
//
//        @Override
//        public int getHits(int timestamp) {
//            return total;
//        }
//
//        private void cleanup() {
//            lock.writeLock().lock();
//            try {
//                while (!hitQueue.isEmpty() && TimeUnit.SECONDS.toMillis(System.currentTimeMillis()) - hitQueue.peek().timestamp >= 300) {
//                    Hit hit = hitQueue.poll();
//                    total -= hit.count;
//                    hitMap.remove(hit.timestamp);
//                }
//            } finally {
//                lock.writeLock().unlock();
//            }
//        }
//    }


    //----

//    import java.util.*;
//import java.util.concurrent.*;
//import java.util.concurrent.locks.*;
//
//    public class Hit implements Comparable<Hit> {
//        int timestamp;
//        int count;
//
//        public Hit(int timestamp, int count) {
//            this.timestamp = timestamp;
//            this.count = count;
//        }
//
//        @Override
//        public int compareTo(Hit other) {
//            return this.timestamp - other.timestamp;
//        }
//    }
//
//    public interface IHitCounter {
//        void hit(int timestamp);
//        int getHits(int timestamp);
//    }
//
//    public class DesignHitCounter implements IHitCounter {
//        private final PriorityQueue<Hit> hitQueue;
//        private final Map<Integer, Hit> hitMap;
//        private final ReadWriteLock lock;
//        private final ScheduledExecutorService executor;
//        private volatile int total;
//
//        public DesignHitCounter() {
//            hitQueue = new PriorityQueue<>();
//            hitMap = new HashMap<>();
//            lock = new ReentrantReadWriteLock();
//            total = 0;
//            executor = Executors.newSingleThreadScheduledExecutor();
//            executor.scheduleAtFixedRate(this::cleanup, 0, 1, TimeUnit.SECONDS);
//        }
//
//        @Override
//        public void hit(int timestamp) {
//            lock.writeLock().lock();
//            try {
//                Hit hit = hitMap.get(timestamp);
//                if (hit == null) {
//                    hit = new Hit(timestamp, 0);
//                    hitMap.put(timestamp, hit);
//                    hitQueue.offer(hit);
//                }
//                hit.count++;
//                total++;
//            } finally {
//                lock.writeLock().unlock();
//            }
//        }
//
//        @Override
//        public int getHits(int timestamp) {
//            return total;
//        }
//
//        private void cleanup() {
//            lock.writeLock().lock();
//            try {
//                while (!hitQueue.isEmpty() && TimeUnit.SECONDS.toMillis(System.currentTimeMillis()) - hitQueue.peek().timestamp >= 300) {
//                    Hit hit = hitQueue.poll();
//                    total -= hit.count;
//                    hitMap.remove(hit.timestamp);
//                }
//            } finally {
//                lock.writeLock().unlock();
//            }
//        }
//    }


}
