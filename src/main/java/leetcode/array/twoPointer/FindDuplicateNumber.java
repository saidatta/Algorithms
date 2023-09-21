package leetcode.array.twoPointer;

// https://leetcode.com/problems/find-the-duplicate-number/description/
public class FindDuplicateNumber {

//     Floyd's Tortoise and Hare (Cycle Detection) algorithm. Since each number in the array represents an index in the
//     array, you can use it to traverse the array in a manner similar to detecting a loop in a linked list.
//
//    Start by initializing two pointers, the tortoise and the hare, at the beginning of the array.
//    Move the tortoise one step at a time, and the hare two steps at a time.
//    If there is a cycle (because of the repeated number), the hare and tortoise will meet at some point.
//    When they meet, reinitialize one of the pointers to the start of the array.
//    Move both the pointers one step at a time. When they meet again, the meeting point will be the start of the cycle,
//    i.e., the duplicate number.
    public int findDuplicate(int[] nums) {
        // Phase 1: Detecting the loop using Floyd's Cycle Detection
        int tortoise = nums[0];
        int hare = nums[nums[0]];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        }

        // Phase 2: Finding the start of the cycle (duplicate number)
        hare = 0;
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }

    // How can we prove that at least one duplicate number must exist in nums?
//    1. Proof: The nums array has n+1 numbers, and each number is between 1 and n inclusive. If all numbers were
//    distinct, then we could only fill up n slots. Since there are n+1 numbers, according to the pigeonhole principle,
//    at least one number must be duplicated.
//
// Can you solve the problem in linear runtime complexity?
//    2. Linear runtime complexity: The above solution uses the Floyd's Cycle Detection algorithm which runs in O(n)
//    time, so it already meets the requirement of linear runtime complexity.
}
