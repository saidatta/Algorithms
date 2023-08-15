package Int.google;

import java.util.*;

/**
 * Query API - for customers that want to call us
 * (prediction of words)
 *
 *
 * Training data:
 * I like sports;
 * like dog
 * I am Sam;
 *
 * Sample randomPick() inputs:
 * "I" "like" "sports"
 *
 *   Query:
 *
 * like -> like / dog;// 2/3 return sports, 1/3  return dog
 *
 *
 * Implement an autocompleter. Given training data, make it so that if a string is suggested, subsequent words have a WEIGHTED PROBABILITY chance of being chosen.
 * Two aspects of this problem: Training() & RandomPick().
 * First, I focused on RandomPick, as I knew that we could reduce the TC of searching for a random number based on prefix sum from O(N) to O(log N) if we used a binary search based solution i.e.
 * dog dog goat goat pig = dog 2 goat 4 pig 5 -> given a random double from range [0,5], the string chosen should be that of the key larger than or equal to it.
 *
 * Then I had to clarify the problem, as I suggested using a Trie for training purposes, but the interviewer mentioned he wanted only the relationship between adjacent words, not the entire context of the sentence.
 * The interviewer then insisted I do the training coding first over the random pick.
 * The training code was actually more involved than I thought it would be, and it actually involved two sets of HashMaps.
 * 1. A Hashmap to store Parent -> children, TOTAL counts i.e. given the above example i would store: dog,2 goat,2 pig,1
 * 2. A Hashmap with keys based on Parent, and a Treemap of Integer breakpoints and their String counterparts.
 *
 * We would use #1 to get counts, and for each Parent string, we would then use it to create the sorted prefix sum for #2.
 * I required a little help from the interviewer to keep the code clean, but they seemed to appreciate that I made it this far.
 * Since I used a Java TreeMap (basically sorted map), I was able to utilize some in built functions to perform binary search for randomPick(), although I did ask my interviewer whether or not they want to see my binary search template (they declined haha).
 *
 * Overall, a very coding heavy portion of the interview. It was nice to see that my coding implementation was actually pretty solid when it came to mapping, and I came up with the O(log n) approach to random pick
 */
public class Autocompleter {
    private final Map<String, Map<String, Integer>> parentToChildrenCountMap = new HashMap<>();
    private final Map<String, TreeMap<Integer, String>> parentToChildrenPrefixSumMap = new HashMap<>();
    private final Random random = new Random();

    public void train(String sentence) {
        String[] words = sentence.split("\\s+");

        for (int i = 0; i < words.length - 1; i++) {
            String parent = words[i];
            String child = words[i + 1];

            parentToChildrenCountMap.putIfAbsent(parent, new HashMap<>());
            parentToChildrenCountMap.get(parent).put(child, parentToChildrenCountMap.get(parent).getOrDefault(child, 0) + 1);
        }

        for (String parent : parentToChildrenCountMap.keySet()) {
            int prefixSum = 0;
            TreeMap<Integer, String> prefixSumMap = new TreeMap<>();

            for (Map.Entry<String, Integer> childEntry : parentToChildrenCountMap.get(parent).entrySet()) {
                prefixSum += childEntry.getValue();
                prefixSumMap.put(prefixSum, childEntry.getKey());
            }

            parentToChildrenPrefixSumMap.put(parent, prefixSumMap);
        }
    }

    public String randomPick(String parent) {
        if (!parentToChildrenPrefixSumMap.containsKey(parent)) {
            return null;
        }

        int total = parentToChildrenPrefixSumMap.get(parent).lastKey();
        int randomIndex = random.nextInt(total);

        return parentToChildrenPrefixSumMap.get(parent).higherEntry(randomIndex).getValue();
    }

    public static void main(String[] args) {
        Autocompleter autocompleter = new Autocompleter();

        autocompleter.train("I like sports");
        autocompleter.train("I like dog");
        autocompleter.train("I am Sam");

        System.out.println(autocompleter.randomPick("I"));
        System.out.println(autocompleter.randomPick("like"));
        System.out.println(autocompleter.randomPick("sports"));
    }
}

