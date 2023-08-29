package leetcode;

public class ReverseInteger {
    // 123456 654321
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

    // A functional interface that takes an int and returns an int
    @FunctionalInterface
    interface IntFunction {
        int apply(int number);
    }

    @FunctionalInterface
    interface OverFlowFunction {
        boolean apply(int number, int digit);
    }

    @FunctionalInterface
    interface ConcatFunction {
        int apply(int number, int digit);
    }

    // A lambda function that extracts the last digit of a number
    IntFunction extractLastDigit = number -> number % 10;

    // A lambda function that removes the last digit of a number
    IntFunction removeLastDigit = number -> number / 10;

    // A lambda function that checks if concatenating a digit to a number will cause overflow
    OverFlowFunction willOverflow = (number, digit) -> number != (number * 10 + digit - digit) / 10;

    // A lambda function that concatenates a digit to a number
    ConcatFunction concatenateLastDigit = (number, digit) -> number * 10 + digit;

    public int reverseNumber(int number) {
        int reversedNumber = 0;

        while (number != 0) {
            int lastDigit = extractLastDigit.apply(number);
            number = removeLastDigit.apply(number);

            if (willOverflow.apply(reversedNumber, lastDigit)) {
                return 0;
            }
            reversedNumber = concatenateLastDigit.apply(reversedNumber, lastDigit);
        }

        return reversedNumber;
    }
}
