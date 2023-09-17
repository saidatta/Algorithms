package leetcode.stack.string;

import java.util.Stack;

// https://leetcode.com/problems/simplify-path/description/
public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] parts = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String part : parts) {
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!part.isEmpty() && !part.equals(".")) {
                stack.push(part);
            }
        }

        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }

        return result.length() > 0 ? result.toString() : "/";
    }

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/home/")); // Output: "/home"
        System.out.println(simplifyPath.simplifyPath("/../"));   // Output: "/"
        System.out.println(simplifyPath.simplifyPath("/home//foo/")); // Output: "/home/foo"
    }

}
