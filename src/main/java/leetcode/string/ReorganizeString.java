package leetcode.string;

// https://leetcode.com/problems/reorganize-string/description/
class ReorganizeString {

    private static final int ALPHABET_SIZE = 26;

    public String reorganizeString(String s) {
        int[] freq = calculateFrequencies(s);
        int maxLetter = getMaxFrequencyLetter(freq);

        // if max frequency is more than half then not possible
        if (freq[maxLetter] > (s.length() + 1) / 2) {
            return "";
        }

        char[] res = arrangeCharacters(s, freq, maxLetter);
        return String.valueOf(res);
    }

    private int[] calculateFrequencies(String s) {
        int[] freq = new int[ALPHABET_SIZE];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        return freq;
    }

    private int getMaxFrequencyLetter(int[] freq) {
        int max = 0;
        int letter = 0;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (freq[i] > max) {
                max = freq[i];
                letter = i;
            }
        }
        return letter;
    }

    private char[] arrangeCharacters(String s, int[] freq, int maxLetter) {
        int idx = 0;
        char[] res = new char[s.length()];

        // distribute the max freq char into even indices
        while (freq[maxLetter] > 0) {
            res[idx] = (char) (maxLetter + 'a');
            idx += 2;
            freq[maxLetter]--;
        }

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            while (freq[i] > 0) {
                // all even indices filled, so switch to odd indices
                if (idx >= s.length()) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                freq[i]--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ReorganizeString solution = new ReorganizeString();

        // Test Case 1
        String s1 = "aab";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + solution.reorganizeString(s1));

        // Test Case 2
        String s2 = "aaab";
        System.out.println("\nInput: " + s2);
        System.out.println("Output: " + solution.reorganizeString(s2));

    }

}
