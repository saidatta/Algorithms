package leetcode.string.prefixes;

// https://leetcode.com/problems/minimum-penalty-for-a-shop/description/
public class MinPenaltyShop {

//    maintaining a penalty difference while iterating over the customer log. The idea is to find the optimal closing
//    time by keeping track of the difference between customers arriving ('Y') and not arriving ('N'). We want to close
//    the shop at the hour when this difference is maximized.
    public int bestClosingTime(String customers) {
        // The length of the customer visit log.
        int n = customers.length();

        // Initialize pointers and variables to track the state.
        int i = 0;
        int bestClosingHour = -1;
        int penaltyDifference = 0;
        int maxPenaltyDifference = 0;

        // Iterate over the customer log.
        while (i < n) {
            char currentHour = customers.charAt(i);
            if (currentHour == 'Y') {
                penaltyDifference++;
            } else if (currentHour == 'N') {
                penaltyDifference--;
            }

            // If the current penalty difference is greater than the max so far,
            // Update the max difference and the best closing hour.
            if (penaltyDifference > maxPenaltyDifference) {
                maxPenaltyDifference = penaltyDifference;
                bestClosingHour = i;
            }
            i++;
        }

        // Return the best closing hour + 1 since hours are 0-indexed.
        return bestClosingHour + 1;
    }

//    The problem essentially requires determining the optimal time to close the shop in order to minimize the penalty.
//    Let's break down the problem:
//
//    For each hour the shop is open and no customers come (N), the penalty increases by 1.
//    For each hour the shop is closed and customers come (Y), the penalty increases by 1.
//    This means that if we think of the problem in terms of gaining and losing potential advantages, we can represent Y
//    as +1 and N as -1.
//
//    Imagine that we have a timeline where, moving forward through time, every Y gives us a point and every N takes a
//    point away. The idea behind the approach is that the optimal time to close would be right after the moment when
//    the cumulative sum of these advantages is at its peak, i.e., when we've accumulated the most net Ys over Ns.
//
//    To achieve this:
//
//    We iterate through the customers string.
//    For every Y (customer arrival), we increase the penaltyDifference.
//    For every N (no customer arrival), we decrease the penaltyDifference.
//    We track the hour (index) at which the penaltyDifference is the maximum since this indicates the optimal time to
//    close to minimize the penalty.
//    We return that hour (index) + 1 as the closing time.

}
