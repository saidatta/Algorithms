package leetcode.string.array;

// https://leetcode.com/problems/verifying-an-alien-dictionary/description/
public class VerifyingAlienDictionary {

    // This will be our order mapping for O(1) look-ups
    private int[] orderIndex;

    public static void main(String[] args) {
        VerifyingAlienDictionary ad = new VerifyingAlienDictionary();
        String[] words = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(ad.isAlienSorted(words, order));  // Expected: true
    }

    public boolean isAlienSorted(String[] words, String order) {
        mapOrder(order);
        for (int i = 0; i < words.length - 1; i++) {
            if (compare(words[i], words[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    // This function maps characters in the order string to their corresponding index for quick look-ups.
    private void mapOrder(String order) {
        orderIndex = new int[26];
        for (int i = 0; i < order.length(); i++) {
            orderIndex[order.charAt(i) - 'a'] = i;
        }
    }

    // This function compares two alien words based on the given order.
    private int compare(String word1, String word2) {
        int i = 0;
        while (i < word1.length() && i < word2.length()) {
            int index1 = orderIndex[word1.charAt(i) - 'a'];
            int index2 = orderIndex[word2.charAt(i) - 'a'];
            if (index1 != index2) {
                return index1 - index2;
            }
            i++;
        }
        return word1.length() - word2.length();
    }
}
