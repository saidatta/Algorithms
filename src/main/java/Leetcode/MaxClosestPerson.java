package Leetcode;

/**
 * https://leetcode.com/problems/maximize-distance-to-closest-person/
 *
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 * There is at least one empty seat, and at least one person sitting.
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * Return that maximum distance to closest person.
 */
public class MaxClosestPerson {

    // 1,0,0,0
    // 1,0,1,0,0,0,1
    // 1,0
    public int maxDistToClosest(int... seats) {
        int left = -1, maxDistance = 0;
        int totalNumberSeats = seats.length;

        for (int i = 0; i < totalNumberSeats; i++) {
            if (seats[i] == 0) {
                continue;
            }

            if (left == -1) {
                maxDistance = Math.max(maxDistance, i);
            } else {
                maxDistance = Math.max(maxDistance, (i - left) / 2);
            }
            left = i;
        }

        if (seats[totalNumberSeats - 1] == 0) {
            maxDistance = Math.max(maxDistance, totalNumberSeats - 1 - left);
        }

        return maxDistance;
    }

    public static void main(String [] args) {
        MaxClosestPerson maxClosestPerson = new MaxClosestPerson();
        System.out.println(maxClosestPerson.maxDistToClosest(0,0,1));
        System.out.println(maxClosestPerson.maxDistToClosest(1,0,0,0));
        System.out.println(maxClosestPerson.maxDistToClosest(1,0,0,0,1,0,1));
        System.out.println(maxClosestPerson.maxDistToClosest(1,0,0,1));
        System.out.println(maxClosestPerson.maxDistToClosest(0,1,1,1,0,0,1,0,0));
    }
}
