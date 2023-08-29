package leetcode.array;

/**
 * https://leetcode.com/problems/find-peak-element/
 *
 * Created by venkatamunnangi on 10/7/19.
 */
public class FindPeakElement {

    //It's not hard to show the correctness, just visualize the trend.
    // If the next number is larger than current, it show an increasing curve,
    // then the peak must in the right half, else in the left half. Since we know
    // there's a local peak for sure.

    public int findPeakElement(int... nums) {
        int peak = -1;
        if(nums == null || nums.length ==0){
            return peak;
        }
        int low = 0, high = nums.length-1;
        while(low<=high) {
            int mid = low + (high-low)/2;
            if(mid==nums.length-1 || nums[mid]>nums[mid+1]) {
                peak = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        return peak;
    }
}
