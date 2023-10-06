package leetcode.greedy.string;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/partition-labels/description/
class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        // An array to keep track of the last occurrence of each character
        int[] lastOccurrence = new int[26];

        // Populate the last occurrence of each character
        for (int i = 0; i < S.length(); ++i) {
            lastOccurrence[S.charAt(i) - 'a'] = i;
        }

        int maxLastOccurrence = 0, partitionStart = 0;
        List<Integer> partitions = new ArrayList<>();

        // Iterate over the string to determine partitions
        for (int i = 0; i < S.length(); ++i) {
            maxLastOccurrence = Math.max(maxLastOccurrence, lastOccurrence[S.charAt(i) - 'a']);

            // If the current index matches the last occurrence of a character,
            // we add the length of the partition to the result list.
            if (i == maxLastOccurrence) {
                partitions.add(i - partitionStart + 1);
                partitionStart = i + 1;
            }
        }

        return partitions;
    }
}
