package leetcode.string.slidingwindow;

// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
public class RemoveAdjacentDuplicates {
    public static String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.charAt(stack.length() - 1) == c) {
                stack.deleteCharAt(stack.length() - 1);
            } else {
                stack.append(c);
            }
        }
        return stack.toString();
    }

    public static void main(String[] args) {
        String s1 = "abbaca";
        System.out.println(removeDuplicates(s1));  // Expected output: "ca"

        String s2 = "azxxzy";
        System.out.println(removeDuplicates(s2));  // Expected output: "ay"
    }
}
