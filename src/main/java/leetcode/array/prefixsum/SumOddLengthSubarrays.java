package leetcode.array.prefixsum;

// https://leetcode.com/problems/sum-of-all-odd-length-subarrays/description/
public class SumOddLengthSubarrays {
    public int sumOddLengthSubarrays(int[] arr) {
        // This variable will hold the sum of all odd-length subarrays.
        int sum = 0;

        // Total number of elements in the array
        int n = arr.length;

        // We loop through each element of the array to calculate its contribution
        // to the sum of all odd-length subarrays.
        for (int i = 0; i < n; i++) {

            // For each element at position 'i', (i + 1) represents the number of
            // ways to choose the start of a subarray that includes arr[i].
            int startChoices = i + 1;

            // Similarly, (n - i) represents the number of ways to choose the end
            // of a subarray that includes arr[i].
            int endChoices = n - i;

            // Total subarrays that include arr[i] is the product of start and end choices.
            int totalSubarrays = startChoices * endChoices;

            // Of these subarrays, we determine the number of odd-length subarrays.
            // Half of the total subarrays will be even-length, and the other half will be odd-length.
            // However, if totalSubarrays is odd, then there will be 1 more odd-length than even-length.
            // Hence, we add 1 to totalSubarrays and divide by 2.
            int oddLengthSubarrays = (totalSubarrays + 1) / 2;

            // The value arr[i] contributes its value times the number of odd-length
            // subarrays it's a part of to the final sum.
            sum += arr[i] * oddLengthSubarrays;
        }

        // Finally, we return the calculated sum.
        return sum;
    }

    public static void main(String[] args) {
        SumOddLengthSubarrays sol = new SumOddLengthSubarrays();
        System.out.println(sol.sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));  // Expected output: 58
        System.out.println(sol.sumOddLengthSubarrays(new int[]{1, 2}));  // Expected output: 3
        System.out.println(sol.sumOddLengthSubarrays(new int[]{10, 11, 12}));  // Expected output: 66
    }

    class Solution {
        public int sumOddLengthSubarrays(int[] arr) {
            int n = arr.length, answer = 0;

            for (int left = 0; left < n; ++left) {
                int currentSum = 0;
                for (int right = left; right < n; ++right) {
                    currentSum += arr[right];
                    answer += (right - left + 1) % 2 == 1 ? currentSum : 0;
                }
            }
            return answer;
        }
    }

}


//    For each number in the array, the number of times it appears in the odd-length subarrays can be calculated based
//    on its position and the length of the array. Specifically, for each element arr[i], the number of times it appears
//    in the odd-length subarrays can be computed as:
//
//        (i + 1) * (arr.length - i)
//
//        To break this down:
//
//        (i + 1) is the number of ways to choose the start of the subarray that contains arr[i].
//        (arr.length - i) is the number of ways to choose the end of the subarray that contains arr[i].
//        However, we're interested only in odd-length subarrays. Given any start and end, there are
//        (start choices) * (end choices) / 2 even-length subarrays, and the same number of odd-length subarrays, with
//        the possible addition of 1 if the total number of subarrays (start choices) * (end choices) is odd.
//
//        Thus, each number is counted ((i + 1) * (arr.length - i) + 1) / 2 times for odd-length subarrays.
//
//        Multiplying the value of each number with the number of times it appears in the odd-length subarrays and
//        summing them up gives the result.
//
//        O(n) time complexity.
