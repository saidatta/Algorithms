package leetcode.array.binarysearch;

// https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
public class PeakIndexMountainArray {
    FindPeakElement findPeakElement = new FindPeakElement();
    public int peakIndexInMountainArray(int[] arr) {
        return findPeakElement.findPeakElement(arr);
    }

    public static void main(String[] args) {
        PeakIndexMountainArray solution = new PeakIndexMountainArray();

        int[] arr1 = {0,1,0};
        System.out.println(solution.peakIndexInMountainArray(arr1));  // Expected output: 1

        int[] arr2 = {0,2,1,0};
        System.out.println(solution.peakIndexInMountainArray(arr2));  // Expected output: 1

        int[] arr3 = {0,10,5,2};
        System.out.println(solution.peakIndexInMountainArray(arr3));  // Expected output: 1
    }
}
