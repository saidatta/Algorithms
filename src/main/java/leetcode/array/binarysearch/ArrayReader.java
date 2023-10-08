package leetcode.array.binarysearch;

public class ArrayReader {
    private final int[] arr;

    public ArrayReader(int[] arr) {
        this.arr = arr;
    }

    public int get(int index) {
        if (index >= arr.length || index < 0) {
            return Integer.MAX_VALUE;
        }
        return arr[index];
    }
}

