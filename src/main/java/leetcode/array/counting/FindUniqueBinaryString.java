package leetcode.array.counting;

// https://leetcode.com/problems/find-unique-binary-string/editorial/ - S tier
// you can use a strategy known as the "Cantor's diagonal argument", which is a straightforward and efficient approach.
// The idea is to construct a binary string that differs from every string in the given list at least in one position.
// This way, you can guarantee that the constructed string is not in the list.
//
// One way to do this is to iterate through each string in the list, and for the ith string, use the opposite
// character of the ith character in that string for the ith character in your new string. For instance, if the
// ith character is '0', use '1', and vice versa.
public class FindUniqueBinaryString {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            // Flip the ith character of the ith string
            sb.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }
        return sb.toString();
    }
}
