package Leetcode.Array.search;

public class FindSmallestLetterGreaterThanTarget {
    public char findNextGreatestLetter(char[] letters, char target) {
            int nextGreaterIndex = binarySearch(letters, target);

            // % here becuse if we dont find the array or its right most, then you have to return 0th element.
            return letters[(nextGreaterIndex + 1) % letters.length];
        }

        private int binarySearch(char[] letters, char target) {
            int low = 0, high = letters.length - 1;

            while (low <= high) {
                int mid = (low + high) >>> 1;
                //because of equals here. you will find the right most side of the target.
                if (letters[mid] <= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return high;
        }
}
