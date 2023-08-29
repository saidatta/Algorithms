package leetcode.math;

/**
 * https://leetcode.com/problems/multiply-strings/#/description
 *
 * Created by venkatamunnangi on 3/28/17.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] position = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int multipliedNumber = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = multipliedNumber + position[p2];

                position[p1] += sum / 10;
                position[p2] = (sum) % 10;
            }
        }

        StringBuilder multipliedNumberString = new StringBuilder();
        for(int p : position) {
            if(!(multipliedNumberString.length() == 0 && p == 0)) {
                multipliedNumberString.append(p);
            }
        }

        return multipliedNumberString.length() == 0 ? "0" : multipliedNumberString.toString();
    }
}
