package leetcode.array;

import java.util.*;

/**
 * Given a string, we can "shift" each of its letter to its successive letter,
 * for example: "abc" ->"bcd". We can keep "shifting" which forms the sequence:
 * "abc" ->"bcd" ->... ->"xyz". Given a list of strings which contains only lowercase alphabets, group all strings
 * that belong to the same shifting sequence, return:
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 *
 * https://leetcode.com/problems/group-shifted-strings/
 *
 * Created by venkatamunnangi on 9/7/19.
 */
public class GroupShiftedStrings {

    public List<List<String>> groupStrings(String... inputStrings) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String currentString : inputStrings) {
            char[] currentCharArray = currentString.toCharArray();
            if (currentCharArray.length > 0) {
                int diff = currentCharArray[0] - 'a';
                for (int i = 0; i < currentCharArray.length; i++) {
                    if (currentCharArray[i] - diff < 'a') {
                        currentCharArray[i] = (char) (currentCharArray[i] - diff + 26);
                    } else {
                        currentCharArray[i] = (char) (currentCharArray[i] - diff);
                    }
                }
            }

            String translatedString = new String(currentCharArray);
            if (map.containsKey(translatedString)) {
                map.get(translatedString).add(currentString);
            } else {
                ArrayList<String> al = new ArrayList<String>();
                al.add(currentString);
                map.put(translatedString, al);
            }
        }
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }
        return new ArrayList<>(map.values());
    }


//    This problem is loosely based on the concept of a rotation cipher. The key to solving this problem are 2 fold
//
//    Identify the logic that helps in deriving a unique identifier key that maps all strings of the same type together.
//    How to handle single character strings.
//    The way I approached this problem was to use a weight function that helps in calculating a unique key for each
//    string in the input array. Its important to note that in a rotation cipher, when we get a negative value after
//    calculating a difference of 2 characters, it has to be translated as the distance between these 2 characters in
//    the reverse order. The way to normalize this is to add the range of the cipher keys in the diff - thereby
//    calculating the distance in the reverse order. Voila!!
//
//    In this solution, I am using separator characters - & * to help distinguish between character ascii values like
//    11 vs 1-1
//
//    https://en.wikipedia.org/wiki/Caesar_cipher
// A map to store the grouped strings. The key represents the "weight" of the strings and the value is a list of strings that share that weight.
private final Map<String, List<String>> map = new HashMap<>();

    // The range for the Caesar cipher. Since there are 26 letters in the English alphabet, our range is 26.
    private final int CIPHER_RANGE = 26;

    /**
     * Groups the input strings based on their shifting sequences.
     *
     * @param strings The input array of strings.
     * @return A list of lists, where each inner list contains strings of the same shifting sequence.
     */
    public List<List<String>> groupStrings2(String[] strings) {
        // Iterate over each string in the input array.
        for (String str : strings) {
            // Calculate the unique key (weight) for the string.
            String key = weight(str);
            // If this weight is encountered for the first time, initialize a new list in the map.
            map.putIfAbsent(key, new ArrayList<>());
            // Add the string to the list corresponding to its weight.
            map.get(key).add(str);
        }

        // Return the lists of grouped strings.
        return new ArrayList<>(map.values());
    }

    /**
     * Calculates a unique weight for the input string based on its shifting sequence.
     * The weight serves as a unique identifier to group strings of the same type together.
     *
     * @param str The input string.
     * @return The weight of the string.
     */
    private String weight(String str) {
        // If the string consists of a single character, assign a special weight "*" to it.
        if (str.length() <= 1) {
            return "*";
        }

        StringBuilder weight = new StringBuilder("-");
        // Iterate over the string, starting from the second character.
        for (int i = 1; i < str.length(); i++) {
            // Calculate the difference between the current character and the previous character.
            int diff = str.charAt(i) - str.charAt(i - 1);

            // If the difference is negative (i.e., the current character comes before the previous character in the
            // alphabet),
            // adjust the difference by adding CIPHER_RANGE to normalize it.
            int value = (diff < 0 ? diff + CIPHER_RANGE : diff);

            // Append the adjusted difference to the weight.
            weight.append("-").append(value);
        }

        // Return the weight as a string.
        return weight.toString();
    }

    public static void main(String[] args) {
        GroupShiftedStrings sol = new GroupShiftedStrings();
        String[] test = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        List<List<String>> result = sol.groupStrings2(test);
        for (List<String> group : result) {
            System.out.println(group);
        }
    }
}
