package leetcode.dp.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/different-ways-to-add-parentheses/
// https://leetcode.com/problems/different-ways-to-add-parentheses/solutions/1294189/easy-solution-faster-than-100-explained-with-diagrams/
// think of it as symbol expression trees
public class DifferentWaysAddParentheses {

    // Cache to store results of sub-expressions
    private final Map<String, List<Integer>> cache = new HashMap<>();

    // 2-1-1
    public List<Integer> diffWaysToCompute(String expression) {
        if (cache.containsKey(expression)) {
            return cache.get(expression);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Split the expression at each operator
            if (c == '+' || c == '-' || c == '*') {
                String leftPart = expression.substring(0, i);
                String rightPart = expression.substring(i + 1);

                List<Integer> leftResults = diffWaysToCompute(leftPart);
                List<Integer> rightResults = diffWaysToCompute(rightPart);

                // Combine results from left and right parts
                for (int left : leftResults) {
                    for (int right : rightResults) {
                        int combinedResult = switch (c) {
                            case '+' -> left + right;
                            case '-' -> left - right;
                            case '*' -> left * right;
                            default -> 0;
                        };
                        result.add(combinedResult);
                    }
                }
            }
        }

        // If the expression is a number, add it to the result
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }

        // Cache the result for future use
        cache.put(expression, result);
        return result;
    }

    public static void main(String[] args) {
        DifferentWaysAddParentheses solution = new DifferentWaysAddParentheses();
        System.out.println(solution.diffWaysToCompute("2-1-1"));  // Output: [0, 2]
        System.out.println(solution.diffWaysToCompute("2*3-4*5"));  // Output: [-34, -14, -10, -10, 10]
    }
}
