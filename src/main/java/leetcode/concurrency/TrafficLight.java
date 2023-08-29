package leetcode.concurrency;

// https://leetcode.com/problems/traffic-light-controlled-intersection/
public class TrafficLight {

    private boolean isRoadAGreen;
    private final Object lock = new Object();

    public TrafficLight() {
        this.isRoadAGreen = true;  // Initially, road A has a green light
    }

    public void carArrived(int carId, int roadId, int direction, Runnable turnGreen, Runnable crossCar) throws InterruptedException {
        synchronized (lock) {
            if (roadId == 1) {
                if (!isRoadAGreen) {
                    turnGreen.run();
                    isRoadAGreen = true;
                }
            } else {
                if (isRoadAGreen) {
                    turnGreen.run();
                    isRoadAGreen = false;
                }
            }
            crossCar.run();
        }
    }
}