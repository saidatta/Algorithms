package Int;

import static java.lang.System.out;

/**
 * find 95th percentile within an unsorted array of 2 million numbers.
 *
 * Created by venkatamunnangi on 5/3/17.
 */
public class QuickSelectWithPercentileSandeep {

    public static void main(String[] args) {
        QuickSelectWithPercentileSandeep qs = new QuickSelectWithPercentileSandeep();

        int[] arr = {43, 54, 87, 88, 89, 93, 95, 96, 98, 99, 100, 56, 61, 62, 66, 68, 69, 69, 70, 71, 72, 77, 78, 79, 85};
        int percentile = 90;

        out.println(qs.pthPercentile(arr, percentile));
    }


    public int pthPercentile(int[] array, int percentile) {
        //-1 because of quick select index starts from 0 & the percentile formula
        int percentileIndex = (int) Math.round((percentile / 100D) * array.length);
        System.out.print(percentileIndex + " "+ array.length) ;
        //int percentileIndex = (int) Math.round((percentile / 100D) * array.length)-1;
        //return quickSelect(array, percentileIndex);
        return array[quickSelect2(array, 0, array.length-1, percentileIndex)];
    }

    // return the index of the kth smallest number
    int quickSelect2(int[] a, int lo, int hi, int k) {
        // use quick sort's idea
        // put nums that are <= pivot to the left
        // put nums that are  > pivot to the right
        int i = lo, j = hi, pivot = a[hi];
        while (i < j) {
            if (a[i++] > pivot) {
                swap(a, --i, --j);
            }
        }
        swap(a, i, hi);

        // count the nums that are <= pivot from lo
        // +1 because u need the next index after the left sub array.
        int m = i - lo + 1;

        // pivot is the one!
        if (m == k) {
            return i;
        } else if (m > k) {
            // pivot is too big, so it must be on the left
            return quickSelect2(a, lo, i - 1, k);
        } else {
            // pivot is too small, so it must be on the right
            return quickSelect2(a, i + 1, hi, k - m);
        }
    }


    public int quickSelect(int[] nums, int k) {
        int start = 0, end = nums.length - 1, index = k;
        while (start < end) {
            int pivot = partion(nums, start, end);
            if (pivot < index) {
                start = pivot + 1;
            } else if (pivot > index) {
                end = pivot - 1;
            } else {
                return nums[pivot];
            }
        }
        return nums[start];
    }

    // 0 24
    private int partion(int[] nums, int start, int end) {
        int pivot = start;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) {
                start++;
            }
            while (start <= end && nums[end] > nums[pivot]) {
                end--;
            }
            if (start > end) {
                break;
            }

            swap(nums, start, end);
        }
        swap(nums, end, pivot);
        return end;
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
