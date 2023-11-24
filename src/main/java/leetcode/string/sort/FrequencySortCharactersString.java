package leetcode.string.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/sort-characters-by-frequency/
public class FrequencySortCharactersString {
    public String frequencySort(String s) {
        // Map to store the frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // PriorityQueue to sort characters based on frequency - descending order
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));
        maxHeap.addAll(frequencyMap.keySet());

        // Building the result string
        StringBuilder sortedString = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char currentChar = maxHeap.poll();
            int count = frequencyMap.get(currentChar);
            sortedString.append(String.valueOf(currentChar).repeat(Math.max(0, count)));
        }

        return sortedString.toString();
    }

    public static void main(String[] args) {
        FrequencySortCharactersString solution = new FrequencySortCharactersString();
        String s = "tree";
        System.out.println(solution.frequencySort(s));
    }
}