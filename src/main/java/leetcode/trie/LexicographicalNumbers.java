package leetcode.trie;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/lexicographical-numbers/description/
public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(i, n, result);
        }
        return result;
    }

    private void dfs(int current, int n, List<Integer> result) {
        if (current > n) {
            return;
        }
        result.add(current);
        for (int i = 0; i <= 9; i++) {
            int next = 10 * current + i;
            if (next > n) {
                return;
            }
            dfs(next, n, result);
        }
    }

    public static void main(String[] args) {
        LexicographicalNumbers solution = new LexicographicalNumbers();
        System.out.println(solution.lexicalOrder(13));  // Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
        System.out.println(solution.lexicalOrder(2));   // Output: [1,2]
    }
}
