package Leetcode.Array;

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
 * <p>
 * <p>
 * Created by venkatamunnangi on 9/7/19.
 */
public class GroupShiftedStrings {

    public static void main(String[] args) {
        GroupShiftedStrings groupShiftedStrings = new GroupShiftedStrings();

        System.out.println(groupShiftedStrings.groupStrings("bcd","xyz"));
    }

    public List<List<String>> groupStrings(String... inputStrings) {
        List<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> map
                = new HashMap<String, ArrayList<String>>();
        for (String currentString : inputStrings) {
            char[] currentCharArray = currentString.toCharArray();
            if (currentCharArray.length > 0) {
                int diff = currentCharArray[0] -'a';
                for (int i = 0; i < currentCharArray.length; i++) {
                    if (currentCharArray[i] - diff <'a'){
                        currentCharArray[i] = (char) (currentCharArray[i] - diff + 26);
                    }else{
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
        result.addAll(map.values());
        return result;
    }
}
