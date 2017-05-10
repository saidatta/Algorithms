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

        int[] arr = {43, 54, 87, 88, 89, 93, 95, 96, 98, 99, 100, 56, 61, 62, 66, 68, 69, 69, 70, 71, 72, 77, 78, 79, 85,};
        int percentile = 90;

        out.println(qs.pthPercentile(arr, percentile));
    }


    public int pthPercentile(int[] array, int percentile) {
        //-1 because of quick select index starts from 0 & the percentile formula
        int percentileIndex = (int) Math.round((percentile / 100D) * array.length) - 1;
        return quickSelect(array, percentileIndex);
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

    private int partion(int[] nums, int start, int end) {
        int pivot = start, temp;
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
