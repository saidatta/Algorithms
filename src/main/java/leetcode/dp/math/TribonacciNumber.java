package leetcode.dp.math;

public class TribonacciNumber {
    public int tribonacci(int n) {
        if(n==0 || n==1){
            return n;
        }

        if(n==2) {
            return 1;
        }
        int a = 0, b = 1, c = 1, ans = 0;
        for(int i = 2; i < n; i++){
            ans = a + b + c; //fn
            a = b; // f(n-2)
            b = c;
            c = ans; // f(n-1)
        }
        return ans;
    }
}
