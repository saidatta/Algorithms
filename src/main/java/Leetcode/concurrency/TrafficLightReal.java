package Leetcode.concurrency;

//
// Here's a semaphore solution. Max 100 cars may cross at a time (limit can be set as required).
//        (Not supposed to pass the tests)
//
//        Idea: Once direction change is requested, no more cars from old direction are allowed (yellow light ;).
//        When all permitted cars cross, direction changes to new direction and it goes on. tryAcquire may also have a
//        timeout to make it more realistic. Synchronizing only to update direction.

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class TrafficLightReal {

    Semaphore roadASignal;          // permits cars on road A
    Semaphore roadBSignal;          // permits cars on road B
    final AtomicInteger roadACount;
    AtomicInteger roadBCount;
    private static final int ROAD_A = 1;
    private static final int MAX_CROSSING_CARS = 100;

    public TrafficLightReal() {
        roadASignal = new Semaphore(MAX_CROSSING_CARS);
        roadBSignal = new Semaphore(0);
        roadACount = new AtomicInteger(0);
        roadBCount = new AtomicInteger(0);
    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {

        // Acquire corresponding permit before crossing
        if (roadId == ROAD_A) {
            // Try acquiring road A permit
            while (!roadASignal.tryAcquire()) {

                // If cannot acquire road A permit, remove all remaining road B permits
                roadBSignal.drainPermits();

                // If no more cars on road B, reassign road A permits and reset road B
                if (roadBCount.get() == 0) {
                    synchronized (roadBCount) {

                        // Recheck to make sure
                        if (roadBCount.get() == 0) {
                            turnGreen.run();
                            roadASignal = new Semaphore(MAX_CROSSING_CARS);
                            roadBSignal = new Semaphore(0);
                        }
                    }
                }
            }

            // Once permit is acquired, good to go: increment car count on road A.
            roadACount.incrementAndGet();
        } else {
            // Exactly same process as for road A permits
            while (!roadBSignal.tryAcquire()) {
                roadASignal.drainPermits();
                if (roadACount.get() == 0) {
                    synchronized (roadACount) {
                        if (roadACount.get() == 0) {
                            turnGreen.run();
                            roadBSignal = new Semaphore(MAX_CROSSING_CARS);
                            roadASignal = new Semaphore(0);
                        }
                    }
                }
            }
            roadBCount.incrementAndGet();
        }

        // Permit acquired, go go go!
        crossCar.run();

        // Reduce count and release permit when leaving.
        if (roadId == ROAD_A) {
            roadACount.decrementAndGet();
            roadASignal.release();
        } else {
            roadBCount.decrementAndGet();
            roadBSignal.release();
        }
    }
}