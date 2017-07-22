package Leetcode.TODO;

/**
 * https://leetcode.com/problems/android-unlock-patterns/#/description
 *
 * Created by venkatamunnangi on 5/10/17.
 */
public class AndroidUnlockPatterns {
    // cur: the current position
    // remain: the steps remaining
    public int dfs(boolean[] visited, int[][] skipOverNumber, int cur, int remain) {
        if (remain < 0) {
            return 0;
        }
        if (remain == 0) {
            return 1;
        }
        visited[cur] = true;
        int result = 0;
        for (int i = 1; i <= 9; ++i) {
            // If visited[i] is not visited and (two numbers are adjacent or skipOverNumber number is already visited)
            if (!visited[i] && (skipOverNumber[cur][i] == 0 || (visited[skipOverNumber[cur][i]]))) {
                result += dfs(visited, skipOverNumber, i, remain - 1);
            }
        }
        visited[cur] = false;
        return result;
    }

    public int numberOfPatterns(int m, int n) {
        // Skip array represents number to skipOverNumber between two pairs
        int[][] skipOverNumber = new int[10][10];
        skipOverNumber[1][3] = skipOverNumber[3][1] = 2;
        skipOverNumber[1][7] = skipOverNumber[7][1] = 4;
        skipOverNumber[3][9] = skipOverNumber[9][3] = 6;
        skipOverNumber[7][9] = skipOverNumber[9][7] = 8;
        skipOverNumber[1][9] = skipOverNumber[9][1] = skipOverNumber[2][8] = skipOverNumber[8][2] = skipOverNumber[3][7] = skipOverNumber[7][3] = skipOverNumber[4][6] = skipOverNumber[6][4] = 5;

        boolean[] visited = new boolean[10];
        int result = 0;
        // dfs search each length from m to n
        for (int i = m; i <= n; ++i) {
            result += dfs(visited, skipOverNumber, 1, i - 1) * 4;    // 1, 3, 7, 9 are symmetric
            result += dfs(visited, skipOverNumber, 2, i - 1) * 4;    // 2, 4, 6, 8 are symmetric
            result += dfs(visited, skipOverNumber, 5, i - 1);        // 5
        }
        return result;
    }
}
