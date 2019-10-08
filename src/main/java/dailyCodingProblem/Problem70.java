package dailyCodingProblem;

/**
 * Created by venkatamunnangi on 9/29/19.
 */
public class Problem70 {
    public int sumOfDigits(int n) {
        int current_sum = 0;
        while(n > 0) {
            current_sum += n%10;
            n /= 10;
        }

        return current_sum;
    }
    //O(n);
    public int perfect(int n) {
        int i = 0, currentPerfectNumber = 0;
        while (currentPerfectNumber < n) {
            i++;
            if (sumOfDigits(i) == 10) {
                currentPerfectNumber += 1;
            }

        }
        return i;
    }

}
