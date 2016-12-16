package Leetcode.String;

/**
 * Created by venkatamunnangi on 12/1/16.
 */
public class CountAndSay {
    public String countAndSay(int n) {
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;
        int count;

        for(int i = 1; i < n;i++) {
            prev = curr;
            int start = prev.charAt(0);
            curr = new StringBuilder();
            count =1;

            for(int j = 1; j < prev.length(); j++) {
                if(start != prev.charAt(j)) {
                    curr.append(count).append(start);
                    count = 1;
                    start = prev.charAt(j);
                } else {
                    count++;
                }
            }

        }
        return curr.toString();
    }
}
