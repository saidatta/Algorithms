package leetcode.string;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/?tab=Description
 *
 * Created by venkatamunnangi on 3/12/17.
 */
public class ReverseWordsString {
    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        String[] words = s.split("\\s+");
        if (words.length == 0) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        for (int i = words.length - 1; i > 0; i--) {
            ans.append(words[i]).append(" ");
        }
        ans.append(words[0]);
        return ans.toString().trim();
    }

    public static void main(String[] args) {
        ReverseWordsString sol = new ReverseWordsString();
        System.out.println(sol.reverseWords("the sky is blue"));       // "blue is sky the"
        System.out.println(sol.reverseWords("  hello world  "));      // "world hello"
        System.out.println(sol.reverseWords("a good   example"));     // "example good a"
    }
}
