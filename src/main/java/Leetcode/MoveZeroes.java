package Leetcode;

/**
 * https://leetcode.com/problems/move-zeroes/#/description
 *
 * Created by venkatamunnangi on 3/23/17.
 */
public class MoveZeroes {

    public static void main(String [] args) {
        System.out.println(secondMax(3,2,1));
        System.out.println(secondMax(1,2,3));
        System.out.println(secondMax(2,3,1,4));

    }
    static int secondMax(int... array) {

            int n = array.length;
            int largest = Integer.MIN_VALUE;
            int secondLargest = Integer.MIN_VALUE;

            if (n == 0) {
                return -1;
            } else {
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        if (array[j] > largest) {
                            secondLargest = largest;
                            largest = array[j];
                        } else if (array[j] > secondLargest && array[j] < largest) {
                            secondLargest = array[j];
                        }
                    }
                }
            }
            return secondLargest;
        }

        // 0(n)
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }

        int p1 = 0, p2 = 1;
        // 0 1 0 3 12 ->1 0 0 3 12
        //-> 1 3 12 0 0
        while(p2 < nums.length) {
            if(nums[p1] == 0 && nums[p2] != 0) {
                nums[p1] = nums[p2];
                nums[p2] = 0;
                p1++;
                p2++;
            } else {
                if(nums[p1] != 0) {
                    p1++;
                }
                p2++;
            }
        }
    }

}
