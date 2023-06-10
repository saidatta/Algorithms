package Leetcode.String;

/**
 * Input: num = "51230100"
 * Output: "512301"
 * Explanation: Integer "51230100" has 2 trailing zeros, we remove them and return integer "512301".
 * Example 2:
 *
 * Input: num = "123"
 * Output: "123"
 * Explanation: Integer "123" has no trailing zeros, we return integer "123".
 *
 * https://leetcode.com/contest/weekly-contest-347/problems/remove-trailing-zeros-from-a-string/
 */
public class RemoveTrailingZerosString {
        public String removeTrailingZeros(String num) {

            StringBuilder sb = new StringBuilder();

            if(num.length() == 1) {
                return num;
            }

            int end = 0;
            for (int i = num.length() -1;i >= 1; i--) {
                if(num.charAt(i) != '0') {
                    end = i;
                    break;
                }
            }

            for (int i =0 ; i<=end; i++) {
                sb.append(num.charAt(i));
            }

            return sb.toString();
        }

        public static void main(String [] args) {
            RemoveTrailingZerosString removeTrailingZerosString = new RemoveTrailingZerosString();

            System.out.println(removeTrailingZerosString.removeTrailingZeros("51230100"));
        }
}
