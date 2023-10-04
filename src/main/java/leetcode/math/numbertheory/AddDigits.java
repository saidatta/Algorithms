package leetcode.math.numbertheory;

/**
 * 258 - https://leetcode.com/problems/add-digits/
 * Created by venkatamunnangi on 12/29/16.
 */
public class AddDigits {
    public int addDigits(int num) {
        if(num < 10) {
            return num;
        }

        while(num >= 10) {
            int sum = 0;
            int curr = num;
            while (curr > 0){
                sum += curr %10;
                curr /= 10;
            }
            num = sum;
        }

        return num;
    }

    //If an integer is like 100a+10b+c, then (100a+10b+c)%9=(a+99a+b+9b+c)%9=(a+b+c)%9
    public int addDigits2(int num) {
        if(num == 0) {
            return 0;
        }

        if(num % 9 == 0) {
            return 9;
        } else {
            return num%9;
        }
    }
}
