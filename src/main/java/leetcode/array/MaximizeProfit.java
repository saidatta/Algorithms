package leetcode.array;

import java.util.List;

// https://leetcode.com/problems/maximize-the-profit-as-the-salesman/description/
public class MaximizeProfit {

    private static final int START_INDEX = 0;
    private static final int END_INDEX = 1;
    private static final int PROFIT = 2;

    /**
     * Calculates the maximum profit achievable based on given offers.
     *
     * @param totalHouses   The total number of houses.
     * @param offersList  A list of offers, where each offer is a list of integers
     *                    in the format [startDay, endDay, profit].
     * @return The maximum achievable profit.
     */
    public int maximizeTheProfit(int totalHouses, List<List<Integer>> offersList) {
        int[] cumulativeProfit = new int[totalHouses + 1];

        // Sort the list based on the endDay, then startDay
        offersList.sort((a, b) ->
                a.get(END_INDEX).equals(b.get(END_INDEX)) ?
                a.get(START_INDEX).compareTo(b.get(START_INDEX)) :
                a.get(END_INDEX).compareTo(b.get(END_INDEX))
        );

        int currentOfferIndex = 0;
        for (int currentHouse = 1; currentHouse <= totalHouses; currentHouse++) {
            cumulativeProfit[currentHouse] = cumulativeProfit[currentHouse - 1];

            while (isOfferForHouse(currentOfferIndex, currentHouse, offersList)) {
                int offerProfit = offersList.get(currentOfferIndex).get(PROFIT);
                int profitTillStartDay = cumulativeProfit[offersList.get(currentOfferIndex).get(START_INDEX)];

                cumulativeProfit[currentHouse] = Math.max(cumulativeProfit[currentHouse], offerProfit + profitTillStartDay);

                currentOfferIndex++;
            }
        }

        return cumulativeProfit[totalHouses];
    }

    /**
     * Helper function to check if the offer at currentOfferIndex ends today.
     */
    private boolean isOfferForHouse(int currentOfferIndex, int currentDay, List<List<Integer>> offersList) {
        return currentOfferIndex < offersList.size() && offersList.get(currentOfferIndex).get(END_INDEX) == currentDay - 1;
    }
}
