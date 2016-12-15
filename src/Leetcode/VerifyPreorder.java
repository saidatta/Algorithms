package Leetcode;

/**
 * Created by venkatamunnangi on 08/11/16.
 */
public class VerifyPreorder {
    public boolean verifyPreorder(int[] preorder) {
        int[] pointer = {0};
        helper(preorder, pointer, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return preorder.length == pointer[0];
    }

    private void helper(int[] preorder, int[] pointer, int min, int max) {
        if(pointer[0] == preorder.length) return;

        int cur = preorder[pointer[0]];  // we check the cur element in arr
        if(cur > min && cur < max) {    // if it can be valid to put here, we increment the pointer. Saying that we already use this value to "create a node and return"
            ++pointer[0];
            helper(preorder, pointer, min, cur);
            helper(preorder, pointer, cur, max);
        }
    }
}
