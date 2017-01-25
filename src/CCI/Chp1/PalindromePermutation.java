package CCI.Chp1;

/**
 * Created by venkatamunnangi on 1/23/17.
 */
public class PalindromePermutation {
    public boolean isPalindromePermutation(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }

        int count = 0;
        int charCount[] = new int[s.length()];

        for(char c : s.toCharArray()) {
            charCount[c]++;
        }
        boolean isfound = false;

        for(int cc : charCount) {

            if(cc % 2 == 1) {
                if(isfound) {
                    return false;
                }
                isfound = true;
            }
        }

        return true;
    }
}
