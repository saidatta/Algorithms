package leetcode.array.traversal;

// https://leetcode.com/problems/jump-game-v/
public class JumpGameV {
    public static int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        int res = 1;

        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(i, arr, d, dp));
        }

        return res;
    }

    private static int dfs(int i, int[] arr, int d, int[] dp) {
        if (dp[i] != 0) {
            return dp[i];
        }

        int n = arr.length;
        int res = 1;

        for (int j = i - 1; j >= Math.max(0, i - d) && arr[j] < arr[i]; j--) {
            res = Math.max(res, 1 + dfs(j, arr, d, dp));
        }

        for (int j = i + 1; j <= Math.min(n - 1, i + d) && arr[j] < arr[i]; j++) {
            res = Math.max(res, 1 + dfs(j, arr, d, dp));
        }

        dp[i] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {6,4,14,6,8,13,9,7,10,6,12};
        System.out.println(maxJumps(arr1, 2));  // 4

        int[] arr2 = {3,3,3,3,3};
        System.out.println(maxJumps(arr2, 3));  // 1

        int[] arr3 = {7,6,5,4,3,2,1};
        System.out.println(maxJumps(arr3, 1));  // 7
    }
}
