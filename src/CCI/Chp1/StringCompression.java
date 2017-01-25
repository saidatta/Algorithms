package CCI.Chp1;

/**
 * Created by venkatamunnangi on 1/23/17.
 */
public class StringCompression {
    String compress(String s) {
        if(s == null || s.isEmpty()) {
            return s;
        }

        StringBuilder stringBuilder = new StringBuilder();

        char charArr[] = s.toCharArray();

        char cc = charArr[0];
        int count = 1;
        for(int i = 1; i< charArr.length; i++) {
            if (cc != charArr[i]) {
                stringBuilder.append(cc);
                stringBuilder.append(count);
                cc = charArr[i];
            } else {
                count++;
            }
        }

        return stringBuilder.toString();
    }
}
