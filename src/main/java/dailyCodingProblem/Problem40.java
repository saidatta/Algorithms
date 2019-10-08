package dailyCodingProblem;

/**
 * Created by venkatamunnangi on 9/29/19.
 */
public class Problem40 {
//    Given a non-empty array of integers, every element appears twice except for one. Find that single one.
//
//    Note:

    public static int singleNumber(int... nums) {

        int[] resultArray = new int[32];

        // Basically, once we encounter number that is multiple of 3, then we set the bits for it to 0.
        // Eventually, the goal is that outstanding bit will be left alone as the bits wont add up to 3 (total count).
        for(int num : nums) {
            for(int i = 0; i < 32; i++) {
                int bit = num >> i & 1;
                resultArray[i] = (resultArray[i]+ bit) % 3;
            }
        }

        int result = 0;

        for(int i = 0; i < resultArray.length; i++) {
            int bit = resultArray[i];
            if(bit != 0) {
                result += Math.pow(2, i);
            }
        }

        return result;
    }

    public static void main(String [] args) {
        System.out.println(singleNumber(13,13,13,12));
    }
}
