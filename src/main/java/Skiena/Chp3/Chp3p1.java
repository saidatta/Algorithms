package Skiena.Chp3;

/**
 * 3-1.
 * A common problem for compilers and text editors is determining whether the parentheses in a string
 * are balanced and properly nested. For example, the string ((())())() contains properly nested pairs of parentheses,
 * which the strings )()( and ()) do not. Give an algorithm that returns true if a string contains properly nested and
 * balanced parentheses, and false if otherwise.
 * For full credit, identify the position of the first offending parenthesis if the string is not properly nested and balanced.
 *
 * Created by venkatamunnangi on 12/28/16.
 */
public class Chp3p1 {
    public static boolean isBalanced(String str) {
        int count = 0;

        for (int i = 0, n = str.length(); i < n; i++) {
            switch (str.charAt(i)) {
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            if (count < 0) {
                System.out.println("Imbalance at index " + i);
                return false;
            }
        }

        if (count != 0) {
            System.out.println("Imbalance at index " + (str.length() - 1));
            return false;
        }

        return true;
    }


    public static boolean isBalanced2(String str) {
        int count = 0;

        for(int i = 0;i<str.length();i++) {
            switch(str.charAt(i)) {
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if(count <0) {
                return false;
            }
        }

        if(count!= 0) {
            return false;
        }
        return true;
    }
}
