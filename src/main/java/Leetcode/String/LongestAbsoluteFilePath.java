package Leetcode.String;

import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-absolute-file-path/#/description
 *
 * Google
 *
 * Created by venkatamunnangi on 1/9/17.
 */
public class LongestAbsoluteFilePath {

    String TAB = "\t";
    String NEW_LINE = "\n";

    /**
     * dir
     * \tsubdir
     * \tsubdir2
     * \t\tfile.ext
     *
     * @param input
     * @return
     */
    public int lengthLongestPath(String input) {
        String[] tokens = input.split("\n");
        int result = 0;
        int curLen = 0;
        Stack<Integer> stack = new Stack<>();

        for (String s : tokens) {
            int level = countLevel(s);

            // if current directory/file depth is lower that the top directory/file on the stack, pop from stack
            while (stack.size() > level) {
                curLen -= stack.pop();
            }

            // +1 here because a "/" needs to be counted following each directory
            int len = s.replaceAll("\t", "").length() + 1;
            curLen += len;

            // if s contains ".", we have found a file!
            if (s.contains(".")) {
                result = Math.max(curLen - 1 , result);
            }
            stack.add(len);
        }
        return result;
    }

    private int countLevel(String s) {
        String cur = s.replaceAll("\t", "");
        return s.length() - cur.length();
    }

    public static void main(String [] args) {
        LongestAbsoluteFilePath lap = new LongestAbsoluteFilePath();
        System.out.println(lap.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }
}
