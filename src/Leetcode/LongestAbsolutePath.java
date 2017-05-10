package Leetcode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-absolute-file-path/#/description
 *
 * Google
 *
 * Created by venkatamunnangi on 1/9/17.
 */
public class LongestAbsolutePath {

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

        for (String currentToken : tokens) {
            int level = countLevel(currentToken);

            // if current directory/file depth is lower that the top directory/file on the stack, pop from stack
            while (stack.size() > level) {
                curLen -= stack.pop();
            }

            // +1 here because a "/" needs to be counted following each diretory
            int len = currentToken.replaceAll("\t", "").length() + 1;
            curLen += len;

            // if currentToken contains ".", we have found a file!
            if (currentToken.contains(".")) {
                result = curLen - 1 > result ? curLen - 1 : result;
            }
            stack.add(len);
        }
        return result;
    }

    private int countLevel(String s) {
        String cur = s.replaceAll("\t", "");
        return s.length() - cur.length();
    }

    public int lengthLongestPath2(String input) {
        if(input == null || input.trim().length() == 0) {
            return 0;
        }

        String[] paths = input.split(NEW_LINE);
        int [] stack = new int[paths.length+1];

        int maxLen = 0;
        for(String path: paths) {
            int level = path.lastIndexOf(TAB)+1;
            int nextAbsolutePathMember = level+1;
            // path of previous members + currentPathLength - tab length + length of adding /
            int currLength = stack[nextAbsolutePathMember] = stack[level]+path.length() - level + 1;
            if(path.contains(".")) {
                // The currLength - 1 excludes the / at the very end.
                maxLen = Math.max(maxLen, currLength-1);
            }
        }

         return maxLen;
    }

    public static void main(String [] args) {
        LongestAbsolutePath lap = new LongestAbsolutePath();
        System.out.println(lap.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        lap.lengthLongestPath2("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
    }
}
