package Leetcode;

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
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }

        dfsHelper(result,num, target, "", 0, 0, 0);
        return result;
    }

    private void dfsHelper(List<String> result, String num, int target, String path, int position, long evaludatedExpression, long prevMultiplied) {
        if(position == num.length()) {
            // base case.
            if(target == evaludatedExpression) {
                result.add(path);
            }
            return;
        }

        for(int i = position; i<num.length();i++) {
            if(i != position && num.charAt(position) == '0') {
                break;
            }
            long current = Long.parseLong(num.substring(position, i+1));
            if(position == 0) {
                dfsHelper(result, num, target, path+current, i+1, current, current);
            } else {
                dfsHelper(result, num, target,path+"+" +current, i+1, evaludatedExpression+current, current);
                dfsHelper(result, num, target, path+"-"+current, i+1, evaludatedExpression-current, -current);
                dfsHelper(result, num, target, path+"*"+current, i+1, evaludatedExpression-prevMultiplied+prevMultiplied*current, prevMultiplied*current);
            }
        }
    }

    public static void main(String[] args) {
        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
        out.println(expressionAddOperators.addOperators("123", 6));
        out.println(expressionAddOperators.addOperators("123", 5));
        out.println(expressionAddOperators.addOperators("321", 6));
        out.println(expressionAddOperators.addOperators(null, 6));
        out.println(expressionAddOperators.addOperators("123", -11));
        out.println(expressionAddOperators.addOperators("123", -1));
        out.println(expressionAddOperators.addOperators("105", 5));
        out.println(expressionAddOperators.addOperators("100", 5));
        out.println(expressionAddOperators.addOperators("199", 18));
        out.println(expressionAddOperators.addOperators("234", 100));
        out.println(expressionAddOperators.addOperators("9999999999", 100));
    }
}
