package Leetcode.dp.array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/buildings-with-an-ocean-view/description/
public class OceanViewBuildings {
    public int[] findBuildings(int[] heights) {
        List<Integer> oceanViewBuildings = new ArrayList<>();
        int maxHeightSoFar = -1; // since minimum height is 1, we can initialize to -1

        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > maxHeightSoFar) {
                oceanViewBuildings.add(i);
                maxHeightSoFar = heights[i];
            }
        }

        // Convert list of integers to int array
        int[] result = new int[oceanViewBuildings.size()];
        for (int i = 0; i < oceanViewBuildings.size(); i++) {
            result[i] = oceanViewBuildings.get(oceanViewBuildings.size() - 1 - i); // reversing while inserting into the array
        }

        return result;
    }
}
