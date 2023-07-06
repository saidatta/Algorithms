package Leetcode.Math;

/**
 * https://leetcode.com/problems/total-distance-traveled/
 */
public class TotalDistanceTraveled {
    private static final int MILEAGE = 10;
    private static final int TRANSFER_THRESHOLD = 5;
    private static final int TRANSFER_AMOUNT = 1;

    public int distanceTraveled(int mainTank, int additionalTank) {
        int distance = 0, spent = 0;

        while (mainTank > 0) {
            distance += MILEAGE;
            mainTank--;
            spent++;

            if (spent == TRANSFER_THRESHOLD && additionalTank > 0) {
                mainTank += TRANSFER_AMOUNT;
                additionalTank -= TRANSFER_AMOUNT;
                spent = 0;
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        TotalDistanceTraveled truck = new TotalDistanceTraveled();

        int mainTank1 = 5;
        int additionalTank1 = 10;
        int distance1 = truck.distanceTraveled(mainTank1, additionalTank1);
        System.out.println("Max distance with " + mainTank1 + " liters in main tank and "
                + additionalTank1 + " liters in additional tank: " + distance1 + "km");

        int mainTank2 = 1;
        int additionalTank2 = 2;
        int distance2 = truck.distanceTraveled(mainTank2, additionalTank2);
        System.out.println("Max distance with " + mainTank2 + " liters in main tank and "
                + additionalTank2 + " liters in additional tank: " + distance2 + "km");
    }
}
