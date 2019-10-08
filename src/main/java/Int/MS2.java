package Int;

import java.util.Arrays;

/**
 * Created by venkatamunnangi on 9/16/19.
 */
public class MS2 {

    public int[] solution(int N) {
        if(N == 0) {
            return new int[0];
        }

        if(N == 1) {
            return new int[] {0};
        }

        int[] result = new int[N];

        int startIndex = (N/2)*(-1);
        for(int i = 0; i < N; i++) {
            result[i] = (startIndex++);

            if(N%2 == 0 && startIndex == 0) {
                startIndex++;
            }
        }

        return result;
    }

    public static void main(String [] args) {
        MS2 ms2 = new MS2();
        System.out.println(Arrays.toString(ms2.solution(3)));
        System.out.println(Arrays.toString(ms2.solution(2)));
        System.out.println(Arrays.toString(ms2.solution(1)));
        System.out.println(Arrays.toString(ms2.solution(0)));

        System.out.println(Arrays.toString(ms2.solution(4)));
        System.out.println(Arrays.toString(ms2.solution(Integer.MAX_VALUE)));
    }
}
