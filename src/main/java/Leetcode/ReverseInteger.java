package Leetcode;

public class ReverseInteger {
    public int reverse(int x) {
        int result = 0;

        while ( x != 0) {
            int currentDigit = x % 10;
            int newResult = result * 10 + currentDigit;

            if((newResult - currentDigit) / 10 != result) {
                return 0;
            }
            result = newResult;
            x /= 10;
        }

        return result;
    }
}
