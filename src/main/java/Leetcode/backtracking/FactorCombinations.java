package Leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 254
 * https://leetcode.com/problems/factor-combinations
 *
 * Created by venkatamunnangi on 12/21/16.
 */
public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> answer = new ArrayList<>();
        getFactorsHelper(n, answer, new ArrayList<>(), 2);
        return answer;

//            List<List<Integer>> answer = new ArrayList<>();
//            new GetFactorsCommand(n, answer, new ArrayList<>(), 2).execute();
//            return answer;
    }

    public void getFactorsHelper(int n, List<List<Integer>> answer, List<Integer> factors, int start) {
        if(n <= 1) {
            if(factors.size() > 1) {
                answer.add(new ArrayList<>(factors));
            }
            return;
        }
        for (int i = start; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                factors.add(i);
                getFactorsHelper(n/i, answer, factors, i);
                factors.remove(factors.size() - 1);
            }
        }

        // At the end of the of factor chain the sqrt function will not include the final number
        // 2 x 2 x 3 - 3 in this factor chain.
        factors.add(n);
        getFactorsHelper(n/n, answer, factors, n);
        factors.remove(factors.size()-1);
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
