package Leetcode;

/**
 * https://leetcode.com/problems/longest-absolute-file-path/#/description
 *
 * Created by venkatamunnangi on 1/9/17.
 */
public class LongestAbsolutePath {

    String TAB = "\t";
    String NEW_LINE = "\n";

    //dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext


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
        if(input == null || input.trim().length() == 0) {
            return 0;
        }

        String[] paths = input.split("\n");
        int [] stack = new int[paths.length+1];

        int maxLen = 0;
        for(String path: paths) {
            int level = path.lastIndexOf("\t")+1;//number of /ts
            stack[level+1] = stack[level]+path.length() - level + 1;
            int currLength = stack[level+1];
            if(path.contains(".")) {
                maxLen = Math.max(maxLen, currLength-1);
            }
        }

        return maxLen;
    }

    public int lengthLongestPath2(String input) {
        if(input == null || input.trim().length() == 0) {
            return 0;
        }

        String[] paths = input.split("\n");
        int [] stack = new int[paths.length+1];

        int maxLen = 0;
        for(String path: paths) {
            int level = path.lastIndexOf("\t")+1;
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
