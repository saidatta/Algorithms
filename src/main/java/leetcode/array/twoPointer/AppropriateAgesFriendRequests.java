package leetcode.array.twoPointer;

/**
 * LeetCode problem: https://leetcode.com/problems/friends-of-appropriate-ages/
 *
 * This solution counts the number of friend requests among people of different ages.
 * The age rule is: A can send a friend request to B if B's age is within (A/2 + 7, A].
 * This implementation uses a two-pointer approach and counting sort technique.
 */
public class AppropriateAgesFriendRequests {

    public int numFriendRequests(int[] ages) {
        // Result to hold the total number of friend requests.
        int totalRequests = 0;

        // numInAge: Counts the number of people at each age (1 to 120).
        // sumInAge: Holds the cumulative sum of people up to that age.
        int[] numInAge = new int[121], sumInAge = new int[121];

        // Count the number of people at each age.
        for (int age : ages)
            numInAge[age]++;

        // Calculate the cumulative sum of people up to each age.
        for (int age = 1; age <= 120; ++age)
            sumInAge[age] = numInAge[age] + sumInAge[age - 1];

        // Loop over each age from 15 to 120 (age < 15 cannot send requests due to age rule).
        for (int age = 15; age <= 120; ++age) {
            // Skip if no one is this age.
            if (numInAge[age] == 0) continue;

            // Calculate the number of people who can receive requests from this age.
            int eligibleReceivers = sumInAge[age] - sumInAge[age / 2 + 7];

            // Add the number of requests that people of this age can send.
            // Subtract the number of people of this age because they can't request themselves.
            totalRequests += eligibleReceivers * numInAge[age] - numInAge[age];
        }

        return totalRequests;
    }
}
