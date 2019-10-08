package Int;

import java.util.*;

/**
 * Created by venkatamunnangi on 9/16/19.
 */
public class MS3 {
    public static int solution(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> freqMap = new HashMap<>();
        Map<Integer, List<Character>> reverseFrequency = new HashMap<>();


        for(char c : s.toCharArray()) {
            if(freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c)+1);
            } else {
                freqMap.put(c, 1);
            }
        }

        //aaaabbbbccdd
        for(char c : s.toCharArray()) {
            int currentCharCount = freqMap.get(c);
            if(freqMap.containsKey(currentCharCount)) {
                reverseFrequency.get(currentCharCount).add(c);
            } else {
                List<Character> arr = new ArrayList<>();
                arr.add(c);
                reverseFrequency.put(currentCharCount, arr);
            }
        }

        int edits = 0;

        for(int c : reverseFrequency.keySet()) {
            List<Character> currList = reverseFrequency.get(c);

            Iterator<Character> i = currList.iterator();
            int tempCount = 1;
            while (i.hasNext()) {
                Character character = i.next();
                i.remove();
                edits++;
                reverseFrequency.get(c-(tempCount--)).add(character);
            }
        }

        return edits;
    }

    public static void main(String [] args) {
//        System.out.printnl()
    }

}
