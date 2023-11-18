package leetcode.design;

import java.util.TreeSet;

// https://leetcode.com/problems/seat-reservation-manager/
public class SeatReservationManager {
    // Marker to point to unreserved seats.
    int marker;

    // Sorted set to store all unreserved seats.
    TreeSet<Integer> availableSeats;

    public SeatReservationManager(int n) {
        // Set marker to the first unreserved seat.
        marker = 1;
        // Initialize the sorted set.
        availableSeats = new TreeSet<>();
    }

    public int reserve() {
        // If the sorted set has any element in it, then,
        // get the smallest-numbered unreserved seat from it.
        if (!availableSeats.isEmpty()) {
            int seatNumber = availableSeats.first();
            availableSeats.remove(seatNumber);
            return seatNumber;
        }

        // Otherwise, the marker points to the smallest-numbered seat.
        int seatNumber = marker;
        marker++;
        return seatNumber;
    }

    public void unreserve(int seatNumber) {
        // Push the unreserved seat in the sorted set.
        availableSeats.add(seatNumber);
    }

    public static void main(String[] args) {
        SeatReservationManager seatManager = new SeatReservationManager(5);
        System.out.println(seatManager.reserve()); // Reserves seat 1
        System.out.println(seatManager.reserve()); // Reserves seat 2
        seatManager.unreserve(2); // Unreserves seat 2
        System.out.println(seatManager.reserve()); // Reserves seat 2 again
        System.out.println(seatManager.reserve()); // Reserves seat 3
        System.out.println(seatManager.reserve()); // Reserves seat 4
        System.out.println(seatManager.reserve()); // Reserves seat 5
        seatManager.unreserve(5); // Unreserves seat 5
    }
}
