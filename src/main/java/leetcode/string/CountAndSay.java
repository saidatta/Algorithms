package leetcode.string;

/**
 * https://leetcode.com/problems/count-and-say/#/description
 *
 * Created by venkatamunnangi on 12/1/16.
 */
public class CountAndSay {
    public String countAndSay(int n) {
            StringBuilder sb = new StringBuilder("1");
            StringBuilder prev;

            for(int i = 1; i<n; i++) {
                prev = sb;
                char start = prev.charAt(0);
                sb = new StringBuilder();
                int count = 0;

                for (int j = 0; j < prev.length(); j++) {
                    if (start != prev.charAt(j)) {
                        sb.append(count).append(start);
                        start = prev.charAt(j);
                        count = 1;
                    } else {
                        count++;
                    }
                }
                sb.append(count).append(start);
            }

            return sb.toString();
    }


    public static void main(String [] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(3));
    }
}
