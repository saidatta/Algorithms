package leetcode.greedy.array;

// https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/description/
public class PartitionThreePartsEqualSum {
    public boolean canThreePartsEqualSum(int[] arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // If totalSum is not divisible by 3, return false
        if (totalSum % 3 != 0) {
            return false;
        }

        int targetSum = totalSum / 3;
        int runningSum = 0;
        int partitions = 0;

        for (int num : arr) {
            runningSum += num;
            if (runningSum == targetSum) {
                partitions++;
                runningSum = 0;
            }
        }

        // partitions greater than or equal 3. [0,0,0,0] -> [0], [0], [0], [0]
        return partitions >= 3;
    }
}
