package leetcode.greedy;

// https://leetcode.com/problems/can-place-flowers/description/
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            // Check if the current plot is empty.
            if (flowerbed[i] == 0) {
                // Check if the left and right plots are empty.
                boolean emptyLeftPlot = (i == 0) || (flowerbed[i - 1] == 0);
                boolean emptyRightPlot = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);

                // If both plots are empty, we can plant a flower here.
                if (emptyLeftPlot && emptyRightPlot) {
                    flowerbed[i] = 1;
                    count++;
                    if (count >= n) {
                        return true;
                    }
                }
            }
        }
        return count >= n;
    }

    public static void main(String[] args) {
        CanPlaceFlowers solver = new CanPlaceFlowers();

        int[] flowerbed1 = {1, 0, 0, 0, 1};
        int n1 = 1;
        boolean result1 = solver.canPlaceFlowers(flowerbed1, n1);
        System.out.println("Can place " + n1 + " flowers in " + arrayToString(flowerbed1) + "? " + result1);

        int[] flowerbed2 = {1, 0, 0, 0, 1};
        int n2 = 2;
        boolean result2 = solver.canPlaceFlowers(flowerbed2, n2);
        System.out.println("Can place " + n2 + " flowers in " + arrayToString(flowerbed2) + "? " + result2);
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
