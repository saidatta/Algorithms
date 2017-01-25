package CCI.Chp1;

/**
 * null
 * Empty spaces
 * One word with spaces
 * multiple words with single spaces in between
 * multiple words with multiple spaces in between
 * spaces before and after within the true length
 *
 * Created by venkatamunnangi on 1/23/17.
 */
public class Urlify {
    public String urlify(String s, int stringSize) {
        if(s == null || s.isEmpty()) {
            return s;
        }
        if(stringSize == 0) {
            return null;
        }

        int numOfSpaces = 0;

        for(int i = 0; i< stringSize; i++) {
            if(s.charAt(i) == ' ') {
                numOfSpaces++;
            }
        }
        if(numOfSpaces == 0) {
            return s;
        }

        int resultSize = stringSize + numOfSpaces*2;
        char [] result = new char[resultSize];

        int rs = 0;
        for(int i = 0; i<stringSize; i++) {
            if(s.charAt(i) == ' ') {
                result[rs++] = '%';
                result[rs++] = '2';
                result[rs++] = '0';
            }

            result[rs++] = s.charAt(i);
        }

        return new String(result);
    }
}
