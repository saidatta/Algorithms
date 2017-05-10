package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/encode-and-decode-strings/#/description
 *
 * Created by venkatamunnangi on 4/30/17.
 */
public class EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s : strs) {
            sb.append(s.length()).append('/').append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ret = new ArrayList<>();
        int i = 0;
        while(i < s.length()) {
            int slash = s.indexOf('/', i);
            int size = Integer.parseInt(s.substring(i, slash));
            ret.add(s.substring(slash + 1, slash + size + 1));
            i = slash + size + 1;
        }
        return ret;
    }

    public static void main(String [] args) {
        EncodeAndDecodeStrings encodeAndDecodeStrings = new EncodeAndDecodeStrings();
        out.println(encodeAndDecodeStrings.encode(Arrays.asList("ABC", "CA")));
        out.println(encodeAndDecodeStrings.decode(encodeAndDecodeStrings.encode(Arrays.asList("ABC", "CA"))));

    }
}
