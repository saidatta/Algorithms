package hackrrank_test;

import java.util.*;

/**
 * Created by venkatamunnangi on 9/23/17.
 */
public class P2 {

    // fruit:number
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = null;

        Map<String, Integer> dict = new HashMap<>();
        try {
            scanner = new Scanner (System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();

                populateFruits(str, dict);
            }
            frequencySort(dict);

        } finally {
            if (scanner != null) scanner.close();
        }

    }

    private static void populateFruits(String str,Map<String, Integer> dict) {
        if(dict.containsKey(str)) {
            dict.put(str, dict.get(str)+1);
        } else {
            dict.put(str, 1);
        }
    }

    public static void frequencySort(Map<String, Integer> dict) {

        List<String>[] bucket = new ArrayList[dict.size() + 1];
        for (String key : dict.keySet()) {
            int frequency = dict.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        Set<String> sortedFruits = new HashSet<>();
        for (int numOfOccurences = bucket.length - 1; numOfOccurences >=0; numOfOccurences--) {
            if (bucket[numOfOccurences] != null) {
                List<String> fruitNames = bucket[numOfOccurences];
                Collections.sort(fruitNames);
                for (String fruit : fruitNames) {
                    for (int i = 0; i < dict.get(fruit); i++) {
                        sortedFruits.add(fruit+":"+numOfOccurences);
                    }
                }
            }
        }

        for(String str : sortedFruits) {
            System.out.println(str);
        }


    }
}
