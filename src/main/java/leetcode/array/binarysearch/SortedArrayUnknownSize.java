package leetcode.array.binarysearch;

public class SortedArrayUnknownSize {
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) {
            return 0;
        }

        // Find the bounds for binary search
        int left = 0, right = 1;
        while (reader.get(right) < target && reader.get(right) < Integer.MAX_VALUE) {
            left = right;
            // double the right index
            right <<= 1;
        }

        // Binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == target) {
                return mid;
            }
            if (reader.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;  // target not found
    }
}
