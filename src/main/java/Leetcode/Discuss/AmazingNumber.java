package Leetcode.Discuss;

import java.util.LinkedList;

/**
 * https://discuss.leetcode.com/topic/67628/fb-phone-find-amazing-number
 * https://www.careercup.com/question?id=6018738030641152
 * <p>
 * Define amazing number as: its value is less than or equal to its index. Given a circular array, find the
 * starting position, such that the total number of amazing numbers in the array is maximized.
 * <p>
 * Example 1: 0, 1, 2, 3
 * Ouptut: 0. When starting point at position 0, all the elements in the array are equal to its index.
 * So all the numbers are amazing number.
 * <p>
 * Example 2: 1, 0 , 0
 * Output: 1. When starting point at position 1, the array becomes 0, 0, 1. All the elements are amazing number.
 * If there are multiple positions, return the smallest one.
 * <p>
 * should get a solution with time complexity less than O(N^2)
 * <p>
 * Created by venkatamunnangi on 3/29/17.
 */
public class AmazingNumber {
/*
 1) obvious solution, try all sub-sequences which are n*(n-1) leading to O(n²) runtime with O(1) space
2) there is a better approach:
- for each element we can know for which interval of start index it will count as an amazing number
- so, we get n intervals and need to know what is the best start. start can be between 0 and n
- if we go through 0..n for each interval, when we have a list with all start and all ends, we can
  find the desired lowest start index if interval starts and ends are sorted

2) in detail, assuming the following array:

index: 0, 1, 2, 3, 4, 5, 6,
value: 4, 2, 8, 2, 4, 5, 3,
n = 7

value 4 at index 0: can be used if start index is between 1 and 3
    becaue there must be at least 4 elements before a[0] to satisfy
	a[0] >= index.
	that is 0 + 1 .. n + 0 - a[0]
value 2 at index 1: can be used if start index is between 2 and 6
    that is 1 + 1 .. n + 1 - a[1]
value 8 at index 2 can never be used because 8>n
	that is 2 + 1 .. n + 2 - a[2] => 3 .. 1 (which is not an interval)
value 2 at index 3 can be used if start index is between 4 and 8 (*),
	that is 3 + 1 .. n + 3 - a[3]
value 4 at index 4 can be used if start index is between 5..7
    that is 4 + 1 .. n + 4 - a[4]
value 5 at index 5 can be used if start index is between 6..7
	that is 5 + 1 .. n + 5 - a[5]
value 3 at in dex 6 can be used if start index is between 7..10
	that is 6 + 1 .. n + 6 - a[6]

result: at index 6 (4 values are amazing)
        at index 7 (4 values are amazing)
		note index 7 = 0, 0 < 6, therefore the result is 0

(*) if index > 6 means basically just index - n, or more generic index mod n
    due to circular buffer characteristics of the problem
	create two intervals, one from start to n-1 and one from 0 to end mod n

sorting the two vectors with start and end index will allow to calculate the
index at which the most intervals overlap which is essentially the index of
interest.

Finding the index works as follows:
let k be the number of intervals covered by the current index
int k = 0;
int maxk = 0;
int maxki = 0;
int s = 0;
int e = 0;
for(int i = 0; i < n; i++)
{
	while(s < start.size() && start[s] == i)  { s++; k++; }
	if(k > maxk) // new found { ... }
	while(e < end.size() && end[e] == i) { e++; k--; }
}

runtime complexity:
creating the start and end vectors: O(n)
sorting start and end vectors O(n * log(n))
finding max index: O(n)
total runtime: O(n * log(n))

space complexity: O(n)

*/
    private int bruteForce(int[] a) {
        int res = 0;
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            int count = 0;
            for (int j = 0; j < a.length; j++) {
                int p = j + i;
                if (j - a[p % a.length] >= 0) count++;
            }
            if (count > max) {
                max = count;
                res = i;
            }
        }
        return res;
    }

    public int amazingNumber(int[] a) {
        int len = a.length;
        LinkedList<Interval> intervals = new LinkedList<>();

        // find all the intervals that if the array starts at any index in the interval, there will
        // be at least 1 element is amazing number
        for (int i = 0; i < len; i++) {
            if (a[i] >= len) continue;
            int start = (i + 1) % len;
            int end = (len + (i - a[i])) % len;
            System.out.println(i + ": " + start + " - " + end);
            intervals.add(new Interval(start, end));
        }

        // now our problem has just become: "find the year that has the maximum number of people
        // alive"
        int[] count = new int[len];
        for (Interval i : intervals) {
            count[i.start]++;
            if (i.end + 1 < count.length) count[i.end + 1]--;
        }
        int max = 0;
        int counter = 0;
        int idx = 0;
        for (int i = 0; i < count.length; i++) {
            counter += count[i];
            if (counter > max) {
                max = counter;
                idx = i;
            }
        }

        return idx;
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        AmazingNumber amazingNumber = new AmazingNumber();

        int[] a = {4, 3, 6, 8, 0, 3, 2, 3};
        //         6  7  0  1  2  3  4  5 = 6
        //         7  0  1  2  3  4  5  6 = 5
        //         4  5  6  7  0  1  2  3 = 6
//        a = new int[]{0, 1, 2, 3};
//        a = new int[]{1, 0, 0};

        System.out.println(amazingNumber.amazingNumber(a));
//        System.out.println(bruteForce(a));
    }

}
