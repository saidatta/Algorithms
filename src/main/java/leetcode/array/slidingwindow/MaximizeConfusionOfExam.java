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