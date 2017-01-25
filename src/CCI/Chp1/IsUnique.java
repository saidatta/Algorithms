package CCI.Chp1;

/**
 *  Problem 1
 * Created by venkatamunnangi on 1/23/17.
 */
public class IsUnique {
    public boolean isUnique(String s) {

        if(s == null || s.trim().isEmpty()) {
            return true;
        }

        int [] charCount = new int[256];

        for(char c : s.toCharArray()) {
            if(charCount[c] > 1) {
                return false;
            }
            charCount[c]++;
        }


        return true;
    }

    public boolean isLowercaseUnique(String s) {
        if(s == null || s.trim().isEmpty()) {
            return true;
        }
        int flag = 0;
        for(int i = 0; i<s.length();i++) {
            int curr = s.charAt(i) - 'a';
            if((flag & (1 << curr)) > 0)
                return false;
            flag |= 1 << curr;
        }
        return true;
    }
}
