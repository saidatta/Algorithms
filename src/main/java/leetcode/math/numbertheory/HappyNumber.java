package leetcode.math.numbertheory;

// https://leetcode.com/problems/happy-number/
public class HappyNumber {
    public boolean isHappy(int n) {
        int tortoise = n;
        int hare = getNext(n);

        while (hare != 1 && hare != tortoise) {
            tortoise = getNext(tortoise);
            hare = getNext(getNext(hare));
        }

        return hare == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        HappyNumber obj = new HappyNumber();
        System.out.println(obj.isHappy(19));  // Expected output: true
        System.out.println(obj.isHappy(2));   // Expected output: false
    }
}

