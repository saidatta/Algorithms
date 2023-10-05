package leetcode.math;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/add-to-array-form-of-integer/description/
public class AddArrayFormInteger {
    // [1,2,0,0] ; 34; [1,2,3,4]
    public List<Integer> addToArrayForm(int[] A, int K) {
        for (int i = A.length - 1; i >= 0; i--) {
            if (K == 0) {
                break;
            }
            int sum = A[i] + K;
            A[i] = sum % 10;
            K = sum / 10;
        }

        List<Integer> result = new ArrayList<>();
        while (K > 0) {
            // when K > A.length, add to start of result.
            result.add(0, K % 10);
            K /= 10;
        }
        for (int num : A) {
            result.add(num);
        }

        return result;
    }

    public static void main(String[] args) {
        AddArrayFormInteger solution = new AddArrayFormInteger();

        // Test cases
        int[] A1 = {1, 2, 9, 9};
        int K1 = 34;
        System.out.println(solution.addToArrayForm(A1, K1)); // Expected output: [1, 2, 3, 4]

        int[] A2 = {2, 7, 4};
        int K2 = 181;
        System.out.println(solution.addToArrayForm(A2, K2)); // Expected output: [4, 6, 5]

        int[] A3 = {2, 1, 5};
        int K3 = 806;
        System.out.println(solution.addToArrayForm(A3, K3)); // Expected output: [1, 0, 2, 1]
    }

}
