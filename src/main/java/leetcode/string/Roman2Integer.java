package leetcode.string;

/**
 * Created by venkatamunnangi on 11/29/16.
 */
public class Roman2Integer {

    public int romanToInt(String s) {
        int ans = 0;

        for(int i = s.length() -1; i>=0; i--) {
            switch (s.charAt(i)) {
                case 'I':
                    ans = (ans < 5) ? ans + 1 : ans -1;
                    break;
                case 'V':
                    ans += 5;
                    break;
                case  'X':
                    ans = (ans < 50) ? ans + 10 : ans - 10;
                    break;
                case 'L':
                    ans += 50;
                    break;
                case 'C':
                    ans = (ans < 500) ? ans + 100 : ans - 100;
                    break;
                case 'D':
                    ans += 500;
                    break;
                case 'M':
                    ans += 1000;
                    break;
                default:
                    break;
            }
        }
        return ans;
    }
}
