package leetcode.math.geometry;

// https://leetcode.com/problems/rectangle-overlap/
public class RectangleOverlap {

    /**
     * This method checks if two rectangles overlap.
     *
     * Conditions to verify overlap:
     * 1. Check if one rectangle is to the left of the other rectangle.
     * 2. Check if one rectangle is to the right of the other rectangle.
     * 3. Check if one rectangle is above the other rectangle.
     * 4. Check if one rectangle is below the other rectangle.
     *
     * @param rec1 First rectangle coordinates.
     * @param rec2 Second rectangle coordinates.
     * @return true if rectangles overlap, false otherwise.
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int rec1Left = rec1[0];
        int rec1Bottom = rec1[1];
        int rec1Right = rec1[2];
        int rec1Top = rec1[3];

        int rec2Left = rec2[0];
        int rec2Bottom = rec2[1];
        int rec2Right = rec2[2];
        int rec2Top = rec2[3];

        return !(rec2Left >= rec1Right ||
                rec1Left >= rec2Right ||
                rec2Bottom >= rec1Top ||
                rec1Bottom >= rec2Top);
    }

    public static void main(String[] args) {
        RectangleOverlap obj = new RectangleOverlap();
        System.out.println(
                obj.isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));  // Expected output: true
        System.out.println(
                obj.isRectangleOverlap(new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1}));  // Expected output: false
        System.out.println(
                obj.isRectangleOverlap(new int[]{0, 0, 1, 1}, new int[]{2, 2, 3, 3}));  // Expected output: false
    }
}

