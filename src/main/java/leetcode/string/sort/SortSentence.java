package leetcode.string.sort;

// https://leetcode.com/problems/sorting-the-sentence/
public class SortSentence {
    public String sortSentence(String s) {
        // Split the given sentence into words.
        String[] words = s.split(" ");

        // Create an array to hold the sorted words.
        String[] sorted = new String[words.length];

        for (String word : words) {
            // Extract the last character (the number) from the word.
            int index = word.charAt(word.length() - 1) - '1';  // Convert to 0-based index

            // Place the word (without the number) in the correct position in the sorted array.
            sorted[index] = word.substring(0, word.length() - 1);
        }

        // Join the words back into a sentence and return.
        return String.join(" ", sorted);
    }

    public static void main(String[] args) {
        SortSentence sol = new SortSentence();
        System.out.println(sol.sortSentence("is2 sentence4 This1 a3"));  // Expected output: "This is a sentence"
        System.out.println(sol.sortSentence("Myself2 Me1 I4 and3"));  // Expected output: "Me Myself and I"
    }

}
