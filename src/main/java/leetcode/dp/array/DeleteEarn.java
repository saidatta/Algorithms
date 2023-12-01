package leetcode.dp.array;

// https://leetcode.com/problems/delete-and-earn/
public class DeleteEarn {
    public int deleteAndEarn(int[] nums) {
        // Find the maximum number in nums to determine the size of the points' array
        int maxNumber = 0;
        for (int num : nums) {
            maxNumber = Math.max(maxNumber, num);
        }

        // Initialize the points array where index represents the number and value represents total points
        // this also sorts the values, and merges the duplicates, since there is no inherent advantage of taking
        // duplicates seperately.
        int[] totalPointsForEachNumber = new int[maxNumber + 1];
        for (int num : nums) {
            totalPointsForEachNumber[num] += num;
        }

        // DP array to store the maximum points up to each number
        int[] maxPointsUpToNumber = new int[maxNumber + 1];
        maxPointsUpToNumber[1] = totalPointsForEachNumber[1];

        // Calculate the maximum points for each number using DP
        for (int i = 2; i <= maxNumber; i++) {
            // Choose between not taking the current number or taking it and adding its points to the max points two steps back
            maxPointsUpToNumber[i] = Math.max(
                    maxPointsUpToNumber[i - 1],
                    maxPointsUpToNumber[i - 2] + totalPointsForEachNumber[i]
            );
        }

        // The last element in maxPointsUpToNumber array contains the result
        return maxPointsUpToNumber[maxNumber];
    }


    public static void main(String[] args) {
        DeleteEarn solution = new DeleteEarn();
        System.out.println(solution.deleteAndEarn(new int[]{3, 4, 2})); // Output: 6
        System.out.println(solution.deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4})); // Output: 9
    }
}
