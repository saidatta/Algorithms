package Leetcode.dp.string;

/**
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/description/
 */
public class MinFlipsMonoIncrement {
    //010110 011111 or 000111
    //00110 00111
    public int minFlipsMonoIncr(String s) {
        // flips denotes the number of flips we have made so far
        int flips = 0;
        // denotes the number of 1s we have seen so far
        int count_ones = 0;

//        for(int i = 0; i < s.length(); i++) {
        for(char c : s.toCharArray()) {
            // calculating the number of flips required to make the string[0:i] monotone increasing
            if(c == '1') {
                // 1 is ok, if the last digit is 1, it will make a monotonic increasing array
                count_ones++;
            } else {
                // 0 is problem. We have 2 choices here.
                // Either flip this 0 to 1, in this case our num flips will increase by 1
                // Or we can let this 0 be as is, but then we have to flip all the previous 1s to 0, this is why we
                // need the count_one variable
                flips = Math.min(flips + 1, count_ones);
            }
        }
        return flips;
    }
}
