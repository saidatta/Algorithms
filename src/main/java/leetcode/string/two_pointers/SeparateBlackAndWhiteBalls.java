package leetcode.string.two_pointers;

public class SeparateBlackAndWhiteBalls {
    public long minimumSteps(String s) {
        int right = s.length() - 1;
        long totalDistance = 0;

        for (int left = 0; left < s.length(); left++) {
            if (s.charAt(left) == '1') {
                totalDistance += right - left;
                right--;
            }
        }

        return totalDistance;
    }

    public static void main(String[] args) {
        SeparateBlackAndWhiteBalls solution = new SeparateBlackAndWhiteBalls();
        System.out.println(solution.minimumSteps("101")); // Output: 1
        System.out.println(solution.minimumSteps("100")); // Output: 2
        System.out.println(solution.minimumSteps("0111")); // Output: 0
    }
}

