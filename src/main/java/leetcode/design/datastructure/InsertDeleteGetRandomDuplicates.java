package leetcode.design.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/description/
public class InsertDeleteGetRandomDuplicates {
    // List to store the elements. The list allows for O(1) access for getRandom.
    private final List<Integer> elements;

    // Map to store the indices of each element in the list. The key is the element and the value is a set of indices.
    private final Map<Integer, Set<Integer>> indices;

    // Random object for getRandom function.
    private final Random random;

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomDuplicates() {
        elements = new ArrayList<>();
        indices = new HashMap<>();
        random = new Random();
    }

    /**
     * Inserts a value to the collection.
     * Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        // Initialize the set in the map if this is the first time inserting 'val'.
        indices.putIfAbsent(val, new HashSet<>());
        indices.get(val).add(elements.size());
        elements.add(val);

        // If the size of the set is 1, it means this is the first occurrence of 'val'.
        return indices.get(val).size() == 1;
    }

    /**
     * Removes a value from the collection.
     * Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if (!indices.containsKey(val) || indices.get(val).isEmpty()) {
            return false; // Element not found
        }

        // Remove one occurrence of 'val' from an indices map
        int removeIndex =   indices.get(val).iterator().next();
        indices.get(val).remove(removeIndex);

        // Swap the removed element with the last element in the list
        int lastElement = elements.get(elements.size() - 1);
        elements.set(removeIndex, lastElement);
        indices.get(lastElement).add(removeIndex);
        indices.get(lastElement).remove(elements.size() - 1);

        // Remove the last element from the list
        elements.remove(elements.size() - 1);

        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return elements.get(random.nextInt(elements.size()));
    }
}
