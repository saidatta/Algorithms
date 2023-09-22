package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/expression-add-operators/#/description
 * <p>
 * This problem has a lot of edge cases to be considered:
 * <p>
 * overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
 * 0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal
 * with it too.
 * a little trick is that we should save the value that is to be multiplied in the next recursion.
 * <p>
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 * <p>
 * Created by venkatamunnangi on 3/27/17.
 */
import java.util.List;
import java.util.ArrayList;

public class ExpressionAddOperators {

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();

        // Edge case where num is null or empty
        if (num == null || num.length() == 0) {
            return result;
        }

        findCombinations(result, num, target, "", 0, 0L, 0L);
        return result;
    }

    /**
     * Recursive method to find combinations of expressions that match the target.
     *
     * @param result The list to store valid expressions.
     * @param num The input string number.
     * @param target The target sum for the expressions.
     * @param path The current expression path.
     * @param position The current position in num.
     * @param evaluatedExpression The result of the current expression path.
     * @param prevMultiplied The last multiplied value for considering precedence of multiplication.
     */
    private void findCombinations(List<String> result,
                                  String num,
                                  int target,
                                  String path,
                                  int position,
                                  long evaluatedExpression,
                                  long prevMultiplied) {
        // If we have processed the entire number string
        if (position == num.length()) {
            // If the current expression equals the target, add it to the result
            if (target == evaluatedExpression) {
                result.add(path);
            }
            return;
        }

        // Check each possible number from the current position
        for (int i = position; i < num.length(); i++) {
            // Zero can't be the start of a number
            if (i != position && num.charAt(position) == '0') {
                break;
            }

            long current = Long.parseLong(num.substring(position, i + 1));

            // If it's the start of the expression, just add the current number
            if (position == 0) {
                findCombinations(result, num, target, path + current, i + 1, current, current);
            } else {
                // Otherwise, explore possibilities with '+', '-' and '*'
                findCombinations(result, num, target, path + "+" + current, i + 1, evaluatedExpression + current, current);
                findCombinations(result, num, target, path + "-" + current, i + 1, evaluatedExpression - current, -current);
                // because of PEMDAS - 2 + 3 * 4
                findCombinations(result, num, target, path + "*" + current, i + 1,
                        evaluatedExpression - prevMultiplied + prevMultiplied * current,
                        prevMultiplied * current
                );
            }
        }
    }

    public static void main(String[] args) {
        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
        out.println(expressionAddOperators.addOperators("105", 1));
//        out.println(expressionAddOperators.addOperators("123", 5));
//        out.println(expressionAddOperators.addOperators("321", 6));
//        out.println(expressionAddOperators.addOperators(null, 6));
//        out.println(expressionAddOperators.addOperators("123", -11));
//        out.println(expressionAddOperators.addOperators("123", -1));
//        out.println(expressionAddOperators.addOperators("105", 5));
//        out.println(expressionAddOperators.addOperators("100", 5));
//        out.println(expressionAddOperators.addOperators("199", 18));
//        out.println(expressionAddOperators.addOperators("234", 100));
//        out.println(expressionAddOperators.addOperators("9999999999", 100));
    }
//    record DFSContext(List<String> result, String num, int target, String path, int position, long evaluatedExpression, long prevMultiplied, long current) {}
//
//    interface DFSHelperStrategy {
//        void execute(DFSContext context);
//    }
//
//    class InitialDFSHelperStrategy implements DFSHelperStrategy {
//        public void execute(DFSContext context) {
//            new DFSHelper(new DFSContext(context.result(), context.num(), context.target(), context.path() + context.current(), context.position() + 1, context.current(), context.current())).execute();
//        }
//    }
//
//    class AdditionDFSHelperStrategy implements DFSHelperStrategy {
//        public void execute(DFSContext context) {
//            new DFSHelper(new DFSContext(context.result(), context.num(), context.target(), context.path() + "+" + context.current(), context.position() + 1, context.evaluatedExpression() + context.current(), context.current())).execute();
//        }
//    }
//
//    class SubtractionDFSHelperStrategy implements DFSHelperStrategy {
//        public void execute(DFSContext context) {
//            new DFSHelper(new DFSContext(context.result(), context.num(), context.target(), context.path() + "-" + context.current(), context.position() + 1, context.evaluatedExpression() - context.current(), -context.current()))
//                    .execute();
//        }
//    }
//
//    class MultiplicationDFSHelperStrategy implements DFSHelperStrategy {
//        public void execute(DFSContext context) {
//            new DFSHelper(new DFSContext(context.result(), context.num(), context.target(), context.path() + "*" + context.current(), context.position() + 1, context.evaluatedExpression() - context.prevMultiplied() + context.prevMultiplied() * context.current(), context.prevMultiplied() * context.current())).execute();
//        }
//    }
//
//    class DFSHelper {
//        private List<DFSHelperStrategy> strategies;
//        private DFSContext context;
//
//        DFSHelper(DFSContext context) {
//            this.context = context;
//            this.strategies = List.of(new InitialDFSHelperStrategy(), new AdditionDFSHelperStrategy(), new SubtractionDFSHelperStrategy(), new MultiplicationDFSHelperStrategy());
//        }
//
//        void execute() {
//            if (context.position() == context.num().length()) {
//                if (context.target() == context.evaluatedExpression()) {
//                    context.result().add(context.path());
//                }
//                return;
//            }
//
//            for (int i = context.position(); i < context.num().length(); i++) {
//                if (i != context.position() && context.num().charAt(context.position()) == '0') {
//                    break;
//                }
//
//                long current = Long.parseLong(context.num().substring(context.position(), i + 1));
//                DFSContext newContext = new DFSContext(context.result(), context.num(), context.target(), context.path(), i + 1, context.evaluatedExpression(), context.prevMultiplied(), current);
//
//                for (DFSHelperStrategy strategy : strategies) {
//                    strategy.execute(newContext);
//                }
//            }
//        }
//    }


}
