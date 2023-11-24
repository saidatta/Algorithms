package leetcode.string;

import java.util.ArrayList;;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-pairs/#/description
 *
 * Given words = ["bat", "tab", "cat"] Return [[0, 1], [1, 0]] The palindromes are ["battab", "tabbat"]
 *
 * Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.
 *
 * Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.
 *
 * Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.
 *
 * Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.
 *
 * To make the search faster, build a HashMap to store the String-idx pairs.
 *
 * Created by venkatamunnangi on 4/5/17.
 */

// https://leetcode.com/problems/palindrome-pairs/editorial/ - S tier
public class PalindromePairs {

    static class TrieNode {
        TrieNode[] children;
        int wordIndex;
        // are there any palindromes below.
        List<Integer> palindromesBelow;

        TrieNode() {
            children = new TrieNode[26];
            wordIndex = -1;
            palindromesBelow = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        TrieNode root = new TrieNode();

        // Build the trie with reversed words
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }

        // Search for palindrome pairs
        for (int i = 0; i < words.length; i++) {
            search(words, i, root, result);
        }

        return result;
    }

    private void addWord(TrieNode root, String word, int wordIndex) {
        TrieNode node = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int ch = word.charAt(i) - 'a';
            if (node.children[ch] == null) {
                node.children[ch] = new TrieNode();
            }
            if (isPalindrome(word, 0, i)) {
                node.palindromesBelow.add(wordIndex);
            }
            node = node.children[ch];
        }
        node.wordIndex = wordIndex;
        node.palindromesBelow.add(wordIndex);
    }

    private void search(String[] words, int wordIndex, TrieNode root, List<List<Integer>> result) {
        TrieNode node = root;
        String currentWord = words[wordIndex];

//        1. Words in the Trie that are the reverse of our current word.
//        2. Words in the Trie that start with the reverse of our current word and then finish in a palindrome.
//        3. Words in the Trie that are the reverse of the first part of our current word, and then what's left of our
//           current word forms a palindrome.

        for (int i = 0; i < currentWord.length(); i++) {
            // Check for a pair when we reach the end of a word in trie
            if (node.wordIndex >= 0 && node.wordIndex != wordIndex && isPalindrome(currentWord, i, currentWord.length() - 1)) {
                // found palindrome.
                result.add(List.of(wordIndex, node.wordIndex));
            }

            node = node.children[currentWord.charAt(i) - 'a'];
            if (node == null) return;
        }

        // Check the remaining words in trie
        for (int otherIndex : node.palindromesBelow) {
            if (wordIndex != otherIndex) {
                result.add(List.of(wordIndex, otherIndex));
            }
        }
    }

    private boolean isPalindrome(String word, int start, int end) {
        while (start < end) {
            if (word.charAt(start++) != word.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePairs solution = new PalindromePairs();

        System.out.println(solution.palindromePairs(
                new String[]{
                        "A", "B", "BAN", "BANANA", "BAT", "LOLCAT", "MANA", "NAB", "NANA", "NOON", "ON", "TA"
                        , "TAC"
                }));

        System.out.println(solution.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
        // Output: [[0,1],[1,0],[3,2],[2,4]]

        System.out.println(solution.palindromePairs(new String[]{"bat","tab","cat"}));
        // Output: [[0,1],[1,0]]

        System.out.println(solution.palindromePairs(new String[]{"a",""}));
        // Output: [[0,1],[1,0]]
    }
}
