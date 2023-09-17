package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 254
 * https://leetcode.com/problems/factor-combinations
 *
 * Created by venkatamunnangi on 12/21/16.
 */
public class FactorCombinations {
    /**
     * Get all unique combinations of factors of a number.
     * @param n The number for which to get factor combinations.
     * @return A list of lists containing combinations of factors.
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        findCombinations(n, combinations, new ArrayList<>(), 2);
        return combinations;
    }

    /**
     * Recursive helper method to find factor combinations.
     *
     * @param n The current dividend.
     * @param combinations The list to store valid factor combinations.
     * @param currentFactors The current factor combination.
     * @param start The current factor to consider.
     */
    private void findCombinations(int n, List<List<Integer>> combinations, List<Integer> currentFactors, int start) {
        // If n is reduced to 1 and we have more than one factor, add the current combination to the result
        if (n <= 1) {
            if (currentFactors.size() > 1) {
                combinations.add(new ArrayList<>(currentFactors));
            }
            return;
        }

        // For each number from 'start' to the square root of n, try dividing n
        for (int i = start; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                currentFactors.add(i);
                findCombinations(n / i, combinations, currentFactors, i);
                currentFactors.remove(currentFactors.size() - 1);
            }
        }

        // At the end of the of factor chain the sqrt function will not include the final number
        // 2 x 2 x 3 - 3 in this factor chain.
        currentFactors.add(n);
        findCombinations(n/n, combinations, currentFactors, n);
        currentFactors.remove(currentFactors.size() - 1);
    }

    public static void main(String[] args) {
        FactorCombinations solver = new FactorCombinations();
        System.out.println(solver.getFactors(1));
    }

    interface Command {
        void execute();
    }

    class GetFactorsCommand implements Command {
        private int n;
        private List<List<Integer>> answer;
        private List<Integer> factors;
        private int start;

        public GetFactorsCommand(int n, List<List<Integer>> answer, List<Integer> factors, int start) {
            this.n = n;
            this.answer = answer;
            this.factors = factors;
            this.start = start;
        }

        @Override
        public void execute() {
            if(n <= 1) {
                if(factors.size() > 1) {
                    answer.add(new ArrayList<>(factors));
                }
                return;
            }
            for (int i = start; i <= Math.sqrt(n); i++) {
                if(n % i == 0) {
                    factors.add(i);
                    new GetFactorsCommand(n/i, answer, factors, i).execute();
                    factors.remove(factors.size() - 1);
                }
            }

            factors.add(n);
            new GetFactorsCommand(1, answer, factors, n).execute();
            factors.remove(factors.size()-1);
        }
    }
}
