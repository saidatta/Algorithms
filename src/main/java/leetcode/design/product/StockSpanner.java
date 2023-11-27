package leetcode.design.product;

import java.util.Stack;

public class StockSpanner {
    private final Stack<PriceSpan> stack;

    StockSpanner() {
        stack = new Stack<>();
    }

    int next(int todayPrice) {
        int span = 1;
        while(!stack.isEmpty() && stack.peek().price <= todayPrice) {
            span += stack.pop().span;
        }

        stack.push(new PriceSpan(todayPrice, span));
        return span;
    }

    public record PriceSpan(int price, int span) { }
}
