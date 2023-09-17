package Int.mongodb;

import java.util.LinkedList;
import java.util.Queue;

interface WindowStrategy {
    int compute(Queue<Integer> window);
}

class SumStrategy implements WindowStrategy {
    @Override
    public int compute(Queue<Integer> window) {
        return window.stream().mapToInt(Integer::intValue).sum();
    }
}

class ProductStrategy implements WindowStrategy {
    @Override
    public int compute(Queue<Integer> window) {
        return window.stream().mapToInt(Integer::intValue).reduce(1, (a, b) -> a * b);
    }
}

class CountStrategy implements WindowStrategy {
    @Override
    public int compute(Queue<Integer> window) {
        return window.size();
    }
}

class SlidingWindowCalculator {
    private final int[] arr;
    private final int n;
    private final WindowStrategy strategy;
    private final Queue<Integer> window = new LinkedList<>();

    public SlidingWindowCalculator(int[] arr, int n, WindowStrategy strategy) {
        if (arr.length < n) {
            throw new IllegalArgumentException("Window size n should be less than or equal to the length of the array");
        }

        this.arr = arr;
        this.n = n;
        this.strategy = strategy;

        for (int i = 0; i < n; i++) {
            window.offer(arr[i]);
        }
    }

    public int next() {
        if (window.size() < n) {
            throw new IllegalStateException("No more windows to slide");
        }

        int result = strategy.compute(window);

        if (window.size() == n) {
            window.poll();
            int nextIndex = n - 1 + (n - window.size());
            if (nextIndex < arr.length) {
                window.offer(arr[nextIndex]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int n = 2;

        SlidingWindowCalculator sumCalc = new SlidingWindowCalculator(arr, n, new SumStrategy());
        System.out.println(sumCalc.next()); // 3
        System.out.println(sumCalc.next()); // 5

        SlidingWindowCalculator prodCalc = new SlidingWindowCalculator(arr, n, new ProductStrategy());
        System.out.println(prodCalc.next()); // 2
        System.out.println(prodCalc.next()); // 6

        AvgWindow avgCalc = new AvgWindow(arr, n);
        System.out.println(avgCalc.next()); // 1.5
        System.out.println(avgCalc.next()); // 2.5
    }
}
class AvgWindow {
    private final SlidingWindowCalculator sumWindow;
    private final SlidingWindowCalculator countWindow;

    public AvgWindow(int[] arr, int n) {
        this.sumWindow = new SlidingWindowCalculator(arr, n, new SumStrategy());
        this.countWindow = new SlidingWindowCalculator(arr, n, new CountStrategy());
    }

    public double next() {
        return (double) sumWindow.next() / countWindow.next();
    }
}

