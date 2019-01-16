package Leetcode;

import java.util.Arrays;

import static java.lang.System.out;

public class NextClosestTime {

    public String nextClosestTime(String time) {
        char[] result = time.toCharArray();
        char[] digits = new char[] {result[0], result[1], result[3], result[4]};
        Arrays.sort(digits);

        // find next digit for HH:M_
        result[4] = findNext(result[4], (char)('9' + 1), digits);  // no upperLimit for this digit, i.e. 0-9
        if(result[4] > time.charAt(4)) return String.valueOf(result);  // e.g. 23:43 -> 23:44

        // find next digit for HH:_M
        result[3] = findNext(result[3], '5', digits);
        if(result[3] > time.charAt(3)) return String.valueOf(result);  // e.g. 14:29 -> 14:41

        // find next digit for H_:MM
        result[1] = findNext(result[1], result[0] == '2' ? '3' : (char) ('9' + 1), digits);
        if(result[1] > time.charAt(1)) return String.valueOf(result);  // e.g. 02:37 -> 03:00

        // find next digit for _H:MM
        result[0] = findNext(result[0], '2', digits);
        return String.valueOf(result);  // e.g. 19:59 -> 11:11
    }

    /**
     * find the next bigger digit which is no more than upperLimit.
     * If no such digit exists in digits[], return the minimum one i.e. digits[0]
     * @param current the current digit
     * @param upperLimit the maximum possible value for current digit
     * @return
     */
    private char findNext(char current, char upperLimit, char[] digits) {
        if(current == upperLimit) {
            return digits[0];
        }

        int pos = Arrays.binarySearch(digits, current) + 1;

        while(pos < 4 && (digits[pos] > upperLimit || digits[pos] == current)) { // traverse one by one to find next greater digit
            pos++;
        }
        return digits[pos == 4 ? 0 : pos];
    }

    public static void main(String[] args) {
        // Create a graph given in the above diagram
        NextClosestTime nextClosestTime = new NextClosestTime();
        out.println(nextClosestTime.nextClosestTime("23:59"));
    }
}
