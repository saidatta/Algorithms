package Int;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by venkatamunnangi on 9/16/19.
 */
public class MS4 {
        public  static int solution(int[] A){
            Map<Integer, Integer> freqMap = new HashMap<>();

            for(int i : A) {
                if(freqMap.containsKey(i)) {
                    freqMap.put(i, freqMap.get(i)+1);
                } else {
                    freqMap.put(i, 1);
                }
            }
            //Every appearance there are n Choose 2 = n(n-1)/2 pairs
            int totalSum=0;

            for(Integer n : freqMap.keySet()) {

                int valuePair = freqMap.get(n);
                totalSum += (valuePair * (valuePair-1))/2;
            }

            if(totalSum > 1_000_000_000) {
                return 1_000_000_000;
            }

            return totalSum;
        }

    public static void main(String args[]) {
        int A[] = {3, 5, 6, 3, 3, 5, 3, 5};
        //int A[] = {};
        System.out.println(solution(A));
        int A2[] = {3, 5, 6, 3, 3, 5};
        System.out.println(solution(A2));
    }

}
