package leetcode.array.counting;

/**
 * https://leetcode.com/problems/h-index/description/
 *
 * The class calculates the H-Index for a researcher.
 * H-Index is defined as the number of papers with citation >= h.
 */
public class HIndex {

    /**
     * Calculates H-Index from given citations array.
     *
     * @param citations Array containing the citations count for each paper
     * @return The H-Index value
     */
    public int calculateHIndex(int[] citations) {
        int totalPapers = citations.length;

        // The array will hold the count of papers for each citation number
        int[] papersCount = new int[totalPapers + 1];

        // Count papers for each citation number
        for (int citation : citations) {
            // If citation count is greater than totalPapers, it contributes to all higher values of H-Index
            // since its at least h papers, at least h citations.
            papersCount[Math.min(totalPapers, citation)]++;
        }

        // Find the H-Index by going from highest potential H-Index downwards
        int hIndex = totalPapers;
        int cumulativePapers = papersCount[totalPapers];

        // Decrease potential H-Index value while it's larger than the cumulative count of papers
        while (hIndex > cumulativePapers) {
            // keep moving down, since, we keep grouping the count of citations with paper using atleast.
            hIndex--;
            cumulativePapers += papersCount[hIndex];
        }

        return hIndex;
    }

    public static void main(String[] args) {
        HIndex hIndexCalculator = new HIndex();

        // Test 1
        int[] citations1 = {3, 0, 6, 1, 5};
        int result1 = hIndexCalculator.calculateHIndex(citations1);
        System.out.println("H-Index for citations1: " + result1);  // Expected: 3

        // Test 2
        int[] citations2 = {1, 4, 7, 9, 2};
        int result2 = hIndexCalculator.calculateHIndex(citations2);
        System.out.println("H-Index for citations2: " + result2);  // Expected: 3
    }
}
