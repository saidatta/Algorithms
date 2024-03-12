package leetcode.array.prefixsum;

// Follow up: find the minimum steps to make two arrays equal
public class MakeTwoSubarraysEqualFollowup {
    public static int minSteps(int[] target, int[] arr) {
        // Check if frequencies are the same
        int[] freq = new int[1001];
        for (int num : target) {
            freq[num]++;
        }
        for (int num : arr) {
            if (--freq[num] < 0) {
                return -1;
            }
        }

        int steps = 0;
        for (int i = 0, j = 0; i < target.length && j < arr.length;) {
            if (target[i] == arr[j]) {
                i++;
                j++;
            } else {
                // Move j to find the correct position of target[i] in arr
                j++;
                steps++;
            }
        }

        return steps;
    }

    public static void main(String[] args) {
        int[] target1 = {1,2,3,4};
        int[] arr1 = {2,4,1,3};

        int[] target2 = {7};
        int[] arr2 = {7};

        int[] target3 = {3,7,9};
        int[] arr3 = {3,7,11};

        System.out.println(minSteps(target1, arr1));  // Expected: 2
        System.out.println(minSteps(target2, arr2));  // Expected: 0
        System.out.println(minSteps(target3, arr3));  // Expected: -1
    }
}

//    To determine the minimum number of steps needed to make arr equal to target by reversing subarrays, it's a bit
//    more complex than just checking for equality of frequencies.
//
//        If the elements and their frequencies in both arrays aren't the same, then it's impossible to make them equal,
//        and we return -1 or a specific value indicating the impossibility. Otherwise, we need to count the number of
//        out-of-place elements.
//
//        Here's a high-level idea of how to approach the problem:
//
//        If the elements and their frequencies in arr and target aren't the same, it's impossible.
//        For each position i, if arr[i] isn't equal to target[i], then this element in arr is out-of-place. To fix it,
//        you'll need to reverse the subarray in arr where this element is in the position it should be in target.
//        This action is equivalent to a "swap" in bubble sort.

//        Count the number of such "swaps". That count is the minimum number of steps to make the arrays equal.