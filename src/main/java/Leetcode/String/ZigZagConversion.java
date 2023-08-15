package Leetcode.String;

/**
 * https://leetcode.com/problems/zigzag-conversion/#/description
 *
 * Created by venkatamunnangi on 11/29/16.
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        StringBuilder sb = new StringBuilder();
        // step
        int step = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            //first & last rows
            if (i == 0 || i == numRows - 1) {
                for (int j = i; j < s.length(); j = j + step) {
                    sb.append(s.charAt(j));
                }
                //middle rows
            } else {
                int j = i;
                boolean flag = true;
                int step1 = 2 * (numRows - 1 - i);
                int step2 = step - step1;

                while (j < s.length()) {
                    sb.append(s.charAt(j));
                    if (flag)
                        j = j + step1;
                    else
                        j = j + step2;
                    flag = !flag;
                }
            }
        }

        return sb.toString();
    }

    public String convert2(String givenString, int numRows) {
        if (numRows == 1) {
            return givenString;
        }

        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }

        int index = 0;
        int direction = 1; // 1 represents down, -1 represents up

        for (char currentChar : givenString.toCharArray()) {
            sb[index].append(currentChar);
            if (index == 0) {
                direction = 1;
            } else if (index == numRows - 1) {
                direction = -1;
            }
            index += direction;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : sb) {
            result.append(row.toString());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion zzc = new ZigZagConversion();
        System.out.println(zzc.convert2("PAYPALISHIRING", 3)); // Output: "PAHNAPLSIIGYIR"
        System.out.println(zzc.convert("PAYPALISHIRING", 4)); // Output: "PINALSIGYAHRPI"
        System.out.println(zzc.convert("A", 1)); // Output: "A"
    }
}
