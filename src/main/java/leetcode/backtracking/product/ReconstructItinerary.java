package leetcode.backtracking.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 *
 * Created by venkatamunnangi on 9/23/19.
 */
public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        Solution solution = new Solution(new DFSItineraryFinder());
        return solution.findItinerary(tickets);
    }

    interface ItineraryFinder {
        List<String> findItinerary(List<List<String>> tickets);
    }

    class DFSItineraryFinder implements ItineraryFinder {
        HashMap<String, PriorityQueue<String>> itenerary;

        public DFSItineraryFinder() {
            this.itenerary = new HashMap<>();
        }

        @Override
        public List<String> findItinerary(List<List<String>> tickets) {
            List<String> finalItinerary = new ArrayList<>();
            if (tickets == null || tickets.size() == 0) {
                return finalItinerary;
            }

            for (List<String> ticket : tickets) {
                itenerary.putIfAbsent(ticket.get(0), new PriorityQueue<>());
                itenerary.get(ticket.get(0)).add(ticket.get(1));
            }

            if (!itenerary.containsKey("JFK")) {
                return finalItinerary;
            }
            dfs("JFK", finalItinerary);
            Collections.reverse(finalItinerary);
            return finalItinerary;
        }

        private void dfs(String start, List<String> finalItinerary) {
            PriorityQueue<String> pq = itenerary.get(start);
            while (pq != null && !pq.isEmpty()) {
                dfs(pq.poll(), finalItinerary);
            }
            finalItinerary.add(start);
        }
    }

    public class Solution {
        private ItineraryFinder itineraryFinder;

        public Solution(ItineraryFinder itineraryFinder) {
            this.itineraryFinder = itineraryFinder;
        }

        public List<String> findItinerary(List<List<String>> tickets) {
            return itineraryFinder.findItinerary(tickets);
        }
    }
}
