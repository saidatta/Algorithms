package leetcode.math;

// https://leetcode.com/problems/angle-between-hands-of-a-clock/description/
public class ClockAngle {
    public static double angleClock(int hour, int minutes) {
        // Calculate the angles of the minute and hour hands
        double minuteAngle = minutes * 6;
        double hourAngle = (hour % 12) * 30 + minutes * 0.5;

        // Find the difference between the two angles
        double angleDifference = Math.abs(minuteAngle - hourAngle);

        // Get the smaller angle
        return Math.min(angleDifference, 360 - angleDifference);
    }

    public static void main(String[] args) {
        System.out.println("Angle (Example 1): " + angleClock(12, 30)); // Output: 165
        System.out.println("Angle (Example 2): " + angleClock(3, 30));  // Output: 75
        System.out.println("Angle (Example 3): " + angleClock(3, 15));  // Output: 7.5
    }
}
