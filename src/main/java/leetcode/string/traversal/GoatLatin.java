package leetcode.string.traversal;

// https://leetcode.com/problems/goat-latin/
public class GoatLatin {
    public String toGoatLatin(String sentence) {
        // Split the sentence into words
        String[] words = sentence.split("\\s+");
        StringBuilder result = new StringBuilder();

        String vowels = "AEIOUaeiou";
        int countA = 1;

        for (String word : words) {
            // If the word starts with a vowel
            if (vowels.indexOf(word.charAt(0)) != -1) {
                result.append(word);
            } else {
                result
                        .append(word.substring(1))
                        .append(word.charAt(0));
            }

            // Append "ma" and the right number of "a" characters to the end of the word
            result
                    .append("ma")
                    .append("a".repeat(countA++));

            // Append a space if it's not the last word
            if (countA <= words.length) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        GoatLatin gl = new GoatLatin();
        // Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
        System.out.println(gl.toGoatLatin("I speak Goat Latin"));
        // Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa
        // ogdmaaaaaaaaaa"
        System.out.println(gl.toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }
}