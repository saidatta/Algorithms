package Int;

/**
 * Created by venkatamunnangi on 02/11/16.
 */
public class PermutationOfPalindrome {

    public boolean isPermutationOfPalindrome(String phrase) {

        int [] freq = new int[26];

        int numOfNonEven = 0;

        for(int i = 0; i < phrase.length();i++) {
            char curr = phrase.charAt(i);

            freq[curr-'a']++;
        }

        for(int i = 0; i < freq.length;i++) {
            int curr = freq[i];

            if(curr % 2 != 0) {
                numOfNonEven++;
            }
        }

        return numOfNonEven < 2;
    }

    public static void main(String [] args) {
        PermutationOfPalindrome palindrome = new PermutationOfPalindrome();

        System.out.println(palindrome.isPermutationOfPalindrome("attaxy"));
    }


}
