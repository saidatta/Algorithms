package leetcode.array.slidingwindow;

// https://leetcode.com/problems/maximize-the-confusion-of-an-exam/
public class MaximizeConfusionOfExam {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }

    private int maxConsecutiveChar(String s, int k, char target) {
        int max = 0;
        int left = 0;
        int count = 0; // Count of characters that are not 'target'

        // Iterate over the string with a sliding window
        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) != target) {
                count++;
            }

            // If the count exceeds k, shrink the window from the left
            while (count > k) {
                if (s.charAt(left) != target) {
                    count--;
                }
                left++;
            }

            // Update the maximum length
            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        MaximizeConfusionOfExam solution = new MaximizeConfusionOfExam();
        System.out.println(solution.maxConsecutiveAnswers("TTFF", 2)); // Output: 4
        System.out.println(solution.maxConsecutiveAnswers("TFFT", 1)); // Output: 3
        System.out.println(solution.maxConsecutiveAnswers("TTFTTFTT", 1)); // Output: 5
    }
}
// The problem is about altering the answers of a true/false test to maximize the length of consecutive
// identical answers (all 'T's or all 'F's). You are given `answerKey`, a string representing the
// original answers, and an integer `k`, which is the maximum number of changes you can make to the answers.
// The objective is to determine the longest string of consecutive 'T's or 'F's that can be achieved by making at
// most `k` changes to the `answerKey`. The challenge lies in deciding which answers to change to maximize the length
// of either all 'T's or all 'F's in a row.
//
// ### Example Explanation
// 1. **Example 1**: For `answerKey = "TTFF"` and `k = 2`, changing both 'F's to 'T's results in "TTTT", which has four
//      consecutive 'T's.
// 2. **Example 2**: With `answerKey = "TFFT"` and `k = 1`, changing the first 'T' to 'F' results in "FFFT" or changing
//      the second 'T' to 'F' results in "TFFF". Both have three consecutive 'F's.
// 3. **Example 3**: For `answerKey = "TTFTTFTT"` and `k = 1`, changing either the first or second 'F' to 'T' results
//      in a sequence with five consecutive 'T's, like "TTTTTFTT" or "TTFTTTTT".