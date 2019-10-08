package Leetcode.String;

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
    // Encodes a list of toBeEncodedStrings to a single string.
    public String encode(List<String> toBeEncodedStrings) {
        StringBuilder sb = new StringBuilder();

        for(String currentString : toBeEncodedStrings) {
            sb.append(currentString.length()).append('/').append(currentString);
        }

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> decodedStrings = new ArrayList<>();
        int i = 0;
        while(i < s.length()) {
            int slashIndex = s.indexOf('/', i);
            int sizeOfString = Integer.parseInt(s.substring(i, slashIndex));
            decodedStrings.add(s.substring(slashIndex + 1, slashIndex + sizeOfString + 1));
            i = slashIndex + sizeOfString + 1;
        }
        return decodedStrings;
    }

    public static void main(String [] args) {
        EncodeAndDecodeStrings encodeAndDecodeStrings = new EncodeAndDecodeStrings();
        out.println(encodeAndDecodeStrings.encode(Arrays.asList("ABC", "CA")));
        out.println(encodeAndDecodeStrings.decode(encodeAndDecodeStrings.encode(Arrays.asList("ABC", "CA"))));

    }
}
