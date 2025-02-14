package Int.mongodb;

import java.util.*;

// inverted index


public class WordDictionary {
    private final Map<String, Set<Integer>> index; // Inverted index mapping words to documents
    private final List<String> addedWords;
    private final Map<String, Integer> docToDocId;

    /** Initialize your data structure here. */
    public WordDictionary() {
        index = new HashMap<>();
        addedWords = new ArrayList<>();
        docToDocId = new HashMap<>();
    }

    /** Adds a document to the index. */
    public void add(String doc) {
        String[] words = doc.toLowerCase().split("\\W+"); // Split document into words, ignoring punctuation
        int docId = addedWords.size();
        addedWords.add(doc);
        docToDocId.put(doc, docId);

        for (String word : words) {
            index.computeIfAbsent(word, k -> new HashSet<>()).add(docId);
        }
    }

    /** Deletes a document from the index. */
    public void delete(String doc) {
        String[] words = doc.toLowerCase().split("\\W+");
        int docId = docToDocId.get(doc);
        for (String word : words) {
            Set<Integer> docIds = index.get(word);
            if (docIds != null) {
                docToDocId.remove(doc);
                docIds.remove(docId);
                if (docIds.isEmpty()) {
                    // clean up if there are no tokens -> with the doc Ids.
                    index.remove(word);
                }
            }
        }
    }

    /** Searches for documents containing the given word. */
    public List<String> search(String word) {
        Set<Integer> foundDocIds = index.getOrDefault(word.toLowerCase(), Collections.emptySet());
        if (!foundDocIds.isEmpty()) {
            List<String> foundDocs = new ArrayList<>();
            for (int foundDocId : foundDocIds) {
                foundDocs.add(addedWords.get(foundDocId));
            }

            return foundDocs;
        }

        return Collections.emptyList();
    }

    /** Advanced search for documents containing the given words with the specified operator ("AND" or "OR"). */
    public List<Integer> advancedSearch(List<String> words, String operator) {
        if (words.isEmpty()) return Collections.emptyList();

        List<Set<Integer>> docSets = new ArrayList<>();
        for (String word : words) {
            docSets.add(index.getOrDefault(word.toLowerCase(), Collections.emptySet()));
        }

        Set<Integer> result = new HashSet<>(docSets.get(0));
        for (int i = 1; i < docSets.size(); i++) {
            if ("AND".equalsIgnoreCase(operator)) {
                result.retainAll(docSets.get(i));
            } else if ("OR".equalsIgnoreCase(operator)) {
                result.addAll(docSets.get(i));
            }
        }

        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        WordDictionary index = new WordDictionary();
        index.add("Atlas Search");
        index.add("Atlas Charts");
        System.out.println(index.search("Atlas")); // ["Atlas Search", "Atlas Charts"]
        System.out.println(index.search("Search")); // ["Atlas Search"]

        index.add("Pizza delivery service");
        index.add("Pizza is delicious");
        System.out.println(index.search("pizza")); // ["Pizza delivery service", "Pizza is delicious"]

        // Test delete
        index.delete("Atlas Search");
        System.out.println(index.search("Atlas")); // ["Atlas Charts"]

        // Test advancedSearch
        index.add("Pizza delivery service");
        index.add("Pizza is delicious");
        index.add("Delivery pizza is fast");
        index.add("Delivery  wass fast");
        System.out.println(index.advancedSearch(List.of("pizza", "delivery"), "AND")); // ["Pizza delivery service", "Delivery pizza is fast"]
        System.out.println(index.advancedSearch(List.of("pizza", "delivery"), "OR")); // ["Pizza delivery service", "Pizza is delicious", "Delivery pizza is fast"]
    }
}
