package leetcode.math.geometry;

public class RectangleArea {

    /**
     * This method calculates the total area covered by two rectangles.
     * It considers the individual areas of the two rectangles and subtracts the
     * area of their overlapping region (if they overlap).
     *
     * @param ax1, ay1, ax2, ay2 are the coordinates of the first rectangle.
     * @param bx1, by1, bx2, by2 are the coordinates of the second rectangle.
     * @return Total area covered by the two rectangles.
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // Calculate area of first rectangle
        int areaOfRectangle1 = (ax2 - ax1) * (ay2 - ay1);

        // Calculate area of second rectangle
        int areaOfRectangle2 = (bx2 - bx1) * (by2 - by1);

        // Compute the coordinates of the overlapping rectangle
        int leftBoundaryOfOverlap = Math.max(ax1, bx1);
        int rightBoundaryOfOverlap = Math.min(ax2, bx2);
        int bottomBoundaryOfOverlap = Math.max(ay1, by1);
        int topBoundaryOfOverlap = Math.min(ay2, by2);

        // Calculate overlap area only if there is an overlap
        int overlapArea = 0;
        if (leftBoundaryOfOverlap < rightBoundaryOfOverlap && bottomBoundaryOfOverlap < topBoundaryOfOverlap) {
            overlapArea = (rightBoundaryOfOverlap - leftBoundaryOfOverlap) * (topBoundaryOfOverlap - bottomBoundaryOfOverlap);
        }

        // Total area is sum of individual areas minus overlap (if any)
        return areaOfRectangle1 + areaOfRectangle2 - overlapArea;
    }

    public static void main(String[] args) {
        RectangleArea calculator = new RectangleArea();
        System.out.println(calculator.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));  // Expected output: 45
        System.out.println(calculator.computeArea(-2, -2, 2, 2, -2, -2, 2, 2));  // Expected output: 16
    }
}
