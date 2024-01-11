package leetcode.graph.topological;

import java.util.*;

// https://leetcode.com/problems/destination-city/description/
public class DestinationCity {
    public String destCity(List<List<String>> edges) {
        // Preprocessing
        Set<String> uniqueCities = new HashSet<>();
        for (List<String> edge : edges) {
            uniqueCities.addAll(edge);
        }
        List<String> idMap = new ArrayList<>(uniqueCities);

        // Map to store city indices
        Map<String, Integer> cityIndexMap = new HashMap<>();
        for (int i = 0; i < idMap.size(); i++) {
            cityIndexMap.put(idMap.get(i), i);
        }

        // Actual algorithm
        int[] outdegree = new int[idMap.size()];
        Arrays.fill(outdegree, -1);
        for (List<String> edge : edges) {
            int srcIndex = cityIndexMap.get(edge.get(0));
            outdegree[srcIndex]++;
        }

        for (int i = 0; i < outdegree.length; i++) {
            if (outdegree[i] < 0) {
                return idMap.get(i);
            }
        }

        return "";
    }

    // possible because of the guarantees from problem statement. that there exist only 1 destination city
    // with non-outgoing edge.
    class DestinationCityHashSetFaster {
        public String destCity(List<List<String>> paths) {
            Set<String> departureCities = new HashSet<>();

            // Add all departure cities to the set
            for (List<String> path : paths) {
                departureCities.add(path.get(0));
            }

            // The destination city will not be in the set of departure cities
            for (List<String> path : paths) {
                if (!departureCities.contains(path.get(1))) {
                    return path.get(1);
                }
            }

            return null; // This line won't be reached as per problem constraints
        }
    }

    public static void main(String[] args) {
        DestinationCity solution = new DestinationCity();

        // Example 1
        List<List<String>> paths1 = List.of(
                List.of("London", "New York"),
                List.of("New York", "Lima"),
                List.of("Lima", "Sao Paulo")
        );
        System.out.println("Destination City for Example 1: " + solution.destCity(paths1));
        // Expected output: "Sao Paulo"

        // Example 2
        List<List<String>> paths2 = List.of(
                List.of("B", "C"),
                List.of("D", "B"),
                List.of("C", "A")
        );
        System.out.println("Destination City for Example 2: " + solution.destCity(paths2));
        // Expected output: "A"

        // Example 3
        List<List<String>> paths3 = List.of(
                List.of("A", "Z")
        );
        System.out.println("Destination City for Example 3: " + solution.destCity(paths3));
        // Expected output: "Z"
    }
}

