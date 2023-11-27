package leetcode.trie;

import java.util.*;

class SearchSuggestions {
    private SearchSuggestionTrieNode root;

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        root = new SearchSuggestionTrieNode();
        Arrays.sort(products); // Sort products lexicographically
        buildTrie(products); // Build the Trie

        return searchSuggestions(searchWord);
    }

    private void buildTrie(String[] products) {
        for (String product : products) {
            SearchSuggestionTrieNode node = root;
            for (char c : product.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new SearchSuggestionTrieNode();
                }
                node = node.children[c - 'a'];
                if (node.products.size() < 3) {
                    node.products.add(product);
                }
            }
        }
    }

    private List<List<String>> searchSuggestions(String searchWord) {
        List<List<String>> result = new ArrayList<>();
        SearchSuggestionTrieNode node = root;
        for (char c : searchWord.toCharArray()) {
            if (node != null) {
                node = node.children[c - 'a'];
            }
            result.add(node != null ? node.products : new ArrayList<>());
        }
        return result;
    }
}

class SearchSuggestionTrieNode {
    SearchSuggestionTrieNode[] children;
    List<String> products;

    public SearchSuggestionTrieNode() {
        children = new SearchSuggestionTrieNode[26]; // 26 lowercase English letters
        products = new ArrayList<>();
    }
}
