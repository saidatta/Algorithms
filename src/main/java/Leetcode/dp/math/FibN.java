package Leetcode.dp.math;

/**
 * https://leetcode.com/problems/fibonacci-number/description/
 */
public class FibN {
    public int fib(int n) {
        if(n==0 || n==1){
            return n;
        }
        int a = 0, b = 1, ans = 0;
        for(int i = 1; i < n; i++){
            ans = a + b; //fn
            a = b; // f(n-2)
            b = ans; // f(n-1)
        }
        return ans;
    }
}
