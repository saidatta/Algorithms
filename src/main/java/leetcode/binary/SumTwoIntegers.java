package leetcode.binary;

// https://leetcode.com/problems/sum-of-two-integers/description/ - S tier editorial
public class SumTwoIntegers {
//    The idea is to use the bitwise XOR (^) operation to perform addition without carrying, and the bitwise
//    AND (&) operation to handle the carry. The process is repeated until there's no carry left.
    public int getSum(int a, int b) {
        while (b != 0) {
// Calculates the carry. carry will be 1 in each position where both a and b have 1.
            int carry = a & b;   // Carry, but don't add
// Adds a and b without considering the carry. The XOR operation effectively adds the bits where only one of the numbers has a 1.
            a = a ^ b;           // Add without carrying
// The carry is shifted one position to the left so that it can be added in the correct position in the next iteration.
            b = carry << 1;      // Shift carry to its correct position
        }
// This process is repeated until there is no carry, which is when b becomes 0. At this point,
// a contains the sum of the original a and b.
        return a;
    }

    public static void main(String[] args) {
        SumTwoIntegers solution = new SumTwoIntegers();
        System.out.println(solution.getSum(5, 5)); // Output: 3
        System.out.println(solution.getSum(2, 3)); // Output: 5
    }
}

// carry = a & b:
// a = a ^ b:
// b = carry << 1:
