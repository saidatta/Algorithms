package leetcode.string.array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/text-justification/description/
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;

        while (index < words.length) {
            // Determine how many words fit in this line
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > maxWidth) break;
                count += words[last].length() + 1;
                last++;
            }

            // Build the line
            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;

            // If this is the last line or only contains one word, left-justify
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i]).append(" ");
                }
                builder.deleteCharAt(builder.length() - 1);
                while (builder.length() < maxWidth) {
                    builder.append(" ");
                }
            } else {
                // Middle justified
                int spaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        builder.append(" ".repeat(Math.max(0, (spaces + ((i - index) < r ? 1 : 0)) + 1)));
                    }
                }
            }
            result.add(builder.toString());
            index = last;
        }

        return result;
    }
}
