package leetcode.math.numbertheory;

// https://leetcode.com/problems/count-square-sum-triples/
public class CountSquareSumTriples {
    public int countTriples(int n) {
        int count = 0;
        for (int a = 1; a < n; a++) {
            for (int b = a; b < n; b++) {
                int cSquare = a * a + b * b;
                int c = (int) Math.sqrt(cSquare);
                if (c * c == cSquare && c <= n) {
                    if (a != b) {
                        // (a, b, c) and (b, a, c) are both valid
                        count += 2;
                    } else {
                        // only one valid triple
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountSquareSumTriples st = new CountSquareSumTriples();
        System.out.println(st.countTriples(5));  // Output: 2
        System.out.println(st.countTriples(10)); // Output: 4
    }
}
