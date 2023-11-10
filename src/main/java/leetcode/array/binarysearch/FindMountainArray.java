package leetcode.array.binarysearch;

public class FindMountainArray {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = findPeak(mountainArr);
        int index = binarySearch(mountainArr, target, 0, peak, true);
        if (index != -1) {
            return index;
        }
        return binarySearch(mountainArr, target, peak + 1, mountainArr.length() - 1, false);
    }

    private int findPeak(MountainArray mountainArr) {
        int start = 0;
        int end = mountainArr.length() - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                // Ascending part of the mountain
                start = mid + 1;
            } else {
                // Descending part of the mountain
                end = mid;
            }
        }
        // start and end will converge to the peak index
        return start;
    }

    private int binarySearch(MountainArray mountainArr, int target, int start, int end, boolean asc) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int value = mountainArr.get(mid);
            if (value == target) {
                return mid;
            }
            if (asc) {
                if (value < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                if (value > target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}

interface MountainArray {
    int get(int index);
    int length();
}