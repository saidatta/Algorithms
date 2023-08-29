package leetcode.string;

// https://leetcode.com/problems/furthest-point-from-origin/
public class FurthestPointOrigin {
    public static int maxDistance(String moves) {
        int countL = 0, countR = 0, count_ = 0;

        // Step 1: Count the characters
        for (char move : moves.toCharArray()) {
            if (move == 'L') countL++;
            else if (move == 'R') countR++;
            else count_++;
        }

        // Step 2: Calculate the distance
        int distanceFromOrigin = Math.abs(countL - countR);

        // Add the underscores to the maximum possible direction (left or right)
        return distanceFromOrigin + count_;
    }
    public static void main(String [] args) {
        // Test
        System.out.println(maxDistance("L_RL__R")); // 3
        System.out.println(maxDistance("_R__LL_")); // 5
        System.out.println(maxDistance("_______")); // 7

    }


}
